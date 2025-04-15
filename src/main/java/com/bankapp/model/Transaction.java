package com.bankapp.model;//import java.sql.Time;
//import java.util.Date;        Arent very useful with hibernate and a bit old

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity(name = "transactions")
public final class Transaction {

    public enum TransactionType {
        withdraw, deposit, transferDeducted, transferCredited, credited;
    }

    private LocalDateTime timestamp;

    @Id
    @Column(name = "transaction_id", unique = true, nullable = false, length = 36)
    private String transactionId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType type;    //--> withdraw/deposit/transfer

    @ManyToOne
    @JoinColumn(name = "account_num", nullable = false)
    private Account account;

    private double amount;

    public Transaction() {
        this.timestamp = null;
        this.type = null;
        this.amount = 0.0;
        this.account = null;
        this.transactionId = null;
    }

    public Transaction(TransactionType type, Account account, double amount) {
        this.timestamp = LocalDateTime.now();
        this.transactionId = UUID.randomUUID().toString();
        this.type = type;
        this.account = account;
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public TransactionType getType() {
        return type;
    }

    public Account getAccount() {
        return account;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatedTime = timestamp.format(formatter);
        return "TransactionId : " + transactionId +
                ", Time : " + formatedTime +
                ", Type : " + type +
                ", Amount : " + amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;
        return transactionId.equals(that.transactionId);
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        return transactionId.hashCode();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public double getAmount() {
        return amount;
    }
}
