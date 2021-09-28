package za.ac.nwu.ac.domain.persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "USER_ACCOUNT", schema = "DISCOVERYSYSTEM")
public class UserAccount implements Serializable {
    private static final long serialVersionUID = -7923892885843374118L;

    private Long userAccountID;
    private Long memberID;
    private Long accountTypeID;
    //private AccountType accountType;
    private Integer accountBalance;
    private LocalDate creationDate;

    public UserAccount(Long userAccountID, Long memberID, Long accountTypeID, Integer accountBalance, LocalDate creationDate) {
        this.userAccountID = userAccountID;
        this.memberID = memberID;
        this.accountTypeID = accountTypeID;
        this.accountBalance = accountBalance;
        this.creationDate = creationDate;
    }
    public UserAccount(Long memberID, Long accountTypeID, Integer accountBalance, LocalDate creationDate) {
        this.memberID = memberID;
        this.accountTypeID = accountTypeID;
        this.accountBalance = accountBalance;
        this.creationDate = creationDate;
    }

    @Id
    @SequenceGenerator(name = "SEQ_USER_ACCOUNT_ID", sequenceName = "DISCOVERYSYSTEM.SEQ_USER_ACCOUNT_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER_ACCOUNT_ID")
    @Column(name = "USER_ACCOUNT_ID")

    public Long getUserAccountID() {
        return userAccountID;
    }

    public void setUserAccountID(Long userAccountID) {
        this.userAccountID = userAccountID;
    }

//ToDo: if adding Member entity, add join to member class
    @Column(name = "MEMBER_ID")
    public Long getMemberID() {
        return memberID;
    }

    public void setMemberID(Long memberID) {
        this.memberID = memberID;
    }
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ACCOUNT_TYPE_ID")
    @Column(name = "ACCOUNT_TYPE_ID")
    public Long getAccountTypeID() {
        return accountTypeID;
    }

    public void setAccountTypeID(Long accountTypeID) {
        this.accountTypeID = accountTypeID;
    }

    @Column(name = "ACCOUNT_BALANCE")
    public Integer getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Integer accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Column(name = "CREATION_DATE")
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
        UserAccount that = (UserAccount) o;
        return Objects.equals(userAccountID, that.userAccountID) && Objects.equals(memberID, that.memberID) && Objects.equals(accountTypeID, that.accountTypeID) && Objects.equals(accountBalance, that.accountBalance) && Objects.equals(creationDate, that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userAccountID, memberID, accountTypeID, accountBalance, creationDate);
    }

    @Override
    public java.lang.String toString() {
        return "UserAccount{" +
                "userAccountID=" + userAccountID +
                ", memberID=" + memberID +
                ", accountTypeID=" + accountTypeID +
                ", accountBalance=" + accountBalance +
                ", creationDate=" + creationDate +
                '}';
    }
}
