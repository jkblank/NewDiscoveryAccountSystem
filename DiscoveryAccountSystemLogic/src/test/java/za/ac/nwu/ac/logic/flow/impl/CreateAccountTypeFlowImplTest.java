package za.ac.nwu.ac.logic.flow.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.translator.AccountTypeTranslator;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CreateAccountTypeFlowImplTest {
  @Mock
    private AccountTypeTranslator translator;
  @InjectMocks
    private CreateAccountTypeFlowImpl flow;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
//        flow = null;
    }

    @Test
    public void create() {
        when(translator.create(any(AccountTypeDto.class))).thenReturn(null);
        AccountTypeDto result = flow.create(new AccountTypeDto());
        assertNull(result);
        verify(translator).create(any(AccountTypeDto.class)); //verify that it ran
//        verify(translator, atLeastOnce()).create(any(AccountTypeDto.class)); //verify it ran once
    }
}