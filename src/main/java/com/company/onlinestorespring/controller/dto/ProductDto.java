package com.company.onlinestorespring.controller.dto;

import com.company.onlinestorespring.entity.ProductCategory;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ProductDto {

    private Long id;

    @NotNull(message = "{validation.isRequired}")
    @Size(min = 1, message = "{validation.atLeastOneCharacter}")
    private String name;

    @NotNull(message = "{validation.isRequired}")
    @Size(min = 1, message = "{validation.atLeastOneCharacter}")
    private String description;

    @NotNull(message = "{validation.isRequired}")
    @Pattern(regexp = "^\\d+\\.\\d{0,4}$", message = "{validation.zeroOrPositive}")
    private String price;

    @NotNull(message = "{validation.isRequired}")
    private Boolean status;

    @NotNull(message = "{validation.isRequired}")
    @Size(min = 1, message = "{validation.atLeastOneCharacter}")
    private String photoReference;

    @NotNull(message = "{validation.isRequired}")
    @Pattern(regexp = "^\\d+$", message = "{validation.zeroOrPositive}")
    private String availableQuantity;

    @Valid
    private ProductCategory productCategory;

    public ProductDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getPhotoReference() {
        return photoReference;
    }

    public void setPhotoReference(String photoReference) {
        this.photoReference = photoReference;
    }

    public String getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(String availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }
}
