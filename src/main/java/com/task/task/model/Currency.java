package com.task.task.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Data
@Document(collection = "currencies")

public class Currency {

    @Transient
    public static final String SEQUENCE_NAME = "currency_count";

    @Id
    private long id;
    private String name;

    public Currency (String name,long id) {
        this.id = id;
        this.name = name;
    }

}
