package za.ac.nwu.ac.logic.flow.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.ac.domain.dto.AccountTransactionDto;
import za.ac.nwu.ac.translator.AccountTransactionTranslator;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CreateTransactionFlowImplTest {
    @Mock
    private AccountTransactionTranslator testTranslator;

    @InjectMocks
    private CreateTransactionFlowImpl testFlow;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void create() {
        when(testTranslator.create(any(AccountTransactionDto.class))).thenReturn(null);
        AccountTransactionDto result = testFlow.create(new AccountTransactionDto());
        assertNull(result);
        verify(testTranslator).create(any(AccountTransactionDto.class));
    }
}