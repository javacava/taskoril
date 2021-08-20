package com.task.task.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
public class СsvTemplate {

    private String name;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;

}
