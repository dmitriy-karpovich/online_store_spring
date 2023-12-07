package com.company.onlinestorespring.controller;

import com.company.onlinestorespring.controller.dto.UserDto;
import com.company.onlinestorespring.controller.dto.converter.DtoConverter;
import com.company.onlinestorespring.entity.ProductCategory;
import com.company.onlinestorespring.entity.User;
import com.company.onlinestorespring.service.ProductCategoryService;
import com.company.onlinestorespring.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
import java.util.Optional;
import static com.company.onlinestorespring.controller.Attributes.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final ProductCategoryService productCategoryService;
    private final DtoConverter<User, UserDto> dtoConverter;

    @Autowired
    public UserController(UserService userService, ProductCategoryService productCategoryService,
                          DtoConverter<User, UserDto> dtoConverter) {
        this.userService = userService;
        this.productCategoryService = productCategoryService;
        this.dtoConverter = dtoConverter;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute(USER_DTO, new UserDto());
        return "registration-form";
    }

    @PostMapping("/register")
    public String register(@Valid UserDto userDto, BindingResult bindingResult,
                           RedirectAttributes redirectAttributes, Model model) {

        if (bindingResult.hasErrors()) {
            return "registration-form";
        }
        String userMessage;
        String username = userDto.getUsername();
        Optional<User> existingUser = userService.retrieveUserByUsername(username);
        if (existingUser.isPresent()) {
            userMessage = "userAlreadyExists";
            model.addAttribute(USER_DTO, new UserDto());
            model.addAttribute(USER_MESSAGE, userMessage);
            return "registration-form";
        }
        userService.saveUser(dtoConverter.convertToEntity(userDto));
        userMessage = "registered";
        redirectAttributes.addFlashAttribute(USER_MESSAGE, userMessage);
        return "redirect:/user/login-page";
    }

    @GetMapping("/login-page")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/profile")
    public String showProfilePage(Model model) {
        List<ProductCategory> categories = productCategoryService.retrieveProductCategories();
        model.addAttribute(CATEGORIES, categories);
        return "profile";
    }
}