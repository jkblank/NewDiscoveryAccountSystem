package za.ac.nwu.ac.domain.persistence;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
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
    private LocalDateTime transactionDateTime;

    public AccountTransaction(Long memberID, String accountTypeName, Long amount, LocalDateTime txDate) {
        this.accountType = accountType;
        this.memberID = memberID;
        this.amount = amount;
        this.transactionDateTime = transactionDateTime;
    }

    public AccountTransaction(long transactionID, AccountType accountType, long memberID, long amount, LocalDateTime transactionDateTime) {
        this.transactionID = transactionID;
        this.accountType = accountType;
        this.memberID = memberID;
        this.amount = amount;
        this.transactionDateTime = transactionDateTime;
    }

    @Column(name = "TX_ID")
    public long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(long transactionID) {
        this.transactionID = transactionID;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="MNEMONIC")
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
    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountTransaction that = (AccountTransaction) o;
        return transactionID == that.transactionID && accountType == that.accountType && memberID == that.memberID && amount == that.amount && Objects.equals(transactionDateTime, that.transactionDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionID, accountType, memberID, amount, transactionDateTime);
    }

    @Override
    public String toString() {
        return "AccountTransaction{" +
                "transactionID=" + transactionID +
                ", accountType=" + accountType +
                ", memberID=" + memberID +
                ", amount=" + amount +
                ", transactionDateTime=" + transactionDateTime +
                '}';
    }
}
