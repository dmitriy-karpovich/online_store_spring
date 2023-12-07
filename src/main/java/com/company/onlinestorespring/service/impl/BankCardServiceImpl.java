package com.company.onlinestorespring.service.impl;

import com.company.onlinestorespring.dao.BankCardDao;
import com.company.onlinestorespring.entity.BankCard;
import com.company.onlinestorespring.exception.BankCardVerificationException;
import com.company.onlinestorespring.service.BankCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Service
public class BankCardServiceImpl implements BankCardService {

    private final BankCardDao bankCardDao;

    @Autowired
    public BankCardServiceImpl(BankCardDao bankCardDao) {
        this.bankCardDao = bankCardDao;
    }

    @Override
    @Transactional
    public boolean pay(BankCard bankCard, double totalCost) throws BankCardVerificationException {
        BankCard existingBankCard = bankCardDao.findBankCardByNumber(bankCard.getCardNumber());
        if (!bankCard.equals(existingBankCard)
            || (!isCardDateValid(existingBankCard.getExpirationMonth(), existingBankCard.getExpirationYear()))
            || (!checkBalance(existingBankCard.getBalance(), totalCost))) {
            throw new BankCardVerificationException("Error occurred when verifying the bank card with number " +
                                                    bankCard.getCardNumber());
        }
        double newBalance = existingBankCard.getBalance() - totalCost;
        double scale = Math.pow(10, 2);
        newBalance = Math.ceil(newBalance * scale) / scale;
        bankCardDao.updateBalanceByCardNumber(existingBankCard.getCardNumber(), newBalance);
        return true;
    }

    private boolean isCardDateValid(int month, int year) {
        LocalDate currentDate = LocalDate.now();
        if (year < currentDate.getYear()) {
            return false;
        }
        if (year == currentDate.getYear() && month <= currentDate.getMonthValue()) {
            return false;
        }
        return true;
    }

    private boolean checkBalance(double balance, double cost) {
        return cost <= balance;
    }
}