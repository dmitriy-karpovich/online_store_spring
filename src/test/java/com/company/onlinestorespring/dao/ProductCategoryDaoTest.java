package com.company.onlinestorespring.dao;

import com.company.onlinestorespring.Application;
import com.company.onlinestorespring.dao.impl.ProductCategoryDaoImpl;
import com.company.onlinestorespring.entity.ProductCategory;
import com.company.onlinestorespring.exception.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {ProductCategoryDaoImpl.class, Application.class})
public class ProductCategoryDaoTest {

    @Autowired
    private ProductCategoryDaoImpl productCategoryDao;

    @Test
    public void ProductCategory_FindCategoryById_ReturnCategory() throws EntityNotFoundException {
        ProductCategory productCategory = productCategoryDao.findProductCategoryById(1L);
        assertThat(productCategory).isNotNull();
    }

    @Test
    public void ProductCategory_SaveCategory_ReturnRowUpdated() throws EntityNotFoundException {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("Микроволновка");

        ProductCategory result = productCategoryDao.saveProductCategory(productCategory);
        assertThat(result).isNotNull();
    }

    @Test
    public void ProductCategory_FindAllCategories_ReturnAllCategories() throws EntityNotFoundException {
        List<ProductCategory> productCategories = productCategoryDao.findAllProductCategories();
        assertThat(productCategories.size()).isEqualTo(5);
    }
}