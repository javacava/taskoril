package com.task.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
public class PricesDto {

    private String currencyName;
    private List<String> prices;

    public PricesDto(String currencyName, List<String> prices) {
        this.currencyName = currencyName;
        this.prices = prices;
    }

}
