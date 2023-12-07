package com.company.onlinestorespring.service;

import com.company.onlinestorespring.dao.impl.UserOrderDaoImpl;
import com.company.onlinestorespring.entity.Product;
import com.company.onlinestorespring.entity.User;
import com.company.onlinestorespring.entity.UserOrder;
import com.company.onlinestorespring.service.impl.UserOrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
public class UserOrderServiceTest {

    @Mock
    private UserOrderDaoImpl userOrderDao;
    @InjectMocks
    private UserOrderServiceImpl userOrderService;

    @Test
    public void UserOrderService_SaveOrder_ReturnTrue() {
        User user = new User();
        Product product = new Product();
        int purchasedQuantity = 1;
        UserOrder userOrder = new UserOrder();
        userOrder.setUser(user);
        userOrder.setProduct(product);
        userOrder.setPurchasedQuantity(purchasedQuantity);

        doNothing().when(userOrderDao).saveUserOrder(userOrder);
        boolean result = userOrderService.addNewOrder(user, product, purchasedQuantity);
        assertThat(result).isTrue();
    }
}