package com.company.onlinestorespring.dao.impl;

import com.company.onlinestorespring.dao.BankCardDao;
import com.company.onlinestorespring.entity.BankCard;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class BankCardDaoImpl implements BankCardDao {

    private static final String FIND_BANK_CARD_BY_NUMBER = "select bk from BankCard bk where bk.cardNumber=:cardNumber";

    private static final String UPDATE_BALANCE_BY_CARD_NUMBER =
            "update BankCard bk set bk.balance=:balance where bk.cardNumber=:number";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public BankCard findBankCardByNumber(Long cardNumber) {
        TypedQuery<BankCard> query = entityManager.createQuery(FIND_BANK_CARD_BY_NUMBER, BankCard.class);
        query.setParameter("cardNumber", cardNumber);
        List<BankCard> bankCardList = query.getResultList();
        return bankCardList.isEmpty() ? null : bankCardList.get(0);
    }

    @Override
    public void updateBalanceByCardNumber(Long cardNumber, double newBalance) {
        Query query = entityManager.createQuery(UPDATE_BALANCE_BY_CARD_NUMBER);
        query.setParameter("balance", newBalance);
        query.setParameter("number", cardNumber);
    }
}