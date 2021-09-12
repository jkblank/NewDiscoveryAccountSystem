package za.ac.nwu.ac.domain.dto;

import za.ac.nwu.ac.domain.persistence.AccountTransaction;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class AccountTransactionDto implements Serializable {

    private static final long serialVersionUID = -7819344808062462808L;

    private Long memberID;
    private String accountTypeName;
    private Long amount;
    private LocalDate txDate;

    public AccountTransactionDto() {
    }

    public AccountTransactionDto(Long memberID, String accountTypeName, Long amount, LocalDate txDate) {
        this.memberID = memberID;
        this.accountTypeName = accountTypeName;
        this.amount = amount;
        this.txDate = txDate;
    }

    public AccountTransactionDto(AccountTransaction accountTransaction) {
        this.setMemberID(accountTransaction.getMemberID());
        this.setAccountTypeName(accountTransaction.getAccountType().getAccountTypeName());
        this.setAmount(accountTransaction.getAmount());
        this.setTxDate(accountTransaction.getTransactionDate());
    }

    public Long getMemberID() {
        return memberID;
    }

    public void setMemberID(Long memberID) {
        this.memberID = memberID;
    }

    public String getAccountTypeName() {
        return accountTypeName;
    }

    public void setAccountTypeName(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

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
        return Objects.equals(memberID, that.memberID) && Objects.equals(accountTypeName, that.accountTypeName) && Objects.equals(amount, that.amount) && Objects.equals(txDate, that.txDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberID, accountTypeName, amount, txDate);
    }

    @Override
    public String toString() {
        return "AccountTransactionDto{" +
                "memberID='" + memberID + '\'' +
                ", accountTypeName='" + accountTypeName + '\'' +
                ", amount=" + amount +
                ", txDate=" + txDate +
                '}';
    }
}
