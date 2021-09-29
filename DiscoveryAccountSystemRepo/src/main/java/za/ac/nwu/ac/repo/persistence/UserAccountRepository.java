package za.ac.nwu.ac.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.nwu.ac.domain.persistence.UserAccount;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
//    @Query(value = "SELECT "+
//            "ua "+
//            "FROM" +
//            "UserAccount ua " +
//            "WHERE " +
//            "ua.member_id = :memberid " +
//            "AND ua.account_type_id = :account_type_id")
//    UserAccount getUserByMemberIDandMnemonic(String memberID, String mnemonic);

    //Query for break/savepoint
    @Query(value = "SAVEPOINT SAVEHERE",nativeQuery = true)
    void createSavePoint();

    //Query for commit
    @Query(value = "COMMIT",nativeQuery = true)
    void commitDB();

    //Query for rollback if exception is caught
    @Query(value = "ROLLBACK TO SAVEPOINT SAVEHERE",nativeQuery = true)
    void rollbackDB();

    @Query(value = "SELECT "+
            "ua "+
            "FROM " +
            "UserAccount ua " +
            "WHERE " +
            "ua.memberID = :memberID " +
            "AND ua.accountTypeID = :accountTypeID")
    UserAccount getUserByMemberIDandAccountTypeID(Long memberID, Long accountTypeID);


    @Modifying
//    @Query(value = "UPDATE " +
//            "UserAccount ua " +
//            "SET ua.ACCOUNT_BALANCE = :ACCOUNT_BALANCE " +
//            "WHERE ua.MEMBER_ID = :MEMBER_ID " +
//            "AND ua.ACCOUNT_TYPE_ID = :ACCOUNT_TYPE_ID")
    @Query(value = "SAVEPOINT SAVEHERE",nativeQuery = true)
    UserAccount updateUserAccount(Integer ACCOUNT_BALANCE, Long MEMBER_ID, Long ACCOUNT_TYPE_ID);
    //Todo: Fix this shit
}

//ToDo: AccountTypeID cannot be called from within UserAccount.
// Need to call from the transaction.
// Need to create rollback point, create transaction,
//      then run update, then commit/rollback