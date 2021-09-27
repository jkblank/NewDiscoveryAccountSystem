package za.ac.nwu.ac.logic.flow.impl;

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


    public CreateTransactionFlowImpl(AccountTransactionTranslator accountTransactionTranslator) {
        this.accountTransactionTranslator = accountTransactionTranslator;
    }

    @Override
    public AccountTransactionDto create(AccountTransactionDto accountTransaction){
        if(null==accountTransaction.getTxDateTime()){
            accountTransaction.setTxDateTime(LocalDateTime.now());
        }
        return accountTransactionTranslator.create(accountTransaction);
    }

}
