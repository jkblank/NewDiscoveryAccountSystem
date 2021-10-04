package za.ac.nwu.ac.translator.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.AccountTransactionDto;
import za.ac.nwu.ac.domain.persistence.AccountTransaction;
import za.ac.nwu.ac.repo.persistence.AccountTransactionRepository;
import za.ac.nwu.ac.translator.AccountTransactionTranslator;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountTransactionTranslatorImpl implements AccountTransactionTranslator {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountTransactionTranslatorImpl.class);

    private final AccountTransactionRepository accountTransactionRepository;

    @Autowired
    public AccountTransactionTranslatorImpl(AccountTransactionRepository accountTransactionRepository){
        this.accountTransactionRepository=accountTransactionRepository;
    }
    @Override
    public List<AccountTransactionDto> getAllAccountTransactions(){
        LOGGER.info("Request to fetch all Account Transactions");
        List<AccountTransactionDto> accountTransactionDtos = new ArrayList<>();
        try {
            LOGGER.info("Attempting to fetch all Account Transactions");
            for (AccountTransaction accountTransaction : accountTransactionRepository.findAll()) {
                accountTransactionDtos.add(new AccountTransactionDto(accountTransaction));
            }
            LOGGER.info("Account Transactions Successfully fetched. Returning {}",accountTransactionDtos);
            return accountTransactionDtos;
        }catch (Exception e){
                LOGGER.error("Unable to fetch Account Transactions", e);
                throw new RuntimeException("Unable to read from DB", e);
            }
        }

    @Override
    public AccountTransactionDto create(AccountTransactionDto accountTransaction) {
        LOGGER.info("The new Account Transaction input object {}", accountTransaction);
        try{
            LOGGER.info("Attempting to create new Account Transaction");
            AccountTransaction accountTransaction1 = accountTransactionRepository.save(accountTransaction.getAccountTransaction());
            AccountTransactionDto result =new AccountTransactionDto(accountTransaction1);
            LOGGER.info("New Account Transaction Object {} successfully created.", result);
            return result;
        }
        catch(Exception e){
            LOGGER.error("Failed to create new Account Transaction", e);
            throw new RuntimeException("Unable to save to the DB", e);
        }
    }
}

