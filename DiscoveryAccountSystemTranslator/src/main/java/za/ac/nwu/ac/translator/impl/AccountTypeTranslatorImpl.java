package za.ac.nwu.ac.translator.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.persistence.AccountType;
import za.ac.nwu.ac.repo.persistence.AccountTypeRepository;
import za.ac.nwu.ac.translator.AccountTypeTranslator;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class AccountTypeTranslatorImpl implements AccountTypeTranslator {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountTypeTranslatorImpl.class);
    private final AccountTypeRepository accountTypeRepository;

    @Autowired
    public AccountTypeTranslatorImpl(AccountTypeRepository accountTypeRepository){
        this.accountTypeRepository=accountTypeRepository;
    }

    @Transactional
    @Override
    public List<AccountTypeDto> getAllAccountTypes(){
        LOGGER.info("Fetch of all Account Types Started");
        List<AccountTypeDto> accountTypeDtos = new ArrayList<>();
        try{
            LOGGER.info("Attempting to fetch all Account Types");
            for (AccountType accountType : accountTypeRepository.findAll()){
                accountTypeDtos.add(new AccountTypeDto(accountType));
            }
            List<AccountTypeDto> result = accountTypeDtos;
            LOGGER.info("Fetch of all Account Types Successful, results {}", result);
            return  result;
        }catch (Exception e){
            throw new RuntimeException( "Unable to read from DB", e);
            //TODO: Create new Exception Type that implements RuntimeException in Domain

        }
    }
    @Transactional
    @Override   //TODO: Continue Logging Errors
    public AccountTypeDto create(AccountTypeDto accountTypeDto){
        try{
            AccountType accountType = accountTypeRepository.save(accountTypeDto.getAccountType());
            return new AccountTypeDto(accountType);
        }catch (Exception e){
            throw  new RuntimeException("Unable to save to the DB", e);
        }
    }

    @Override
    public AccountTypeDto getAccountTypeByMnemonic(String mnemonic) {
        try{
            AccountType accountType = accountTypeRepository.getAccountTypeByMnemonic(mnemonic);
            return new AccountTypeDto(accountType);
        }catch (Exception e) {
            throw new RuntimeException("Unable to read from the DB", e);
        }
    }

    @Override
    @Transactional
    public AccountTypeDto deleteAccountTypeByMnemonic(String mnemonic) {
        try{
            AccountType accountType = accountTypeRepository.getAccountTypeByMnemonic(mnemonic);
            accountTypeRepository.deleteAccountTypeByMnemonic(mnemonic);
            return new AccountTypeDto(accountType);
        }catch (Exception e){
            throw new RuntimeException("Unable to read from the DB ", e);
        }
    }

    @Override
    @Transactional
    public AccountTypeDto updateAccountType(AccountTypeDto accountType) {
        try{
            accountTypeRepository.updateAccountType(accountType.getMnemonic(), accountType.getAccountTypeName(),
                    accountType.getCreationDate());
            return accountType;
        }catch (Exception e){
            throw new RuntimeException( "Unable to update the DB ", e);
        }
    }

}
