package com.company.onlinestorespring.controller;

import com.company.onlinestorespring.Application;
import com.company.onlinestorespring.entity.Product;
import com.company.onlinestorespring.service.ProductService;
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
public class TransitionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ProductService productService;

    @Test
    public void TransitionController_ViewCatalog_ReturnProfile() throws Exception {
        Product product = new Product();
        List<Product> products = List.of(product);
        when(productService.retrieveProductsByProductCategoryId(Mockito.anyLong())).thenReturn(products);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/catalog")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("categoryId", "1"))
                .andReturn();
        ModelAndView modelAndView = mvcResult.getModelAndView();
        ModelAndViewAssert.assertViewName(modelAndView,"catalog");
    }
}