package za.ac.nwu.ac.web.sb.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.logic.flow.CreateAccountTypeFlow;
import za.ac.nwu.ac.logic.flow.FetchAccountTypeFlow;
import za.ac.nwu.ac.logic.flow.ModifyUserAccountFlow;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountTypeControllerTest {

    private static final String APP_URL="/account-system/mvc";
    private static final String ACCOUNT_TYPE_CONTROLLER_URL = APP_URL + "/account-type";

    @Mock
    private FetchAccountTypeFlow fetchAccountTypeFlow;

    @Mock
    private CreateAccountTypeFlow createAccountTypeFlow;

    @Mock
    private ModifyUserAccountFlow modifyUserAccountFlow;

    @InjectMocks
    private AccountTypeController controller;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAll() throws Exception {
        String expectedResponse = "{\"successful\":true,\"payLoad\":[" +
                "{\"mnemonic\":\"MILES\",\"accountTypeName\":\"Miles account type\",\"creationDate\":[2020,1,1]}," +
                "{\"mnemonic\":\"PLAY\",\"accountTypeName\":\"Play account type\",\"creationDate\":[2021,4,1]}]}";
        List<AccountTypeDto> accountTypes = new ArrayList<>();
        accountTypes.add(new AccountTypeDto("MILES", "Miles account type",
                LocalDate.parse("2020-01-01")));
        accountTypes.add(new AccountTypeDto("PLAY", "Play account type",
                LocalDate.parse("2021-04-01")));
        when(fetchAccountTypeFlow.getAllAccountTypes()).thenReturn(accountTypes);
        MvcResult mvcResult = mockMvc.perform(get((String.format("%s/%s",
                        ACCOUNT_TYPE_CONTROLLER_URL, "all")))
                        .servletPath(APP_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        verify(fetchAccountTypeFlow, times(1)).getAllAccountTypes();
        assertEquals(expectedResponse,
                mvcResult.getResponse().getContentAsString());

    }

    @Test
    public void create() throws Exception {
        String accountTypeToBeCreated =
                "{\"mnemonic\":\"MILES\",\"accountTypeName\":\"Miles account type\",\"creationDate\":[2020,1,1]},";
        String expectedResponse = "{\"successful\":true,\"payLoad\":" +
                "{\"mnemonic\":\"MILES\",\"accountTypeName\":\"Miles account type\",\"creationDate\":[2020,1,1]}}";
        AccountTypeDto accountType = new AccountTypeDto("MILES", "Miles account type", LocalDate.parse("2020-01-01"));
                when(createAccountTypeFlow.create(eq(accountType))).then(returnsFirstArg());
        MvcResult mvcResult =
                mockMvc.perform(post(ACCOUNT_TYPE_CONTROLLER_URL)
                                .servletPath(APP_URL)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(accountTypeToBeCreated)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                        .andReturn();
        verify(createAccountTypeFlow, times(1)).create(eq(accountType));
        assertEquals(expectedResponse,
                mvcResult.getResponse().getContentAsString());

    }

    /*
    @Test
    public void updateAccountType() throws Exception {
        String expectedResponse = "{\"successful\":true,\"payload\":" +
            "{\"mnemonic\":\"PLAY\",\"accountTypeName\":\"The new Play account type name\",\"creationDate\":[2021,4,1]}}";
        AccountTypeDto accountType = new AccountTypeDto("PLAY", "The new Play account type name", LocalDate.parse("2021-04-01"));
            when(modifyAccountTypeFlow.updateAccountType(anyString(), anyString(), any(LocalDate.class))).thenReturn(accountType);
        MvcResult mvcResult =
                mockMvc.perform(put((String.format("%s/%s", ACCOUNT_TYPE_CONTROLLER_URL, "PLAY")))
                    .param("newAccountTypeName", "The new Play account type name")
                    .param("newCreationDate", "2021-04-01")
                    .servletPath(APP_URL)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    verify(modifyAccountTypeFlow, times(1)).updateAccountType(eq("PLAY"), eq("The new Play account type name"), eq(LocalDate.parse("2021-04-01")));
        assertEquals(expectedResponse,
            mvcResult.getResponse().getContentAsString());
    }
    */

    /*
    @Test
    public void updateAccountTypeWithNoOptionalDate() throws Exception {
        String expectedResponse = "{\"successful\":true,\"payload\":" +
            "{\"mnemonic\":\"PLAY\",\"accountTypeName\":\"The new Play account type name\",\"creationDate\":[2021,9,1]}}";
        AccountTypeDto accountType = new AccountTypeDto("PLAY", "The new Play account type name", LocalDate.parse("2021-09-01"));
            when(modifyAccountTypeFlow.updateAccountType(anyString(), anyString(), isNull())).thenReturn(accountType);
        MvcResult mvcResult = mockMvc.perform(put((String.format("%s/%s", ACCOUNT_TYPE_CONTROLLER_URL, "PLAY")))
                    .param("newAccountTypeName", "The new Play account
                    type name")
                    .servletPath(APP_URL)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    verify(modifyAccountTypeFlow, times(1)).updateAccountType(eq("PLAY"), eq("The new Play account type name"), eq(null));
        assertEquals(expectedResponse,
            mvcResult.getResponse().getContentAsString());
    }*/

    /*
    @Test
    public void updateAccountTypeObitMandatory() throws Exception {
        mockMvc.perform(put((String.format("%s/%s", ACCOUNT_TYPE_CONTROLLER_URL, "PLAY")))
                .servletPath(APP_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andReturn();
        verify(modifyAccountTypeFlow, never()).updateAccountType(anyString(), anyString(), any(LocalDate.class));
        verify(modifyAccountTypeFlow, never()).updateAccountType(anyString(), anyString(), isNull());
        verify(modifyAccountTypeFlow, never()).updateAccountType(anyString(), isNull(), isNull());
    }*/

}