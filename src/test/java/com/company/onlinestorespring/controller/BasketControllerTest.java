package com.company.onlinestorespring.controller;

import com.company.onlinestorespring.Application;
import com.company.onlinestorespring.entity.User;
import com.company.onlinestorespring.entity.UserOrder;
import com.company.onlinestorespring.service.OrderInformationService;
import com.company.onlinestorespring.service.UserOrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class BasketControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserOrderService userOrderService;
    @MockBean
    private OrderInformationService orderInformationService;

    @Test
    public void BasketController_AddProductToBasket_ReturnBasket() throws Exception {
        User user = new User();
        UserOrder userOrder = new UserOrder();
        List<UserOrder> userOrders = List.of(userOrder);
        when(userOrderService.retrieveUserOrdersByUserId(Mockito.anyLong())).thenReturn(userOrders);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/basket/add-to-basket")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("productId", "1")
                        .param("categoryId", "1")
                        .sessionAttr("user", user)
                        .param("purchasedQuantity", "2"))
                .andReturn();
        ModelAndView modelAndView = mvcResult.getModelAndView();
        ModelAndViewAssert.assertViewName(modelAndView,"redirect:/catalog?categoryId=" + 1);
    }

    @Test
    public void BasketController_ViewBasket_ReturnView() throws Exception {
        User user = new User();
        user.setId(1L);
        UserOrder userOrder = new UserOrder();
        List<UserOrder> userOrders = List.of(userOrder);
        when(userOrderService.retrieveUserOrdersByUserWhereProductStatusTrue(1L)).thenReturn(userOrders);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/basket/view-basket")
                        .contentType(MediaType.APPLICATION_JSON)
                        .sessionAttr("user", user))
                .andReturn();
        ModelAndView modelAndView = mvcResult.getModelAndView();
        ModelAndViewAssert.assertViewName(modelAndView,"basket");
    }
}