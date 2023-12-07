package com.company.onlinestorespring.dao;

import com.company.onlinestorespring.entity.BankCard;

public interface BankCardDao {

    /**
     * Find a bank card in the database
     *
     * @param cardNumber - the bank card number
     * @return the bank card
     */
    BankCard findBankCardByNumber(Long cardNumber);

    /**
     * Update the balance in the database by bank card number
     *
     * @param cardNumber - the bank card number
     * @param newBalance - the new balance
     */
    void updateBalanceByCardNumber(Long cardNumber, double newBalance);
}