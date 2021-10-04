package za.ac.nwu.ac.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.AccountTransactionDto;
import za.ac.nwu.ac.logic.flow.CreateTransactionFlow;
import za.ac.nwu.ac.translator.AccountTransactionTranslator;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Transactional
@Component("createAccountTransactionName")
public class CreateTransactionFlowImpl implements CreateTransactionFlow {
    private final AccountTransactionTranslator accountTransactionTranslator;

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateTransactionFlowImpl.class);

    public CreateTransactionFlowImpl(AccountTransactionTranslator accountTransactionTranslator) {
        this.accountTransactionTranslator = accountTransactionTranslator;
    }

    @Override
    public AccountTransactionDto create(AccountTransactionDto accountTransaction){
        LOGGER.info("The new AccountTransaction input object is {}.", accountTransaction);
        if(null==accountTransaction.getTxDateTime()){
            LocalDateTime now = LocalDateTime.now();
            accountTransaction.setTxDateTime(now);
            LOGGER.info("AccountTransaction input Transaction DateTime is Null, setting to {}", now);
        }
        AccountTransactionDto result = accountTransactionTranslator.create(accountTransaction);
        LOGGER.info("The new AccountTransaction return object is {}.",result);
        return result;
    }

}
