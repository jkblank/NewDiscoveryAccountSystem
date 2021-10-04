package za.ac.nwu.ac.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.UserAccountDto;
import za.ac.nwu.ac.logic.flow.CreateUserAccountFlow;
import za.ac.nwu.ac.translator.UserAccountTranslator;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Transactional
@Component
public class CreateUserAccountFlowImpl implements CreateUserAccountFlow {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateUserAccountFlowImpl.class);

    private final UserAccountTranslator userAccountTranslator;

    public CreateUserAccountFlowImpl(UserAccountTranslator userAccountTranslator) {
        this.userAccountTranslator = userAccountTranslator;
    }

    @Override
    public UserAccountDto create(UserAccountDto userAccount) {
        LOGGER.info("The new UserAccount input object was {}.", userAccount);
        if(null==userAccount.getCreationDate()){
            LocalDate now = LocalDate.now();
            userAccount.setCreationDate(now);
            LOGGER.info("UserAccount input Creation Date is NULL, setting to {}", LocalDate.now());
        }
        UserAccountDto result = userAccountTranslator.create(userAccount);
        LOGGER.info("The new UserAccount return object is {}.", result);
        return result;
    }
}
