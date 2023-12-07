package com.company.onlinestorespring.dao;

import com.company.onlinestorespring.Application;
import com.company.onlinestorespring.dao.impl.BankCardDaoImpl;
import com.company.onlinestorespring.entity.BankCard;
import com.company.onlinestorespring.exception.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {BankCardDaoImpl.class, Application.class})
public class BankCardDaoTest {

    @Autowired
    private BankCardDaoImpl bankCardDao;

    @Test
    public void BankCardDao_FindBankCardByNumber_ReturnBankCard() throws EntityNotFoundException {
        long cardNumber = 1122334455667788L;
        BankCard bankCard = bankCardDao.findBankCardByNumber(cardNumber);

        assertThat(bankCard).isNotNull();
    }
}
