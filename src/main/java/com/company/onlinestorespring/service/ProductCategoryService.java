package com.company.onlinestorespring.service;

import com.company.onlinestorespring.entity.ProductCategory;
import java.util.List;
import java.util.Optional;

public interface ProductCategoryService {

    /**
     * Retrieve product categories
     *
     * @return the list of product categories
     */
    List<ProductCategory> retrieveProductCategories();

    /**
     * Save a new product category
     *
     * @param productCategory - the product category
     * @return the saved product category
     */
    ProductCategory saveProductCategory(ProductCategory productCategory);

    /**
     * Retrieve a product category by id
     *
     * @param productCategoryId - the product category id
     * @return the product category
     */
    Optional<ProductCategory> retrieveCategoryById(Long productCategoryId);
}