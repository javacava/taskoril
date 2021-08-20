package com.task.task.dto;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
public class MinPriceDto {

    private String currencyName;
    private String minimumPrice;

    public MinPriceDto(String currencyName, String minimumPrice) {
        this.currencyName = currencyName;
        this.minimumPrice = minimumPrice;
    }

}
