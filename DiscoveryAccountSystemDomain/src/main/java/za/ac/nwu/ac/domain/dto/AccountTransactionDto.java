package za.ac.nwu.ac.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import za.ac.nwu.ac.domain.persistence.AccountTransaction;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
@ApiModel(value = "AccountTransaction",
            description = "This DTO Represents a transaction record")

public class AccountTransactionDto implements Serializable {

    private static final long serialVersionUID = -7819344808062462808L;

    private Long memberID;
    //private String accountTypeMnemonic;
    private Long accountTypeID;
    private Integer amount;
    private LocalDateTime txDateTime;

    public AccountTransactionDto() {
    }

    public AccountTransactionDto(Long memberID, Long accountTypeID, Integer amount) {
        this.memberID = memberID;
        //this.accountTypeMnemonic = accountTypeMnemonic;
        this.accountTypeID = accountTypeID;
        this.amount = amount;
        this.txDateTime = txDateTime;
    }

    public AccountTransactionDto(AccountTransaction accountTransaction) {
        this.setMemberID(accountTransaction.getMemberID());
        this.setAccountTypeID(accountTransaction.getAccountTypeID());
        this.setAmount(accountTransaction.getAmount());
        this.setTxDateTime(accountTransaction.getTransactionDateTime());
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
        value = "AccountTransaction AccountType ID",
        name = "Account Type ID",
        notes = "Uniquely identifies the account type of the account that made The transaction",
        dataType = "java.lang.Long",
        example = "100000000000003",
        //allowEmptyValue = false,
        required = true)

    public long getAccountTypeID() {
        return accountTypeID;
    }

    public void setAccountTypeID(long accountTypeID) {
        this.accountTypeID = accountTypeID;
    }
//    public String getAccountTypeMnemonic() {
//        return accountTypeMnemonic;
//    }
//
//    public void setAccountTypeMnemonic(String accountTypeMnemonic) {
//        this.accountTypeMnemonic = accountTypeMnemonic;
//    }

@ApiModelProperty(position = 3,
        value = "AccountTransaction TX_Amount",
        name = "AccountTransaction Value",
        notes = "Contains the value of the transaction in the selected currency. Positive/Negative",
        dataType = "java.lang.Long",
        example = "200",
        //allowEmptyValue = false,
        required = true)
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
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
    public LocalDateTime getTxDateTime() {
        return txDateTime;
    }

    public void setTxDateTime(LocalDateTime txDateTime) {
        this.txDateTime = txDateTime;
    }

    @JsonIgnore
    public AccountTransaction getAccountTransaction(){
        return new AccountTransaction(getMemberID(), getAccountTypeID(), getAmount(), getTxDateTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountTransactionDto that = (AccountTransactionDto) o;
        return accountTypeID == that.accountTypeID && Objects.equals(memberID, that.memberID) && Objects.equals(amount, that.amount) && Objects.equals(txDateTime, that.txDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberID, accountTypeID, amount, txDateTime);
    }

    @Override
    public String toString() {
        return "AccountTransactionDto{" +
                "memberID=" + memberID +
                ", accountTypeID=" + accountTypeID +
                ", amount=" + amount +
                ", txDateTime=" + txDateTime +
                '}';
    }


}
