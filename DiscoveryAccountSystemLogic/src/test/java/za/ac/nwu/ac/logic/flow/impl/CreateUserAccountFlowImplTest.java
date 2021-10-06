package za.ac.nwu.ac.logic.flow.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.ac.domain.dto.UserAccountDto;
import za.ac.nwu.ac.translator.UserAccountTranslator;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class CreateUserAccountFlowImplTest {

    @Mock
    private UserAccountTranslator userAccountTranslatorMock;

    @InjectMocks
    private CreateUserAccountFlowImpl createUserAccountFlowMock;

    @Test
    public void create() {
        when(userAccountTranslatorMock.create(any(UserAccountDto.class))).thenReturn(new UserAccountDto());
        UserAccountDto result = createUserAccountFlowMock.create(new UserAccountDto());
        assertNotNull(result);
        verify(userAccountTranslatorMock,atLeastOnce()).create(any(UserAccountDto.class));
    }


}