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

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CreateAccountTypeFlowImplTest {


    @InjectMocks
    private CreateAccountTypeFlowImpl createAccountTypeFlowMock;
    @Mock
    private AccountTypeTranslator accountTypeTranslatorMock;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void create() {
        when(accountTypeTranslatorMock.create(any(AccountTypeDto.class))).thenReturn(new AccountTypeDto());
        AccountTypeDto result = createAccountTypeFlowMock.create(new AccountTypeDto());
        assertNotNull(result);
        verify(accountTypeTranslatorMock,atLeastOnce()).create(any(AccountTypeDto.class));
    }
}