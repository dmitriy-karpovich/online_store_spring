package com.company.onlinestorespring.controller;

import com.company.onlinestorespring.controller.dto.ProductDto;
import com.company.onlinestorespring.controller.dto.converter.DtoConverter;
import com.company.onlinestorespring.entity.Product;
import com.company.onlinestorespring.entity.ProductCategory;
import com.company.onlinestorespring.service.ProductCategoryService;
import com.company.onlinestorespring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import static com.company.onlinestorespring.controller.Attributes.CATEGORIES;
import static com.company.onlinestorespring.controller.Attributes.PRODUCTS;

@Controller
@RequestMapping("/")
public class TransitionController {

    private final ProductService productService;
    private final ProductCategoryService productCategoryService;
    private final DtoConverter<Product, ProductDto> dtoConverter;

    @Autowired
    public TransitionController(ProductService productService, ProductCategoryService productCategoryService,
                                DtoConverter<Product, ProductDto> dtoConverter) {

        this.productService = productService;
        this.productCategoryService = productCategoryService;
        this.dtoConverter = dtoConverter;
    }

    @GetMapping("/main")
    public String showMain(Model model) {
        List<ProductCategory> categories = productCategoryService.retrieveProductCategories();
        model.addAttribute(CATEGORIES, categories);
        return "main";
    }

    @GetMapping("/catalog")
    public String showCatalog(Model model, @RequestParam(value = "categoryId") String categoryId) {
        List<ProductCategory> categories = productCategoryService.retrieveProductCategories();
        Long productCategoryId = Long.parseLong(categoryId);
        List<Product> result = productService.retrieveProductsByProductCategoryId(productCategoryId);
        List<ProductDto> products = result.stream()
                .map(dtoConverter::convertToDto).toList();
        model.addAttribute(CATEGORIES, categories);
        model.addAttribute(PRODUCTS, products);
        return "catalog";
    }

    @GetMapping("/contacts")
    public String showContacts(Model model) {
        List<ProductCategory> categories = productCategoryService.retrieveProductCategories();
        model.addAttribute(CATEGORIES, categories);
        return "contacts";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }
}