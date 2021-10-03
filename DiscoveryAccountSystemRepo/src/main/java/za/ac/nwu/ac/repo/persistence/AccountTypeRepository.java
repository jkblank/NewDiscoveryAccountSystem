package za.ac.nwu.ac.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.persistence.AccountType;

import java.time.LocalDate;

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


    //ToDo: fix delete
    @Modifying
    @Query(value = "DELETE FROM "+
            "           AccountType at"+
            "           WHERE at.mnemonic = :mnemonic ")
    void deleteAccountTypeByMnemonic( String mnemonic);

//ToDO: fix the updateAccountType function

    @Modifying
    @Query(value = "Update " +
            "AccountType at " +
            "set " +
            "at.accountTypeName = :newAccountTypeName, " +
            "at.creationDate = :newCreationDate " +
            "where " +
            "at.mnemonic = :mnemonic")
    void updateAccountType(String mnemonic, String newAccountTypeName, @Param("newCreationDate") LocalDate newCreationDate);

}
