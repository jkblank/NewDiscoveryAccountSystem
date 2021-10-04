package za.ac.nwu.ac.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.logic.flow.ModifyAccountTypeFlow;
import za.ac.nwu.ac.translator.AccountTypeTranslator;

import javax.transaction.Transactional;

@Transactional
@Component
public class ModifyAccountTypeFlowImpl implements ModifyAccountTypeFlow {
    private static final Logger LOGGER = LoggerFactory.getLogger(ModifyAccountTypeFlowImpl.class);


    private final AccountTypeTranslator accountTypeTranslator;

    public ModifyAccountTypeFlowImpl(AccountTypeTranslator accountTypeTranslator) {
        this.accountTypeTranslator = accountTypeTranslator;
    }

    @Override
    public AccountTypeDto deleteAccountTypeByMnemonic(String mnemonic) {
        LOGGER.info("The AccountType to be deleted has mnemonic {}", mnemonic);

        AccountTypeDto result =accountTypeTranslator.deleteAccountTypeByMnemonic(mnemonic);
        LOGGER.info("The following Account Type was deleted {}", result);
        return result;
    }

    @Override
    public AccountTypeDto updateAccountType(AccountTypeDto accountType) {

        LOGGER.info("The AccountType to Update has input object {}", accountType);

        AccountTypeDto result =accountTypeTranslator.updateAccountType(accountType);
        LOGGER.info("The AccountType was updated and has output object {}", result);
        return result;
    }
}
