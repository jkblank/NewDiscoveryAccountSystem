package za.ac.nwu.ac.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.logic.flow.FetchAccountTypeFlow;
import za.ac.nwu.ac.translator.AccountTypeTranslator;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Component
public class FetchAccountTypeFlowImpl implements FetchAccountTypeFlow {
    private final AccountTypeTranslator accountTypeTranslator;
    private static final Logger LOGGER = LoggerFactory.getLogger(FetchAccountTypeFlowImpl.class);


    @Autowired
    public FetchAccountTypeFlowImpl(AccountTypeTranslator accountTypeTranslator){
        this.accountTypeTranslator = accountTypeTranslator;
    }

    @Override
    public List<AccountTypeDto> getAllAccountTypes() {
        LOGGER.info("Fetching all Account Types");

        List<AccountTypeDto> result = accountTypeTranslator.getAllAccountTypes();
        LOGGER.info("The following Account Types are returned:\n{}",result);
        return result;
    }

    @Override
    public AccountTypeDto getAccountTypeByMnemonic(String mnemonic) {
        LOGGER.info("Fetching all Account Types that match Mnemonic {}", mnemonic);

        AccountTypeDto result = accountTypeTranslator.getAccountTypeByMnemonic(mnemonic);
        LOGGER.info("The AccountType return object is {}.", result);
        return result;
    }


}
