package com.company.onlinestorespring.service;

import com.company.onlinestorespring.entity.BankCard;
import com.company.onlinestorespring.exception.BankCardVerificationException;

public interface BankCardService {

    /**
     * Order payment
     *
     * @param bankCard  - the bank card
     * @param totalCost - the total cost of ordering
     * @return the result of payment
     */
    boolean pay(BankCard bankCard, double totalCost) throws BankCardVerificationException;
}