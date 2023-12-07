package com.company.onlinestorespring.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bank_card")
public class BankCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_card_id")
    private Long id;

    @Column(name = "card_number")
    private long cardNumber;

    @Column(name = "expiration_year")
    private int expirationYear;

    @Column(name = "expiration_month")
    private int expirationMonth;

    @Column(name = "card_owner")
    private String cardOwnerName;

    @Column(name = "cvv_number")
    private int cvvNumber;

    @Column(name = "balance")
    private Double balance;
    private Long userId;

    public BankCard() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(int expirationYear) {
        this.expirationYear = expirationYear;
    }

    public int getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(int expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public String getCardOwnerName() {
        return cardOwnerName;
    }

    public void setCardOwnerName(String cardOwnerName) {
        this.cardOwnerName = cardOwnerName;
    }

    public int getCvvNumber() {
        return cvvNumber;
    }

    public void setCvvNumber(int cvvNumber) {
        this.cvvNumber = cvvNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankCard bankCard = (BankCard) o;
        return cardNumber == bankCard.cardNumber
                && expirationYear == bankCard.expirationYear
                && expirationMonth == bankCard.expirationMonth
                && cvvNumber == bankCard.cvvNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, expirationYear, expirationMonth, cvvNumber);
    }
}
