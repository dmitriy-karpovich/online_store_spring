package com.company.onlinestorespring.service.impl;

import com.company.onlinestorespring.dao.ProductCategoryDao;
import com.company.onlinestorespring.entity.ProductCategory;
import com.company.onlinestorespring.exception.EntityNotFoundException;
import com.company.onlinestorespring.service.ProductCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryDao productCategoryDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductCategoryServiceImpl.class);

    @Autowired
    public ProductCategoryServiceImpl(ProductCategoryDao productCategoryDao) {
        this.productCategoryDao = productCategoryDao;
    }

    @Override
    public List<ProductCategory> retrieveProductCategories() {
        List<ProductCategory> productCategories = productCategoryDao.findAllProductCategories();
        if (productCategories.isEmpty()) {
            throw new EntityNotFoundException("Product categories were not found.");
        }
        return productCategories;
    }

    @Override
    public ProductCategory saveProductCategory(ProductCategory productCategory) {
        LOGGER.info("Product category with the name {} has been saved.", productCategory.getCategoryName());
        return productCategoryDao.saveProductCategory(productCategory);
    }

    @Override
    public Optional<ProductCategory> retrieveCategoryById(Long id) {
        ProductCategory productCategory = productCategoryDao.findProductCategoryById(id);
        return Optional.ofNullable(productCategory);
    }
}