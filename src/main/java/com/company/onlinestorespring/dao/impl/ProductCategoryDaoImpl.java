package com.company.onlinestorespring.dao.impl;

import com.company.onlinestorespring.dao.ProductCategoryDao;
import com.company.onlinestorespring.entity.ProductCategory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ProductCategoryDaoImpl implements ProductCategoryDao {

    private static final String FIND_PRODUCT_CATEGORY_BY_NAME =
            "select pc from ProductCategory pc where pc.categoryName=:name";

    private static final String FIND_PRODUCT_CATEGORY_BY_ID = "select pc from ProductCategory pc where pc.id=:id";

    private static final String FIND_ALL_PRODUCT_CATEGORY = "select pc from ProductCategory pc";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ProductCategory findProductCategoryById(Long id) {
        TypedQuery<ProductCategory> query = entityManager.createQuery(FIND_PRODUCT_CATEGORY_BY_ID, ProductCategory.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public ProductCategory saveProductCategory(ProductCategory productCategory) {
        entityManager.persist(productCategory);
        return productCategory;
    }

    @Override
    public ProductCategory findProductCategoryByName(String name) {
        TypedQuery<ProductCategory> query = entityManager.createQuery(FIND_PRODUCT_CATEGORY_BY_NAME, ProductCategory.class);
        query.setParameter("name", name);
        List<ProductCategory> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public List<ProductCategory> findAllProductCategories() {
        TypedQuery<ProductCategory> query = entityManager.createQuery(FIND_ALL_PRODUCT_CATEGORY, ProductCategory.class);
        return query.getResultList();
    }
}