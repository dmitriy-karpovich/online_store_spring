package com.company.onlinestorespring.dao;

import com.company.onlinestorespring.entity.ProductCategory;
import java.util.List;

public interface ProductCategoryDao {

    /**
     * Find a product category in the database by id
     *
     * @param id - the id
     * @return  the product category
     */
    ProductCategory findProductCategoryById(Long id);

    /**
     * Save a new product category in the database
     *
     * @param productCategory - the product category
     * @return  the saved product category
     */
    ProductCategory saveProductCategory(ProductCategory productCategory);

    /**
     * Find a product category in the database by name
     *
     * @param name - the product category name
     * @return the product category
     */
    ProductCategory findProductCategoryByName(String name);

    /**
     * Find all product categories in the database
     *
     * @return the list of product categories
     */
    List<ProductCategory> findAllProductCategories();
}