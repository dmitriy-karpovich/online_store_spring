package com.company.onlinestorespring.service;

import com.company.onlinestorespring.dao.impl.ProductCategoryDaoImpl;
import com.company.onlinestorespring.dao.impl.ProductDaoImpl;
import com.company.onlinestorespring.entity.Product;
import com.company.onlinestorespring.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductDaoImpl productDao;
    @Mock
    private ProductCategoryDaoImpl productCategoryDao;
    @InjectMocks
    private ProductServiceImpl productService;


    @Test
    public void ProductService_RetrieveByCategoryId_ReturnList() {
        Product product = new Product();
        product.setPrice(1000.00);
        List<Product> products = new ArrayList<>();
        products.add(product);

        when(productDao.findProductByCategoryId(Mockito.any(Long.class))).thenReturn(products);
        List<Product> result = productService.retrieveProductsByProductCategoryId(1L);
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    public void ProductService_RetrieveByProductId_ReturnProduct() {
        Product product = new Product();
        product.setName("Ноутбуки");

        when(productDao.findProductByName(Mockito.any(String.class))).thenReturn(product);
        Optional<Product> result = productService.retrieveProductByName(product.getName());
        assertThat(result).isNotEmpty();
    }
}