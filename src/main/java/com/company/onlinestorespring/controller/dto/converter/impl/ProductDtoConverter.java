package com.company.onlinestorespring.controller.dto.converter.impl;

import com.company.onlinestorespring.controller.dto.ProductDto;
import com.company.onlinestorespring.controller.dto.converter.DtoConverter;
import com.company.onlinestorespring.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoConverter implements DtoConverter<Product, ProductDto> {

    @Override
    public Product convertToEntity(ProductDto dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(Double.parseDouble(dto.getPrice()));
        product.setStatus(dto.getStatus());
        product.setPhotoReference(dto.getPhotoReference());
        product.setAvailableQuantity(Long.valueOf(dto.getAvailableQuantity()));
        product.setProductCategory(dto.getProductCategory());
        return product;
    }

    @Override
    public ProductDto convertToDto(Product entity) {
        ProductDto productDto = new ProductDto();
        productDto.setId(entity.getId());
        productDto.setName(entity.getName());
        productDto.setDescription(entity.getDescription());
        productDto.setPrice(String.valueOf(entity.getPrice()));
        productDto.setStatus(entity.getStatus());
        productDto.setPhotoReference(entity.getPhotoReference());
        productDto.setAvailableQuantity(String.valueOf(entity.getAvailableQuantity()));
        productDto.setProductCategory(entity.getProductCategory());
        return productDto;
    }
}