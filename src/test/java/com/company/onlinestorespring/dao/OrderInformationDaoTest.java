package com.company.onlinestorespring.dao;

import com.company.onlinestorespring.Application;
import com.company.onlinestorespring.dao.impl.OrderInformationDaoImpl;
import com.company.onlinestorespring.entity.OrderInformation;
import com.company.onlinestorespring.exception.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {OrderInformationDaoImpl.class, Application.class})
public class OrderInformationDaoTest {

    @Autowired
    private OrderInformationDaoImpl orderInformationDao;

    @Test
    public void OrderInformationDao_SaveOderInformation_SavedSuccessfully() throws EntityNotFoundException {
        OrderInformation orderInformation = new OrderInformation();
        orderInformation.setOrderDate(LocalDate.now());
        orderInformation.setDeliveryDate(LocalDate.now().plusDays(1));
        orderInformation.setDeliveryAddress("Yanka Kupala Street");
        orderInformation.setOrderStatus("Awaiting fulfillment");

        OrderInformation result  = orderInformationDao.saveOrderInformation(orderInformation);
        assertThat(result).isNotNull();
    }
}