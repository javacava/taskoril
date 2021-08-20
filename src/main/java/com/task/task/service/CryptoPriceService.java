package com.task.task.service;


import com.task.task.model.CryptoPrice;
import com.task.task.model.Currency;
import com.task.task.repos.CryptoPriceRepository;
import com.task.task.repos.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CryptoPriceService {

    private final CryptoPriceRepository cryptoPriceRepository;

    public CryptoPrice save(CryptoPrice cryptoPrice) {
        return cryptoPriceRepository.save(cryptoPrice);
    }

    public CryptoPrice findMaxPrice(Currency currency) {
        return cryptoPriceRepository.findFirstByCurrencyOrderByPriceDesc(currency).orElseThrow(() -> {
            throw new RuntimeException("MaxPrice was not found");
        });
    }

    public CryptoPrice findMinPrice(Currency currency) {
        return cryptoPriceRepository.findFirstByCurrencyOrderByPriceAsc(currency).orElseThrow(() -> {
            throw new RuntimeException("MinPrice was not found");
        });
    }

    public List<CryptoPrice> cryptoPriceList(Currency currency, Pageable pageable) {
        return cryptoPriceRepository.findAllByCurrencyOrderByPriceAsc(currency, pageable).stream().collect(Collectors.toList());
    }

}
