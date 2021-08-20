package com.task.task.repos;

import com.task.task.model.CryptoPrice;
import com.task.task.model.Currency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;


public interface CryptoPriceRepository extends MongoRepository<CryptoPrice, String> {

    Optional<CryptoPrice> findFirstByCurrencyOrderByPriceAsc(Currency currency);

    Optional<CryptoPrice> findFirstByCurrencyOrderByPriceDesc(Currency currency);

    Page<CryptoPrice> findAllByCurrencyOrderByPriceAsc(Currency currency, Pageable pageable);

}
