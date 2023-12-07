package com.company.onlinestorespring.controller;

import com.company.onlinestorespring.controller.dto.PaymentInformationDto;
import com.company.onlinestorespring.controller.dto.converter.DtoConverter;
import com.company.onlinestorespring.entity.BankCard;
import com.company.onlinestorespring.entity.ProductCategory;
import com.company.onlinestorespring.entity.User;
import com.company.onlinestorespring.entity.UserOrder;
import com.company.onlinestorespring.exception.BankCardVerificationException;
import com.company.onlinestorespring.service.OrderInformationService;
import com.company.onlinestorespring.service.ProductCategoryService;
import com.company.onlinestorespring.service.UserOrderService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
import static com.company.onlinestorespring.controller.Attributes.*;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final ProductCategoryService productCategoryService;
    private final UserOrderService userOrderService;
    private final OrderInformationService orderInformationService;
    private final DtoConverter<BankCard, PaymentInformationDto> converter;

    @Autowired
    public OrderController(ProductCategoryService productCategoryService, UserOrderService userOrderService,
                           OrderInformationService orderInformationService, DtoConverter<BankCard,
                           PaymentInformationDto> converter) {

        this.productCategoryService = productCategoryService;
        this.userOrderService = userOrderService;
        this.orderInformationService = orderInformationService;
        this.converter = converter;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/user-orders")
    public String viewUserOrders(Model model, HttpSession session) {
        User user = (User) session.getAttribute(USER);
        if (user == null) {
            return "/access-denied";
        }
        List<ProductCategory> categories = productCategoryService.retrieveProductCategories();
        model.addAttribute(CATEGORIES, categories);

        long userId = user.getId();
        List<UserOrder> userOrders = userOrderService.retrieveUserOrdersByUserId(userId);
        model.addAttribute(USER_ORDERS, userOrders);
        return "user-orders";
    }

    @GetMapping("/place-order-form")
    public String placeOrderForm(Model model, HttpSession session) {
        User user = (User) session.getAttribute(USER);
        if (user == null) {
            return "/access-denied";
        }
        Long userId = user.getId();
        List<UserOrder> userOrders = userOrderService.retrieveUserOrdersByUserWhereProductStatusTrue(userId);
        model.addAttribute(USER_ORDERS, userOrders);

        double totalCost = userOrderService.calculateTotalCost(userOrders);
        model.addAttribute(TOTAL_COST, totalCost);
        PaymentInformationDto paymentInformationDto = new PaymentInformationDto();
        model.addAttribute(PAYMENT_INFORMATION, paymentInformationDto);
        return "place-order-form";
    }

    @PostMapping("/place-order")
    public String placeOrder(@Valid PaymentInformationDto paymentInformationDto, BindingResult bindingResult,
                             Model model, HttpSession session, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "place-order-form";
        }
        String orderResult;
        BankCard bankCard = converter.convertToEntity(paymentInformationDto);
        try {
            User user = (User) session.getAttribute(USER);
            List<UserOrder> userOrders = userOrderService.retrieveUserOrdersByUserWhereProductStatusTrue(user.getId());
            double totalCost = userOrderService.calculateTotalCost(userOrders);
            orderInformationService.placeOrder(userOrders, bankCard, paymentInformationDto.getDeliveryAddress(),
                                                paymentInformationDto.getDeliveryDate(), totalCost);
            orderResult = "orderPlaced";
            redirectAttributes.addFlashAttribute(ORDER_RESULT, orderResult);
            return "redirect:/order/user-orders";
        } catch (BankCardVerificationException e) {
            orderResult = "noBankCard";
            model.addAttribute(ORDER_RESULT, orderResult);
            return "place-order-form";
        }
    }

    @GetMapping("/active-orders")
    public String viewAllActiveOrders(Model model) {
        List<ProductCategory> categories = productCategoryService.retrieveProductCategories();
        model.addAttribute(CATEGORIES, categories);
        List<UserOrder> userOrders = userOrderService.retrieveUserOrderByOrderInformationStatus(WAIT_STATUS);
        model.addAttribute(USER_ORDERS, userOrders);
        return "all-active-orders";
    }

    @PostMapping("/finalize-order")
    public String finalizeUserOrder(@RequestParam(value = "orderInformationId") Long orderInformationId) {
        orderInformationService.finalizeOrderByOrderInformationId(orderInformationId);
        return "redirect:/order/active-orders";
    }

    @PostMapping("/refuse-order")
    public String refuseUserOrder(@RequestParam(value = "orderInformationId") Long orderInformationId) {
        orderInformationService.refuseOrderByOrderInformationId(orderInformationId);
        return "redirect:/order/active-orders";
    }

    @GetMapping("/completed-orders")
    public String viewAllCompletedOrders(Model model) {
        List<ProductCategory> categories = productCategoryService.retrieveProductCategories();
        model.addAttribute(CATEGORIES, categories);
        List<UserOrder> userOrders = userOrderService.retrieveAllUserOrders();
        model.addAttribute(COMPLETED_ORDERS, userOrders);
        return "all-completed-orders";
    }
}