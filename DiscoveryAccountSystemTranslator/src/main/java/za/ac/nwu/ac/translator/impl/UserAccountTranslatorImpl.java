package za.ac.nwu.ac.translator.impl;

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
            UserAccount userAccount= userAccountRepository.save(userAccountDto.getUserAccount());
            return new UserAccountDto(userAccount);
        }catch (Exception e){
            throw  new RuntimeException("Unable to save to the DB", e);
        }
    }

    //ToDo: Fix UpdateUserAccount
    //ToDo: AccountTypeID cannot be called from within UserAccount.!!
    // Need to call from the transaction.
    // Need to create rollback point, create transaction,
    //      then run update, then commit/rollback

    @Override
    @Transactional
    public UserAccountDto updateUserAccount(Integer TransactionAmount, Long memberID, Long accountTypeID) {

        try{
            Integer oldAccountBalance= 0;
            Integer newAccountBalance= 0;

            //ToDo: check if current value  is more than substract value
            oldAccountBalance= getUserByMemberIDandAccountTypeID(memberID,accountTypeID).getAccountBalance();
            //ToDO: Move to Logic
            if( TransactionAmount + oldAccountBalance >=0 ){
                newAccountBalance = TransactionAmount + oldAccountBalance;//ToDO: Move to Logic
                UserAccount userAccount =new UserAccount(memberID, accountTypeID,newAccountBalance);
                userAccountRepository.updateUserAccount(newAccountBalance, memberID, accountTypeID);
                return new UserAccountDto(userAccount);
            }else{
                //ToDo some catch or some shit
                throw new RuntimeException("Cannot Subtract more currency that you own!");
            }
        }catch (Exception e){
            throw new RuntimeException("Unable to update DB", e);
        }


    }

    //ToDo: Create GetUserByMemberIDandAccountTypeID

    @Override
    public UserAccountDto getUserByMemberIDandAccountTypeID(Long memberID, Long accountTypeID) {
        try{
            UserAccount userAccount=userAccountRepository.getUserByMemberIDandAccountTypeID(memberID, accountTypeID);
            return new UserAccountDto(userAccount);
        }catch (Exception e){
            throw new RuntimeException("Unable to read from the DB", e);
        }

    }

}
