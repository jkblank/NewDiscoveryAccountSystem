package za.ac.nwu.ac.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import za.ac.nwu.ac.domain.persistence.UserAccount;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;


//TODO: (Optional) add mnemonic search functionality for adding account
//avoid duplicates
@ApiModel(value = "UserAccount",
            description = "This DTO represents the UserAccount")

public class UserAccountDto implements Serializable {

    private static final long serialVersionUID = -6362744561310132459L;

    private Long memberID;
    private Long accountTypeID;
    private Integer accountBalance;
    private LocalDate creationDate;

    public UserAccountDto() {
    }

    public UserAccountDto(Long memberID, Long accountTypeID, Integer accountBalance, LocalDate creationDate) {
        this.memberID = memberID;
        this.accountTypeID = accountTypeID;
        this.accountBalance = accountBalance;
        this.creationDate = creationDate;
    }

    public UserAccountDto(UserAccount userAccount){
        this.memberID = userAccount.getMemberID();
        this.accountTypeID =userAccount.getAccountTypeID();
        this.accountBalance =userAccount.getAccountBalance();
        this.creationDate = userAccount.getCreationDate();
    }

    @ApiModelProperty(
            position = 1,
            value = "UserAccount MemberID",
            name = "MemberID",
            notes = "Unique Member ID for the Member that owns the account",
            dataType = "java.lang.String",
            example ="100000000000001",
            required = true)
    public Long getMemberID() {
        return memberID;
    }

    public void setMemberID(Long memberID) {
        this.memberID = memberID;
    }

    @ApiModelProperty(
            position = 2,
            value = "UserAccount AccountType",
            name = "AccountTypeID",
            notes = "The AccountTypeID for the specific AccountType",
            dataType = "java.lang.String",
            example = "100000000000003",
            required = true
    )
    public Long getAccountTypeID() {
        return accountTypeID;
    }

    public void setAccountTypeID(Long accountTypeID) {
        this.accountTypeID = accountTypeID;
    }

    @ApiModelProperty(
            position = 3,
            value = "UserAccount Account Balance",
            name = "AccountBalance",
            notes = "Contains the amount of a currency that a User " +
                    "has in their account and is allowed to spend.",
            dataType = "java.lang.Integer",
            example = "42069",
            required = true
    )
    public Integer getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Integer accountBalance) {
        this.accountBalance = accountBalance;
    }

    @ApiModelProperty(
            position = 4,
            value = "UserAccount Creation Date",
            name = "The creation date of the UserAccount",
            dataType = "java.lang.String",
            example = "2020-01-01",
            required = true
    )
    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccountDto that = (UserAccountDto) o;
        return Objects.equals(memberID, that.memberID) && Objects.equals(accountTypeID, that.accountTypeID) && Objects.equals(accountBalance, that.accountBalance) && Objects.equals(creationDate, that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberID, accountTypeID, accountBalance, creationDate);
    }

    @JsonIgnore
    public UserAccount getUserAccount(){
        return new UserAccount(getMemberID(), getAccountTypeID(), getAccountBalance(), getCreationDate());
    }

    @Override
    public java.lang.String toString() {
        return "UserAccountDto{" +
                "memberID=" + memberID +
                ", accountTypeID=" + accountTypeID +
                ", accountBalance=" + accountBalance +
                ", creationDate=" + creationDate +
                '}';
    }
}
