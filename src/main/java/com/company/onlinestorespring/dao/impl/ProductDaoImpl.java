package com.company.onlinestorespring.dao.impl;

import com.company.onlinestorespring.dao.ProductDao;
import com.company.onlinestorespring.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    private static final String FIND_PRODUCTS_BY_CATEGORY_ID =
            "select p from Product p where p.productCategory.id=:categoryId";
    private static final String FIND_PRODUCT_BY_NAME = "select p from Product p where p.name=:name";
    private static final String FIND_PRODUCT_BY_ID = "select p from Product p where p.id=:id";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Product findProductById(Long id) {
        TypedQuery<Product> query = entityManager.createQuery(FIND_PRODUCT_BY_ID, Product.class);
        query.setParameter("id", id);
        List<Product> productList = query.getResultList();
        return productList.isEmpty() ? null : productList.get(0);
    }

    @Override
    public Product saveProduct(Product product) {
        entityManager.persist(product);
        return product;
    }

    @Override
    public Product findProductByName(String name) {
        TypedQuery<Product> query = entityManager.createQuery(FIND_PRODUCT_BY_NAME, Product.class);
        query.setParameter("name", name);
        List<Product> productList = query.getResultList();
        return productList.isEmpty() ? null : productList.get(0);
    }

    @Override
    public List<Product> findProductByCategoryId(Long categoryId) {
        TypedQuery<Product> query = entityManager.createQuery(FIND_PRODUCTS_BY_CATEGORY_ID, Product.class);
        query.setParameter("categoryId", categoryId);
        return query.getResultList();
    }

    @Override
    public Product updateProduct(Product product) {
        return entityManager.merge(product);
    }
}