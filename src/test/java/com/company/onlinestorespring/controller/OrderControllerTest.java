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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserOrderService userOrderService;
    @MockBean
    private OrderInformationService orderInformationService;

    @Test
    public void OrderController_ViewUserOrder_ReturnView() throws Exception {
        User user = new User();
        user.setId(1L);
        UserOrder userOrder = new UserOrder();
        List<UserOrder> userOrders = List.of(userOrder);

        when(userOrderService.retrieveUserOrdersByUserId(Mockito.any(Long.class))).thenReturn(userOrders);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/order/user-orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .sessionAttr("user", user))
                .andReturn();
        ModelAndView modelAndView = mvcResult.getModelAndView();
        ModelAndViewAssert.assertViewName(modelAndView,"user-orders");
    }

    @Test
    public void UserController_FinaliseOrder_ReturnView() throws Exception {
        doNothing().when(orderInformationService).finalizeOrderByOrderInformationId(Mockito.any(Long.class));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/order/finalize-order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("orderInformationId", "1"))
                .andReturn();
        ModelAndView modelAndView = mvcResult.getModelAndView();
        ModelAndViewAssert.assertViewName(modelAndView,"redirect:/order/active-orders");
    }
}
