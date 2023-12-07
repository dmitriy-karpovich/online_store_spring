package com.company.onlinestorespring.dao;

import com.company.onlinestorespring.Application;
import com.company.onlinestorespring.dao.impl.ProductCategoryDaoImpl;
import com.company.onlinestorespring.dao.impl.ProductDaoImpl;
import com.company.onlinestorespring.entity.Product;
import com.company.onlinestorespring.entity.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {ProductDaoImpl.class, ProductCategoryDaoImpl.class, Application.class})
public class ProductDaoTest {

    @Autowired
    private ProductDaoImpl productDao;

    @Autowired
    private ProductCategoryDaoImpl productCategoryDao;

    @Test
    public void ProductDao_SaveProduct_SuccessfullySaved() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("Ноутбуки Apple");
        productCategoryDao.saveProductCategory(productCategory);

        Product product = new Product();
        product.setStatus(true);
        product.setName("Apple Macbook");
        product.setPrice(3000.00);
        product.setPhotoReference("/photo");
        product.setAvailableQuantity(10L);
        product.setDescription("Apple Laptop");
        product.setProductCategory(productCategory);
        productDao.saveProduct(product);

        Product result = productDao.findProductByName("Apple Macbook");
        assertThat(result).isNotNull();
    }
}