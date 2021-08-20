package com.task.task.util;


import com.task.task.model.Currency;
import com.task.task.repos.CurrencyRepository;
import com.task.task.service.SequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class DBMigrator {

    private final CurrencyRepository currencyRepository;
    private final MongoTemplate mongoTemplate;
    private final SequenceGeneratorService sequenceGeneratorService;

    @PostConstruct
    public void setup() {
        mongoTemplate.getDb().drop();
        currencyRepository.save(new Currency("BTC", sequenceGeneratorService.generateSequence(Currency.SEQUENCE_NAME)));
        currencyRepository.save(new Currency("XRP", sequenceGeneratorService.generateSequence(Currency.SEQUENCE_NAME)));
        currencyRepository.save(new Currency("ETH", sequenceGeneratorService.generateSequence(Currency.SEQUENCE_NAME)));
    }

}
