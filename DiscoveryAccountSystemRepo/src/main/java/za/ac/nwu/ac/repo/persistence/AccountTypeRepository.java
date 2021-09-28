package za.ac.nwu.ac.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.persistence.AccountType;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {
    @Query(value = "Select " +
    "           ACCOUNT_TYPE_ID,"+
    "           ACCOUNT_TYPE_NAME, "+
    "           MNEMONIC, "+
    "           CREATION_DATE"+
    " FROM " +
    "DISCOVERY.ACCOUNT_TYPE "+
    "WHERE MNEMONIC = :mnemonic", nativeQuery = true) //not recommended
    AccountType getAccountTypeByMnemonicNativeQuery(String mnemonic);

    @Query(value = "SELECT"+
    "           at "+
    "           FROM "+
    "           AccountType at "+
    "           WHERE at.mnemonic = :mnemonic ")
    AccountType getAccountTypeByMnemonic(String mnemonic);

    //recommended for use with constructing full DTO
    //putting values into the constructor of DTO
    @Query(value = "SELECT new za.ac.nwu.ac.domain.dto.AccountTypeDto( "+
        "       at.mnemonic, "+
        "       at.accountTypeName," +
        "       at.creationDate )" +
    "       FROM "+
        "       AccountType at "+
        "   WHERE at.mnemonic= :mnemonic")
    AccountTypeDto getAccountTypeDTOByMnemonic(String mnemonic);

//Query for break/savepoint
    @Query(value = "SAVEPOINT SAVEHERE",nativeQuery = true)
    void createSavePoint();

//Query for commit
    @Query(value = "COMMIT",nativeQuery = true)
    void commitDB();

//Query for rollback if exception is caught
    @Query(value = "ROLLBACK TO SAVEPOINT SAVEHERE",nativeQuery = true)
    void rollbackDB();

//    @Query(value = "SELECT"+
//            "           at.account_type_id "+
//            "           FROM "+
//            "           AccountType at"+
//            "           WHERE at.mnemonic = :mnemonic ")
//    String getAccountTypeAccountTypeIDByMnemonic(String accountTypeId);
}
