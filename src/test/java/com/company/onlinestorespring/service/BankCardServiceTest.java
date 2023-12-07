package com.company.onlinestorespring.service;

import com.company.onlinestorespring.dao.impl.BankCardDaoImpl;
import com.company.onlinestorespring.entity.BankCard;
import com.company.onlinestorespring.exception.BankCardVerificationException;
import com.company.onlinestorespring.service.impl.BankCardServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BankCardServiceTest {

    @Mock
    private BankCardDaoImpl bankCardDao;
    @InjectMocks
    private BankCardServiceImpl bankCardService;

    @Test
    public void BankCardService_PayForOrder_SuccessfullyPaid() throws BankCardVerificationException {
        BankCard bankCard = new BankCard();
        bankCard.setCardNumber(1122334455667788L);
        bankCard.setExpirationMonth(5);
        bankCard.setExpirationYear(2024);
        bankCard.setCvvNumber(111);
        bankCard.setBalance(1000.00);
        double price = 100.0;

        when(bankCardDao.findBankCardByNumber(bankCard.getCardNumber())).thenReturn(bankCard);
        boolean paid = bankCardService.pay(bankCard, price);
        assertThat(paid).isTrue();
    }
}