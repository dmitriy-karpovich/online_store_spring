package com.company.onlinestorespring.controller.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class PaymentInformationDto {

    @NotNull(message = "{validation.isRequired}")
    @Pattern(regexp = "^[0-9]{16}$")
    private String cardNumber;

    @NotNull(message = "{validation.isRequired}")
    @Pattern(regexp = "^202[3-9]{1}$")
    private String expirationYear;

    @NotNull(message = "{validation.isRequired}")
    @Pattern(regexp = "^(0[1-9]|1[012])$")
    private String expirationMonth;

    @NotNull(message = "{validation.isRequired}")
    @Pattern(regexp = "^[A-Z]{2,15}\s[A-Z]{2,15}$")
    private String cardOwnerName;

    @NotNull(message = "{validation.isRequired}")
    @Pattern(regexp = "^[0-9]{3}$")
    private String cvvNumber;

    @NotNull(message = "{validation.isRequired}")
    @Size(min = 15, message = "{validation.address}")
    private String deliveryAddress;

    @NotNull(message = "{validation.isRequired}")
    @Future(message = "{validation.date}")
    LocalDate deliveryDate;

    public PaymentInformationDto() {
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(String expirationYear) {
        this.expirationYear = expirationYear;
    }

    public String getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(String expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public String getCardOwnerName() {
        return cardOwnerName;
    }

    public void setCardOwnerName(String cardOwnerName) {
        this.cardOwnerName = cardOwnerName;
    }

    public String getCvvNumber() {
        return cvvNumber;
    }

    public void setCvvNumber(String cvvNumber) {
        this.cvvNumber = cvvNumber;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
