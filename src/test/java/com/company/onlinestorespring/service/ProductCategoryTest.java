package com.company.onlinestorespring.service;

import com.company.onlinestorespring.dao.impl.ProductCategoryDaoImpl;
import com.company.onlinestorespring.entity.ProductCategory;
import com.company.onlinestorespring.service.impl.ProductCategoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductCategoryTest {

    @Mock
    private ProductCategoryDaoImpl productCategoryDao;

    @InjectMocks
    private ProductCategoryServiceImpl productCategoryService;

    @Test
    public void ProductCategoryService_SaveCategory_ReturnSavedCategory() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("Ноутбуки");

        when(productCategoryDao.saveProductCategory(Mockito.any(ProductCategory.class))).thenReturn(productCategory);
        ProductCategory result = productCategoryService.saveProductCategory(productCategory);
        assertThat(result).isNotNull();
    }
}