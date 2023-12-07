package com.company.onlinestorespring.dao;

import com.company.onlinestorespring.entity.Product;
import java.util.List;

public interface ProductDao {

    /**
     * Find a product in the database
     *
     * @param id - the id
     * @return  the product
     */
    Product findProductById(Long id);

    /**
     * Save a new product in the database
     *
     * @param product - the product
     * @return the saved product
     */
    Product saveProduct(Product product);

    /**
     * Receive Product from Database by name
     *
     * @param name - Product name
     * @return the product
     */
    Product findProductByName(String name);

    /**
     * Find a product in the database by category id
     *
     * @param categoryId - the product category id
     * @return the list of product
     */
    List<Product> findProductByCategoryId(Long categoryId);

    /**
     * Update a product in the database
     *
     * @param product - the product to update
     * @return the updated product
     */
    Product updateProduct(Product product);
}