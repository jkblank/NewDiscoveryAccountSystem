package za.ac.nwu.ac.logic.flow.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.ac.domain.dto.AccountTransactionDto;
import za.ac.nwu.ac.translator.AccountTransactionTranslator;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CreateTransactionFlowImplTest {

    @Mock
    private AccountTransactionTranslator accountTransactionTranslatorMock;

    @InjectMocks
    private CreateTransactionFlowImpl createTransactionFlowMock;

    @Test
    public void create() {
        when(accountTransactionTranslatorMock.create(any(AccountTransactionDto.class))).thenReturn(new AccountTransactionDto());
        AccountTransactionDto result = createTransactionFlowMock.create(new AccountTransactionDto());
        assertNotNull(result);
        verify(accountTransactionTranslatorMock,atLeastOnce()).create(any(AccountTransactionDto.class));
    }
}