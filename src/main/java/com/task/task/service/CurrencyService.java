package com.task.task.service;


import com.task.task.model.Currency;
import com.task.task.repos.CryptoPriceRepository;
import com.task.task.repos.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    public Currency findByName(String name) {
        return currencyRepository.findByName(name).orElseThrow(() -> {
            throw new RuntimeException("СurrencyName was not found");
        });
    }

}
