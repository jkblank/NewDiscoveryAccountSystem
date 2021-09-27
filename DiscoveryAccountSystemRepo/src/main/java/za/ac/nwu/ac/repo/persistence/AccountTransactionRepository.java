package za.ac.nwu.ac.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.nwu.ac.domain.persistence.AccountTransaction;

@Repository
public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Long> {

    //Query for break/savepoint
    @Query(value = "SAVEPOINT SAVEHERE",nativeQuery = true)
    void createSavePoint();

    //Query for commit
    @Query(value = "COMMIT",nativeQuery = true)
    void commitDB();

    //Query for rollback if exception is caught
    @Query(value = "ROLLBACK TO SAVEPOINT SAVEHERE",nativeQuery = true)
    void rollbackDB();
}
