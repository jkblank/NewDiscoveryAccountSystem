package za.ac.nwu.ac.logic.flow.impl;

import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.UserAccountDto;
import za.ac.nwu.ac.logic.flow.ModifyUserAccountFlow;
import za.ac.nwu.ac.translator.UserAccountTranslator;

import javax.transaction.Transactional;

@Transactional
@Component
public class ModifyUserAccountFlowImpl implements ModifyUserAccountFlow {
    private final UserAccountTranslator userAccountTranslator;

    public ModifyUserAccountFlowImpl(UserAccountTranslator userAccountTranslator) {
        this.userAccountTranslator = userAccountTranslator;
    }

    @Override
    public UserAccountDto updateUserAccount(long memberID, long accountTypeID, int transactionAmount){
        return userAccountTranslator.updateUserAccount(memberID, accountTypeID, transactionAmount);
    }

}
