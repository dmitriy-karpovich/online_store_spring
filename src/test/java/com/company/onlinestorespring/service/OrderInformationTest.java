package com.company.onlinestorespring.service;

import com.company.onlinestorespring.dao.impl.OrderInformationDaoImpl;
import com.company.onlinestorespring.dao.impl.UserOrderDaoImpl;
import com.company.onlinestorespring.entity.*;
import com.company.onlinestorespring.exception.BankCardVerificationException;
import com.company.onlinestorespring.service.impl.BankCardServiceImpl;
import com.company.onlinestorespring.service.impl.OrderInformationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.List;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderInformationTest {

    @Mock
    private OrderInformationDaoImpl orderInformationDao;
    @Mock
    private BankCardServiceImpl bankCardService;
    @Mock
    private UserOrderDaoImpl userOrderDao;
    @InjectMocks
    private OrderInformationServiceImpl orderInformationService;
    private static final String WAIT_STATUS = "ожидается";

    @Test
    public void OrderInformationService_PlaceOrder_ReturnTrue() throws BankCardVerificationException {
        User user = new User();
        user.setId(1L);
        Product product = new Product();
        product.setId(1L);
        int purchasedQuantity = 1;
        UserOrder userOrder = new UserOrder();
        userOrder.setUser(user);
        userOrder.setProduct(product);
        userOrder.setPurchasedQuantity(purchasedQuantity);
        List<UserOrder> userOrders = List.of(userOrder);

        BankCard bankCard = new BankCard();
        bankCard.setCardNumber(1122334455667788L);
        bankCard.setExpirationMonth(5);
        bankCard.setExpirationYear(2024);
        bankCard.setCvvNumber(111);
        bankCard.setBalance(1000.00);

        LocalDate deliveryDate = LocalDate.of(2023,12,10);
        String deliveryAddress = "Minsk";
        double totalCost = 100;

        OrderInformation orderInformation = new OrderInformation();
        orderInformation.setId(1L);
        orderInformation.setDeliveryDate(deliveryDate);
        orderInformation.setOrderDate(LocalDate.now());
        orderInformation.setDeliveryAddress(deliveryAddress);
        orderInformation.setOrderStatus(WAIT_STATUS);

        when(orderInformationDao.saveOrderInformation(Mockito.any(OrderInformation.class))).thenReturn(orderInformation);
        orderInformationService.placeOrder(userOrders, bankCard, deliveryAddress, deliveryDate, totalCost);

        verify(orderInformationDao, times(1)).saveOrderInformation(orderInformation);
    }
}
