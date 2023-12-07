package com.company.onlinestorespring.service;

import com.company.onlinestorespring.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    /**
     * Retrieve a product by id
     *
     * @param productId - the product id
     * @return the product
     */
    Product retrieveProductById(Long productId);

    /**
     * Retrieve a product by id
     *
     * @param productName - the product name
     * @return the product
     */
    Optional<Product> retrieveProductByName(String productName);

    /**
     * Retrieve products by product category id
     *
     * @param productCategoryId - the product category id
     * @return the list of products
     */
    List<Product> retrieveProductsByProductCategoryId(Long productCategoryId);

    /**
     * Add a new product
     *
     * @param product - the product
     * @return the product
     */
    Product addNewProduct(Product product);

    /**
     * Update a product
     *
     * @param product - the product
     * @return the saved product
     */
    Product updateProductInformation(Product product);
}
