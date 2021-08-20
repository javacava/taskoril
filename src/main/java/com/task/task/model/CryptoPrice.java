package com.task.task.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@NoArgsConstructor
@Data
@Document(collection = "crypto_prices")

public class CryptoPrice {

    @Transient
    public static final String SEQUENCE_NAME = "crypto_prices_count";

    public CryptoPrice(long id, BigDecimal price, Instant createdAt, Currency currency) {
        this.id = id;
        this.price = price;
        this.createdAt = createdAt;
        this.currency = currency;
    }

    @Id
    private long id;
    private BigDecimal price;
    private Instant createdAt;

    @DBRef
    private Currency currency;

}
