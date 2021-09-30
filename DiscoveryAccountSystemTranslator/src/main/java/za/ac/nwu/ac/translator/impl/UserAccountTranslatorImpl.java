package za.ac.nwu.ac.translator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.AccountTransactionDto;
import za.ac.nwu.ac.domain.dto.UserAccountDto;
import za.ac.nwu.ac.domain.persistence.UserAccount;
import za.ac.nwu.ac.repo.persistence.UserAccountRepository;
import za.ac.nwu.ac.translator.AccountTransactionTranslator;
import za.ac.nwu.ac.translator.UserAccountTranslator;


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
//            userAccountRepository.createSavePoint();
            UserAccount userAccount= userAccountRepository.save(userAccountDto.getUserAccount());
//            userAccountRepository.commitDB();
            return new UserAccountDto(userAccount);
        }catch (Exception e){
//            userAccountRepository.rollbackDB();
            throw  new RuntimeException("Unable to save to the DB", e);
        }
    }

    //ToDo: Fix UpdateUserAccount
    //ToDo: AccountTypeID cannot be called from within UserAccount.!!
    // Need to call from the transaction.
    // Need to create rollback point, create transaction,
    //      then run update, then commit/rollback

    @Override
    public UserAccountDto updateUserAccount(Integer TransactionAmount, Long memberID, Long accountTypeID) {

        try{

            Integer oldAccountBalance= new Integer(0);
            Integer newAccountBalance= new Integer(0);

            AccountTransactionDto accountTransaction = accountTransactionTranslator.create(new AccountTransactionDto(memberID, accountTypeID, TransactionAmount));

            //accountTransaction.getAmount();
            oldAccountBalance= getUserByMemberIDandAccountTypeID(memberID,accountTypeID).getAccountBalance();
            //ToDo: check if current value  is more than substract value
            //get current account val and add transaction value and
            if( (Math.abs(TransactionAmount) <= oldAccountBalance)){

                newAccountBalance = TransactionAmount + oldAccountBalance;
            }else{
                //ToDo some catch or some shit
            }
            // then pass to updateUserAccount(long memberID, long accountTypeID, int newAccountBalance)
            UserAccount userAccount = userAccountRepository.updateUserAccount(newAccountBalance, memberID, accountTypeID);
            return new UserAccountDto(userAccount);
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

//    @Override
//    public UserAccountDto getUserByMemberIDandMnemonic(String memberID, String mnemonic){
//        try{
//            UserAccount userAccount = userAccountRepository.getUserByMemberIDandMnemonic(memberID, mnemonic);
//            return new UserAccountDto(userAccount);
//        }catch ( Exception e){
//            throw new RuntimeException("Unable to read from the DB", e);
//            //ToDo: New exception to be implemented here
//        }
//    }
}
