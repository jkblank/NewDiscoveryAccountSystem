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
        oldAccountBalance= userAccountTranslator.getUserByMemberIDandAccountTypeID(memberID,accountTypeID).getAccountBalance();

        if(transactionAmount + oldAccountBalance >=0){
            LOGGER.info("Transaction is Valid - Subtracting less than current AccountValue");
            newAccountBalance = transactionAmount + oldAccountBalance;
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
        oldAccountBalance= userAccountTranslator.getUserByMemberIDandAccountTypeID(memberID,accountTypeID).getAccountBalance();

        newAccountBalance = transactionAmount + oldAccountBalance;
        UserAccountDto result =userAccountTranslator.updateUserAccount(newAccountBalance, memberID, accountTypeID);
        LOGGER.info("The UserAccount was updated and has return object {}",result);
        return result;

    }
}
