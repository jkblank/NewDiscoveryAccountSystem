package za.ac.nwu.ac.logic.flow.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.ac.domain.dto.UserAccountDto;
import za.ac.nwu.ac.translator.UserAccountTranslator;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CreateUserAccountFlowImplTest {

    @Mock
    private UserAccountTranslator testTranslator;

    @InjectMocks
    private CreateUserAccountFlowImpl testflow;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void create() {
        when(testTranslator.create(any(UserAccountDto.class))).thenReturn(null);
        UserAccountDto result = testflow.create(new UserAccountDto());
        assertNull(result);
        verify(testTranslator).create(any(UserAccountDto.class));
    }
}