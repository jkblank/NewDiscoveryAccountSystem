package za.ac.nwu.ac.logic.flow.impl;

import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.UserAccountDto;
import za.ac.nwu.ac.logic.flow.CreateUserAccountFlow;
import za.ac.nwu.ac.translator.UserAccountTranslator;

import javax.transaction.Transactional;

@Transactional
@Component
public class CreateUserAccountFlowImpl implements CreateUserAccountFlow {

    private final UserAccountTranslator userAccountTranslator;

    public CreateUserAccountFlowImpl(UserAccountTranslator userAccountTranslator) {
        this.userAccountTranslator = userAccountTranslator;
    }

    @Override
    public UserAccountDto create(UserAccountDto userAccount) {
        return userAccountTranslator.create(userAccount);
    }
}
