package com.task.task.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class MaxPriceDto {

    private String currencyName;
    private String maximumPrice;

    public MaxPriceDto(String currencyName, String maximumPrice) {
        this.currencyName = currencyName;
        this.maximumPrice = maximumPrice;
    }

}
