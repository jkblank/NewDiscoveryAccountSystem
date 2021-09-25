package za.ac.nwu.ac.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import za.ac.nwu.ac.domain.persistence.AccountTransaction;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
@ApiModel(value = "AccountTransaction",
            description = "This DTO Represents a transaction record")

public class AccountTransactionDto implements Serializable {

    private static final long serialVersionUID = -7819344808062462808L;

    private Long memberID;
    private String accountTypeMnemonic;
    private Long amount;
    private LocalDate txDate;

    public AccountTransactionDto() {
    }

    public AccountTransactionDto(Long memberID, String accountTypeMnemonic, Long amount, LocalDate txDate) {
        this.memberID = memberID;
        this.accountTypeMnemonic = accountTypeMnemonic;
        this.amount = amount;
        this.txDate = txDate;
    }

    public AccountTransactionDto(AccountTransaction accountTransaction) {
        this.setMemberID(accountTransaction.getMemberID());
        this.setAccountTypeMnemonic(accountTransaction.getAccountType().getMnemonic());
        this.setAmount(accountTransaction.getAmount());
        this.setTxDate(accountTransaction.getTransactionDate());
    }
@ApiModelProperty(position = 1,
        value = "AccountTransaction Member ID",
        name = "Member ID",
        notes = "Uniquely identifies the Member who's account Made The transaction",
        dataType = "java.lang.String",
        example = "100000000000001",
        //allowEmptyValue = false,
        required = true)
    public Long getMemberID() {
        return memberID;
    }

    public void setMemberID(Long memberID) {
        this.memberID = memberID;
    }

@ApiModelProperty(position = 2,
        value = "AccountTransaction AccountType Name",
        name = "Account Type Name",
        notes = "Uniquely identifies the account type of the account that made The transaction",
        dataType = "java.lang.String",
        example = "Miles",
        //allowEmptyValue = false,
        required = true)
    public String getAccountTypeMnemonic() {
        return accountTypeMnemonic;
    }

    public void setAccountTypeMnemonic(String accountTypeMnemonic) {
        this.accountTypeMnemonic = accountTypeMnemonic;
    }

@ApiModelProperty(position = 3,
        value = "AccountTransaction TX_Amount",
        name = "AccountTransaction Value",
        notes = "Contains the value of the transaction in the selected currency. Positive/Negative",
        dataType = "java.lang.Long",
        example = "200",
        //allowEmptyValue = false,
        required = true)
    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

@ApiModelProperty(position = 4,
        value = "AccountTransaction Transaction Date",
        name = "AccountTransaction Date",
        notes = "Shows the date and time of the transaction",
        dataType = "java.lang.String",
        example = "14-09-2021",
        //allowEmptyValue = false,
        required = true)
    public LocalDate getTxDate() {
        return txDate;
    }

    public void setTxDate(LocalDate txDate) {
        this.txDate = txDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountTransactionDto that = (AccountTransactionDto) o;
        return Objects.equals(memberID, that.memberID) && Objects.equals(accountTypeMnemonic, that.accountTypeMnemonic) && Objects.equals(amount, that.amount) && Objects.equals(txDate, that.txDate);
    }

    @JsonIgnore
    public AccountTransaction getAccountTransaction(){
        return new AccountTransaction(getMemberID(), getAccountTypeMnemonic(), getAmount(), getTxDate());
    }
    @Override
    public int hashCode() {
        return Objects.hash(memberID, accountTypeMnemonic, amount, txDate);
    }

    @Override
    public String toString() {
        return "AccountTransactionDto{" +
                "memberID='" + memberID + '\'' +
                ", accountTypeName='" + accountTypeMnemonic + '\'' +
                ", amount=" + amount +
                ", txDate=" + txDate +
                '}';
    }
}
