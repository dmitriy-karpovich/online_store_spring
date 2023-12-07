package com.company.onlinestorespring.controller;

import com.company.onlinestorespring.Application;
import com.company.onlinestorespring.controller.dto.UserDto;
import com.company.onlinestorespring.controller.dto.converter.DtoConverter;
import com.company.onlinestorespring.entity.User;
import com.company.onlinestorespring.service.ProductCategoryService;
import com.company.onlinestorespring.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
import static org.mockito.Mockito.doNothing;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;
    @MockBean
    private ProductCategoryService productCategoryService;
    @MockBean
    private DtoConverter<User, UserDto> dtoConverter;


    @Test
    public void UserController_RegistrationForm_ReturnRegistrationView() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/user/registration"))
                .andReturn();
        ModelAndView modelAndView = mvcResult.getModelAndView();
        ModelAndViewAssert.assertViewName(modelAndView,"registration-form");
    }

    @Test
    public void UserController_Register_ReturnLoginPage() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUsername("freddy");
        userDto.setPassword("freddy123");
        userDto.setName("Freddy");
        userDto.setSurname("Freddy");
        userDto.setPatronymic("Freddy");
        userDto.setPhone("375000000000");
        userDto.setEmail("freddy@mail.com");
        userDto.setMatchingPassword("freddy123");
        User user = dtoConverter.convertToEntity(userDto);

        doNothing().when(userService).saveUser(user);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andReturn();

        ModelAndView modelAndView = mvcResult.getModelAndView();
        ModelAndViewAssert.assertViewName(modelAndView,"redirect:/user/login-page");
    }
}