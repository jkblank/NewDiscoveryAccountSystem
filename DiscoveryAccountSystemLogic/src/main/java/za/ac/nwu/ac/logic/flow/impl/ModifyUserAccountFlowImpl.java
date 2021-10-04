package za.ac.nwu.ac.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.UserAccountDto;
import za.ac.nwu.ac.logic.flow.ModifyUserAccountFlow;
import za.ac.nwu.ac.translator.UserAccountTranslator;

import javax.transaction.Transactional;

@Transactional
@Component
public class ModifyUserAccountFlowImpl implements ModifyUserAccountFlow {
    private static final Logger LOGGER = LoggerFactory.getLogger(ModifyUserAccountFlowImpl.class);

    private final UserAccountTranslator userAccountTranslator;

    public ModifyUserAccountFlowImpl(UserAccountTranslator userAccountTranslator) {
        this.userAccountTranslator = userAccountTranslator;
    }

    @Override
    public UserAccountDto updateUserAccount(Integer transactionAmount, Long memberID, Long accountTypeID){
        LOGGER.info("The UserAccount to Update has input values: " +
                "\ntransactionAmount {}" +
                "\nmemberID {}" +
                "\naccountTypeID {}", transactionAmount,memberID,  accountTypeID);

        UserAccountDto result =userAccountTranslator.updateUserAccount(transactionAmount, memberID, accountTypeID);
        LOGGER.info("The UserAccount was updated and has return object {}",result);
        return result;
    }

}
