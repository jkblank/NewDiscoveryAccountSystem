package za.ac.nwu.ac.logic.flow.impl;

import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.UserAccountDto;
import za.ac.nwu.ac.logic.flow.FetchUserAccountFlow;
import za.ac.nwu.ac.translator.UserAccountTranslator;

import javax.transaction.Transactional;

@Transactional
@Component
public class FetchUserAccountFlowImpl implements FetchUserAccountFlow {
    private final UserAccountTranslator userAccountTranslator;

    public FetchUserAccountFlowImpl(UserAccountTranslator userAccountTranslator) {
        this.userAccountTranslator = userAccountTranslator;
    }

    @Override
    public UserAccountDto getUserByMemberIDandAccountID(Long memberID, Long accountTypeID) {
        return userAccountTranslator.getUserByMemberIDandMnemonic(memberID, accountTypeID);
    }


}
