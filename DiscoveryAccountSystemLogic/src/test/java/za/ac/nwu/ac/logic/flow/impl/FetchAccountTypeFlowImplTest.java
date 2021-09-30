package za.ac.nwu.ac.logic.flow.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.translator.AccountTypeTranslator;

import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FetchAccountTypeFlowImplTest {

    @Mock
    private AccountTypeTranslator testTranslator;

    @InjectMocks
    private FetchAccountTypeFlowImpl testFlow;

    @Before
    public void setUp() throws Exception {
//        testFlow= new FetchAccountTypeFlowImpl(null);
    }

    @After
    public void tearDown() throws Exception {
//        testFlow=null;
    }

    @Ignore //Will skip this test
    @Test
    public void getAllAccountTypes() {
        //ToDo: fix getAllAccountTypestest

        List<AccountTypeDto> allAccountTypes;
        //Boolean result = when(testTranslator.getAllAccountTypes()).then();

        //assertEquals("Should Return False", false, result);
        verify(testTranslator, atLeastOnce());
    }
@Ignore
    @Test
    public void getAccountTypeByMnemonic(){
    //ToDo: fix  getAccountTypeByMnemonictest

//        AccountTypeDto accountTypeDto = new AccountTypeDto();
        when(testTranslator.getAccountTypeByMnemonic(anyString())).thenThrow(new RuntimeException());
//        try{
//            testFlow.getAccountTypeByMnemonic(null);
//            fail("Should throw exception");
//        }catch (Exception e){
//
//        }
        testFlow.getAccountTypeByMnemonic("");
        verify(testTranslator, times(1)).getAccountTypeByMnemonic(anyString());
        //verify(testTranslator, never().getAccountTypeByMnemonic(anyString()));
//        AccountTypeDto result = testFlow.getAccountTypeByMnemonic("");
//        assertEquals(result.getMnemonic(), null);
    }

//    @Test
//    public void create(){
//        AccountTypeDto accountTypeDto =
//                new AccountTypeDto("mnemonic", "name", LocalDate.parse("2021-01-01"));
//        when(testTranslator.create(eq(null))).thenReturn(accountTypeDto);
//        AccountTypeDto result =testFlow.create(new accountTypeDTO());
//    }
}