package com.task.task.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


@Data
public class CexioResponse {

    private String e;
    private String ok;
    private List<Price> data;

    public static class Price {
        private String symbol1;
        private String symbol2;
        private BigDecimal lprice;

        public String getSymbol1() {
            return symbol1;
        }

        public void setSymbol1(String symbol1) {
            this.symbol1 = symbol1;
        }

        public String getSymbol2() {
            return symbol2;
        }

        public void setSymbol2(String symbol2) {
            this.symbol2 = symbol2;
        }

        public BigDecimal getLprice() {
            return lprice;
        }

        public void setLprice(BigDecimal lprice) {
            this.lprice = lprice;
        }
    }
}
