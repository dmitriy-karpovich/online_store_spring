package com.company.onlinestorespring.service.impl;

import com.company.onlinestorespring.dao.ProductCategoryDao;
import com.company.onlinestorespring.dao.ProductDao;
import com.company.onlinestorespring.entity.Product;
import com.company.onlinestorespring.entity.ProductCategory;
import com.company.onlinestorespring.exception.EntityNotFoundException;
import com.company.onlinestorespring.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;
    private final ProductCategoryDao productCategoryDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    public ProductServiceImpl(ProductDao productDao, ProductCategoryDao productCategoryDao) {
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
    }

    @Override
    public Product retrieveProductById(Long productId) {
        Product product = productDao.findProductById(productId);
        if (product == null) {
            throw new EntityNotFoundException("Product with id " + productId + " was not found.");
        }
        return product;
    }

    @Override
    public Optional<Product> retrieveProductByName(String productName) {
        return Optional.ofNullable(productDao.findProductByName(productName));
    }

    @Override
    public List<Product> retrieveProductsByProductCategoryId(Long productCategoryId) {
        List<Product> products = productDao.findProductByCategoryId(productCategoryId);
        if (products.isEmpty()) {
            throw new EntityNotFoundException("Products were not found.");
        }
        return products;
    }

    @Override
    @Transactional
    public Product addNewProduct(Product product) {
        ProductCategory productCategory = retrieveAvailableCategory(product.getProductCategory().getCategoryName());
        product.setProductCategory(productCategory);
        Product result = productDao.saveProduct(product);
        LOGGER.info("Product with id {} was successfully added.", result.getId());
        return result;
    }

    @Override
    @Transactional
    public Product updateProductInformation(Product product) {
        Product existingProduct = productDao.findProductById(product.getId());
        if (existingProduct == null) {
            throw new EntityNotFoundException("Product with the name " + product.getName() + " was not found.");
        }
        ProductCategory productCategory = retrieveAvailableCategory(product.getProductCategory().getCategoryName());
        product.setProductCategory(productCategory);
        LOGGER.info("Product with id {} was updated", product.getId());
        return productDao.updateProduct(product);
    }

    private ProductCategory retrieveAvailableCategory(String categoryName) {
        ProductCategory availableProductCategory =
                    productCategoryDao.findProductCategoryByName(categoryName);
        if (availableProductCategory != null) {
            return availableProductCategory;
        } else {
            ProductCategory productCategory = new ProductCategory();
            productCategory.setCategoryName(categoryName);
            return productCategoryDao.saveProductCategory(productCategory);
        }
    }
}