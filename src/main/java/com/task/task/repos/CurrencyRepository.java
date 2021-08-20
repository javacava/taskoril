package com.task.task.repos;

import com.task.task.model.Currency;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface CurrencyRepository extends MongoRepository<Currency, String> {

    Optional<Currency> findByName(String name);

}
