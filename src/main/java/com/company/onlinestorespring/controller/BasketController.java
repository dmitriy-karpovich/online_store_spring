package com.company.onlinestorespring.controller;

import com.company.onlinestorespring.entity.*;
import com.company.onlinestorespring.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
import static com.company.onlinestorespring.controller.Attributes.*;

@Controller
@RequestMapping("/basket")
public class BasketController {

    private final UserOrderService userOrderService;
    private final ProductService productService;
    private final ProductCategoryService productCategoryService;

    @Autowired
    public BasketController(UserOrderService userOrderService, ProductService productService,
                            ProductCategoryService productCategoryService) {
        this.userOrderService = userOrderService;
        this.productService = productService;
        this.productCategoryService = productCategoryService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @PostMapping("/add-to-basket")
    public String addProductToBasket(@RequestParam(value = "productId") String productId,
                                     @RequestParam(value = "purchasedQuantity") String quantity,
                                     HttpSession session, RedirectAttributes redirectAttributes) {

        User user = (User) session.getAttribute("user");
        Long prodId = Long.parseLong(productId);
        Product product = productService.retrieveProductById(prodId);
        Integer purchasedQuantity = Integer.parseInt(quantity);

        String basketMessage = "ok";
        boolean addingOrderResult = userOrderService.addNewOrder(user, product, purchasedQuantity);
        if (!addingOrderResult) {
            basketMessage = "error";
        }
        Long categoryId = product.getProductCategory().getId();
        redirectAttributes.addFlashAttribute(BASKET_MESSAGE, basketMessage);
        return "redirect:/catalog?categoryId=" + categoryId;
    }

    @GetMapping("/view-basket")
    public String viewBasket(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "/access-denied";
        }
        List<ProductCategory> categories = productCategoryService.retrieveProductCategories();
        model.addAttribute(CATEGORIES, categories);

        Long userId = user.getId();
        List<UserOrder> userOrders = userOrderService.retrieveUserOrdersByUserWhereProductStatusTrue(userId);
        model.addAttribute(USER_ORDERS, userOrders);

        double totalCost = userOrderService.calculateTotalCost(userOrders);
        model.addAttribute(TOTAL_COST, totalCost);
        return "basket";
    }

    @PostMapping("/empty-basket")
    public String emptyBasket(@RequestParam(value = "userOrderId") Long userOrderId) {
        userOrderService.removeUserOrdersById(userOrderId);
        return "redirect:/basket/view-basket";
    }
}