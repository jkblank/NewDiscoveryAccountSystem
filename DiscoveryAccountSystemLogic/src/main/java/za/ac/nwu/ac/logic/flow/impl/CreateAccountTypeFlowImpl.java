package za.ac.nwu.ac.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.logic.flow.CreateAccountTypeFlow;
import za.ac.nwu.ac.translator.AccountTypeTranslator;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Transactional
@Component("createAccountTypeFlowName") //can inject using this name in a qualifier field
public class CreateAccountTypeFlowImpl implements CreateAccountTypeFlow {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateAccountTypeFlowImpl.class);

    private final AccountTypeTranslator accountTypeTranslator;

    //@Autowired
    public CreateAccountTypeFlowImpl(AccountTypeTranslator accountTypeTranslator) {
        this.accountTypeTranslator = accountTypeTranslator;
    }

    @Override
    public AccountTypeDto create(AccountTypeDto accountType){
        LOGGER.info("The new AccountType input object was {}.", accountType);

        if(null==accountType.getCreationDate()){
            accountType.setCreationDate(LocalDate.now());
            LOGGER.info("AccountType input Creation Date is NULL, setting to {}", LocalDate.now());
        }
        AccountTypeDto result = accountTypeTranslator.create(accountType);
        LOGGER.info("The new AccountType return object is {}.", result);
        return result;
    }
}
