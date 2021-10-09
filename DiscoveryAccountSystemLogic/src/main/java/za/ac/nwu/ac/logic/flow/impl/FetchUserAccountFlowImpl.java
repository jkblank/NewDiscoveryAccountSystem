package za.ac.nwu.ac.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.UserAccountDto;
import za.ac.nwu.ac.logic.flow.FetchUserAccountFlow;
import za.ac.nwu.ac.translator.UserAccountTranslator;

import javax.transaction.Transactional;

@Transactional
@Component
public class FetchUserAccountFlowImpl implements FetchUserAccountFlow {
    private static final Logger LOGGER = LoggerFactory.getLogger(FetchUserAccountFlowImpl.class);

    private final UserAccountTranslator userAccountTranslator;

    public FetchUserAccountFlowImpl(UserAccountTranslator userAccountTranslator) {
        this.userAccountTranslator = userAccountTranslator;
    }

    @Override
    public UserAccountDto getUserByMemberIDandAccountID(Long memberID, Long accountTypeID) {
        LOGGER.info("The fetch operation has input values: \n memberID {} \n accountTypeID {}",memberID, accountTypeID);

        UserAccountDto result = userAccountTranslator.getUserByMemberIDandAccountTypeID(memberID, accountTypeID);
        LOGGER.info("The returned UserAccountDto has value {}", result);
        return result;
    }
    @Override
    public UserAccountDto getUserMilesAccount(Long memberID) {
        LOGGER.info("The fetch operation has input values: memberID {}",memberID);

        UserAccountDto result = userAccountTranslator.getUserMilesAccount(memberID);
        LOGGER.info("The returned UserAccountDto has value {}", result);
        return result;
    }


}
