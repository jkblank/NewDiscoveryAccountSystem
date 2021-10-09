package za.ac.nwu.ac.translator.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.UserAccountDto;
import za.ac.nwu.ac.domain.persistence.UserAccount;
import za.ac.nwu.ac.repo.persistence.UserAccountRepository;
import za.ac.nwu.ac.translator.AccountTransactionTranslator;
import za.ac.nwu.ac.translator.UserAccountTranslator;

import javax.transaction.Transactional;


@Component
public class UserAccountTranslatorImpl implements UserAccountTranslator {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserAccountTranslatorImpl.class);

    private final UserAccountRepository userAccountRepository;
    private final AccountTransactionTranslator accountTransactionTranslator;

    @Autowired
    public UserAccountTranslatorImpl(UserAccountRepository userAccountRepository, AccountTransactionTranslator accountTransactionTranslator) {
        this.userAccountRepository = userAccountRepository;
        this.accountTransactionTranslator = accountTransactionTranslator;
    }

    @Override
    public UserAccountDto create(UserAccountDto userAccountDto) {
        try{
            LOGGER.info("Attempting to write new UserAccount to Database with Properties {}", userAccountDto);
            UserAccount userAccount= userAccountRepository.save(userAccountDto.getUserAccount());
            LOGGER.info("UserAccountSuccessfully Created");
            return new UserAccountDto(userAccount);
        }catch (Exception e){
            LOGGER.error("Creation of new UserAccount failed with reason: ", e);
            throw  new RuntimeException("Unable to save to the DB", e);
        }
    }

    @Override
    @Transactional
    public UserAccountDto updateUserAccount(Integer newAccountBalance, Long memberID, Long accountTypeID) {

        try{
            LOGGER.info("Attempting to update UserAccount with Input data: " +
                    "\nMemberID: {}" +
                    "\nAccountTypeID: {}" +
                    "\nNew Account Balance: {}",
                    memberID, accountTypeID, newAccountBalance);
            UserAccount userAccount =new UserAccount(memberID, accountTypeID,newAccountBalance);
            userAccountRepository.updateUserAccount(newAccountBalance, memberID, accountTypeID);
            LOGGER.info("Successfully updated UserAccount");
            return new UserAccountDto(userAccount);
        }catch (Exception e){
            LOGGER.error("Update of UserAccount Failed with reason",e);
            throw new RuntimeException("Unable to update DB", e);
        }
    }

    @Override
    public UserAccountDto getUserByMemberIDandAccountTypeID(Long memberID, Long accountTypeID) {
        try{
            LOGGER.info("Attempting to Find UserAccount with Properties:" +
                    "\nMemberID: {}" +
                    "\nAccountTypeID: {}",
                    memberID, accountTypeID);
            UserAccount userAccount=userAccountRepository.getUserByMemberIDandAccountTypeID(memberID, accountTypeID);
            LOGGER.info("User Found!\nHas properties: {}", userAccount);
            return new UserAccountDto(userAccount);
        }catch (Exception e){
            LOGGER.error("Failed to find User because of reason: ",e);
            throw new RuntimeException("Unable to read from the DB", e);
        }

    }

    @Override
    public UserAccountDto getUserMilesAccount(Long memberID){
        Long accountTypeID = 100000000000003L; //Miles Account Type ID
        try{
            LOGGER.info("Attempting to Find UserAccount with Properties:" +
                            "\nMemberID: {}" +
                            "\nAccountTypeID: {}",
                    memberID, accountTypeID);
            UserAccount userAccount=userAccountRepository.getUserByMemberIDandAccountTypeID(memberID, accountTypeID);
            LOGGER.info("User Found!\nHas properties: {}", userAccount);
            return new UserAccountDto(userAccount);
        }catch (Exception e){
            LOGGER.error("Failed to find User because of reason: ",e);
            throw new RuntimeException("Unable to read from the DB", e);
        }

    }

}
