package za.ac.nwu.ac.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
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
    UserAccount getUserByMemberIDandMnemonic(Long memberID, Long accountTypeID);

}
