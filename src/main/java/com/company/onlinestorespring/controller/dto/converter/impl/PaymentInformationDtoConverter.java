package com.company.onlinestorespring.controller.dto.converter.impl;

import com.company.onlinestorespring.controller.dto.PaymentInformationDto;
import com.company.onlinestorespring.controller.dto.converter.DtoConverter;
import com.company.onlinestorespring.entity.BankCard;
import org.springframework.stereotype.Component;

@Component
public class PaymentInformationDtoConverter implements DtoConverter<BankCard, PaymentInformationDto> {

    @Override
    public BankCard convertToEntity(PaymentInformationDto dto) {
        BankCard bankCard = new BankCard();
        bankCard.setCardNumber(Long.parseLong(dto.getCardNumber()));
        bankCard.setCardOwnerName(dto.getCardOwnerName());
        bankCard.setExpirationMonth(Integer.parseInt(dto.getExpirationMonth()));
        bankCard.setExpirationYear(Integer.parseInt(dto.getExpirationYear()));
        bankCard.setCvvNumber(Integer.parseInt(dto.getCvvNumber()));
        return bankCard;
    }

    @Override
    public PaymentInformationDto convertToDto(BankCard entity) {
        return null;
    }
}
