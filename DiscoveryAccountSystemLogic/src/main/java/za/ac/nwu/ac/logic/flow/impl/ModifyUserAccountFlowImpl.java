package za.ac.nwu.ac.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.UserAccountDto;
import za.ac.nwu.ac.logic.flow.ModifyUserAccountFlow;
import za.ac.nwu.ac.translator.UserAccountTranslator;

import javax.transaction.Transactional;


@Component
public class ModifyUserAccountFlowImpl implements ModifyUserAccountFlow {
    private static final Logger LOGGER = LoggerFactory.getLogger(ModifyUserAccountFlowImpl.class);

    private final UserAccountTranslator userAccountTranslator;

    public ModifyUserAccountFlowImpl(UserAccountTranslator userAccountTranslator) {
        this.userAccountTranslator = userAccountTranslator;
    }

    @Transactional
    @Override
    public UserAccountDto subtractCurrencyFromUserAccount(Integer transactionAmount, Long memberID, Long accountTypeID){

        if(transactionAmount>0){
            transactionAmount=transactionAmount * -1;
        }
        LOGGER.info("The UserAccount to Update has input values: " +
                "\n\ttransactionAmount = {}" +
                "\n\tmemberID = {}" +
                "\n\taccountTypeID = {}", transactionAmount,memberID,  accountTypeID);

        Integer oldAccountBalance= 0;
        Integer newAccountBalance= 0;
        try {
            oldAccountBalance= userAccountTranslator.getUserByMemberIDandAccountTypeID(memberID,accountTypeID).getAccountBalance();
            LOGGER.info("The userAccount had a Pre-transaction Balance of {} Miles", oldAccountBalance);
        }catch (Exception e){
            LOGGER.error("The userAccount could not be found due to reason: ", e);
            throw new RuntimeException("UserAccount Could not be found", e);
        }
        if(transactionAmount + oldAccountBalance >=0){
            LOGGER.info("Transaction is Valid - Subtracting less than current AccountValue");
            newAccountBalance = transactionAmount + oldAccountBalance;
            LOGGER.info("The userAccount had a Post-transaction Balance of {} Miles", newAccountBalance);
            UserAccountDto result =userAccountTranslator.updateUserAccount(newAccountBalance, memberID, accountTypeID);
            LOGGER.info("The UserAccount was updated and has return object {}",result);
            return result;
        }else{
            LOGGER.warn("Transaction is not valid - Attempting to Subtract more than current AccountValue");
            throw new RuntimeException("Unable to Update the database");
        }
    }
    @Transactional
    @Override
    public UserAccountDto subtractMilesFromUserAccount(Integer transactionAmount, Long memberID){
        Long accountTypeID = 1000000001L; //Miles Account Type ID
        if(transactionAmount>0){
            transactionAmount=transactionAmount * -1;
        }
        LOGGER.info("The UserAccount to Update has input values: " +
                "\n\ttransactionAmount = {}" +
                "\n\tmemberID = {}" +
                "\n\taccountTypeID = {}", transactionAmount, memberID,  accountTypeID);

        Integer oldAccountBalance= 0;
        Integer newAccountBalance= 0;
        try {
            oldAccountBalance= userAccountTranslator.getUserByMemberIDandAccountTypeID(memberID,accountTypeID).getAccountBalance();
            LOGGER.info("The userAccount had a Pre-transaction Balance of {} Miles", oldAccountBalance);
        }catch (Exception e){
            LOGGER.error("The userAccount could not be found due to reason: ", e);
            throw new RuntimeException("UserAccount Could not be found", e);
        }

        if(transactionAmount + oldAccountBalance >=0){
            LOGGER.info("Transaction is Valid - Subtracting less than current AccountValue");
            newAccountBalance = transactionAmount + oldAccountBalance;
            LOGGER.info("The userAccount had a Post-transaction Balance of {} Miles", newAccountBalance);
            UserAccountDto result =userAccountTranslator.updateUserAccount(newAccountBalance, memberID, accountTypeID);
            LOGGER.info("The UserAccount was updated and has return object {}",result);
            return result;
        }else{
            LOGGER.warn("Transaction is not valid - Attempting to Subtract more than current AccountValue");
            throw new RuntimeException("Unable to Update the database");
        }
    }
    @Transactional
    @Override
    public UserAccountDto addCurrencytoUserAccount(Integer transactionAmount, Long memberID, Long accountTypeID){

        if(transactionAmount<0){
            transactionAmount=transactionAmount * -1;
        }

        LOGGER.info("The UserAccount to Update has input values: " +
                "\n\ttransactionAmount = {}" +
                "\n\tmemberID = {}" +
                "\n\taccountTypeID = {}", transactionAmount,memberID,  accountTypeID);

        Integer oldAccountBalance= 0;
        Integer newAccountBalance= 0;
        try {
            oldAccountBalance= userAccountTranslator.getUserByMemberIDandAccountTypeID(memberID,accountTypeID).getAccountBalance();
            LOGGER.info("The userAccount had a Pre-transaction Balance of {} Miles", oldAccountBalance);
        }catch (Exception e){
            LOGGER.error("The userAccount could not be found due to reason: ", e);
            throw new RuntimeException("UserAccount Could not be found", e);
        }
        newAccountBalance = transactionAmount + oldAccountBalance;
        LOGGER.info("The userAccount had a Post-transaction Balance of {} Miles", newAccountBalance);

        UserAccountDto result =userAccountTranslator.updateUserAccount(newAccountBalance, memberID, accountTypeID);
        LOGGER.info("The UserAccount was updated and has return object {}",result);
        return result;
    }

    @Transactional
    @Override
    public UserAccountDto addMilestoUserAccount(Integer transactionAmount, Long memberID){
        Long accountTypeID = 1000000001L; //Miles Account Type ID
        if(transactionAmount<0){
            transactionAmount=transactionAmount * -1;
        }

        LOGGER.info("The UserAccount to Update has input values: " +
                "\n\ttransactionAmount = {}" +
                "\n\tmemberID = {}" +
                "\n\taccountTypeID = {}", transactionAmount,memberID,  accountTypeID);

        Integer oldAccountBalance= 0;
        Integer newAccountBalance= 0;

        try {
            oldAccountBalance= userAccountTranslator.getUserByMemberIDandAccountTypeID(memberID,accountTypeID).getAccountBalance();
            LOGGER.info("The userAccount had a Pre-transaction Balance of {} Miles", oldAccountBalance);
        }catch (Exception e){
            LOGGER.error("The userAccount could not be found due to reason: ", e);
            throw new RuntimeException("UserAccount Could not be found", e);
        }
        newAccountBalance = transactionAmount + oldAccountBalance;
        LOGGER.info("The userAccount had a Post-transaction Balance of {} Miles", newAccountBalance);

        UserAccountDto result =userAccountTranslator.updateUserAccount(newAccountBalance, memberID, accountTypeID);
        LOGGER.info("The UserAccount was updated and has return object {}",result);
        return result;

    }
}
