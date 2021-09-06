package za.ac.nwu.ac.domain.persistence;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="ACCOUNT_TX", schema = "DISCOVERYSYSTEM")
public class AccountTransaction implements Serializable {
    private static final long serialVersionUID = 1636276203670716488L;
    @Id
    @SequenceGenerator(name="SEQ_TX_ID", sequenceName = "DISCOVERYSYSTEM.SEQ_TX_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TX_ID")

    private long transactionID;
    private AccountType accountType;
    private long memberID;
    private long amount;
    private LocalDate transactionDate;

    public AccountTransaction() {
    }

    public AccountTransaction(long transactionID, AccountType accountType, long memberID, long amount, LocalDate transactionDate) {
        this.transactionID = transactionID;
        this.accountType = accountType;
        this.memberID = memberID;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    @Column(name = "TX_ID")
    public long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(long transactionID) {
        this.transactionID = transactionID;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="ACCOUNT_TYPE_ID")
    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @Column(name = "MEMBER_ID")
    public long getMemberID() {
        return memberID;
    }

    public void setMemberID(long memberID) {
        this.memberID = memberID;
    }

    @Column(name = "AMOUNT")
    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    @Column(name = "TX_DATE")
    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountTransaction that = (AccountTransaction) o;
        return transactionID == that.transactionID && accountType == that.accountType && memberID == that.memberID && amount == that.amount && Objects.equals(transactionDate, that.transactionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionID, accountType, memberID, amount, transactionDate);
    }

    @Override
    public String toString() {
        return "AccountTransaction{" +
                "transactionID=" + transactionID +
                ", accountTypeID=" + accountType +
                ", memberID=" + memberID +
                ", amount=" + amount +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
