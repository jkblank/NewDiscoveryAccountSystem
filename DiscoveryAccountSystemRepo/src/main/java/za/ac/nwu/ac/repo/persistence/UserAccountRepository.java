package za.ac.nwu.ac.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
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
}
//TODO: query for break/savepoint
//ToDO: Query for commit
//ToDo:Query for rollback if exception is caught