package com.company.onlinestorespring.controller;

import com.company.onlinestorespring.controller.dto.ProductDto;
import com.company.onlinestorespring.controller.dto.converter.DtoConverter;
import com.company.onlinestorespring.entity.Product;
import com.company.onlinestorespring.entity.ProductCategory;
import com.company.onlinestorespring.service.ProductCategoryService;
import com.company.onlinestorespring.service.ProductService;
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
import java.util.Optional;
import static com.company.onlinestorespring.controller.Attributes.*;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final ProductCategoryService productCategoryService;
    private final DtoConverter<Product, ProductDto> dtoConverter;

    @Autowired
    public ProductController(ProductService productService, ProductCategoryService productCategoryService,
                             DtoConverter<Product, ProductDto> dtoConverter) {
        this.productService = productService;
        this.productCategoryService = productCategoryService;
        this.dtoConverter = dtoConverter;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/update-form")
    public String showEditProductPage(Model model, @RequestParam(value = "productId") String productId) {
        List<ProductCategory> categories = productCategoryService.retrieveProductCategories();
        model.addAttribute(CATEGORIES, categories);
        Long prodId = Long.parseLong(productId);
        Product result = productService.retrieveProductById(prodId);
        ProductDto product = dtoConverter.convertToDto(result);
        model.addAttribute(PRODUCT, product);
        return "update-form";
    }

    @PostMapping("/update-product")
    public String updateProduct(@Valid @ModelAttribute(name = "product") ProductDto product,
                                BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "update-form";
        }
        Product result = productService.updateProductInformation(dtoConverter.convertToEntity(product));
        Long categoryId = result.getProductCategory().getId();
        redirectAttributes.addFlashAttribute(PRODUCT_MESSAGE, "productUpdated");
        return "redirect:/catalog?categoryId=" + categoryId;
    }

    @GetMapping("/add-form")
    public String showAddProductPage(Model model) {
        List<ProductCategory> categories = productCategoryService.retrieveProductCategories();
        model.addAttribute(CATEGORIES, categories);
        ProductDto product = new ProductDto();
        model.addAttribute(PRODUCT, product);
        return "add-product-form";
    }

    @PostMapping("/add-product")
    public String addProduct(@Valid @ModelAttribute(name = "product") ProductDto product,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            return "add-product-form";
        }
        String productMessage;
        Optional<Product> existingProduct = productService.retrieveProductByName(product.getName());
        if (existingProduct.isPresent()) {
            productMessage = "productExists";
            model.addAttribute(PRODUCT, new ProductDto());
            model.addAttribute(PRODUCT_MESSAGE, productMessage);
            return "add-product-form";
        }
        Product result = productService.addNewProduct(dtoConverter.convertToEntity(product));
        Long categoryId = result.getProductCategory().getId();
        productMessage = "productAdded";
        redirectAttributes.addFlashAttribute(PRODUCT_MESSAGE, productMessage);
        return "redirect:/catalog?categoryId=" + categoryId;
    }
}
