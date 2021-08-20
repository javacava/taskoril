package com.task.task.controller;


import com.task.task.dto.MaxPriceDto;
import com.task.task.dto.MinPriceDto;
import com.task.task.dto.PricesDto;
import com.task.task.model.CryptoPrice;
import com.task.task.model.Currency;
import com.task.task.model.СsvTemplate;
import com.task.task.repos.CryptoPriceRepository;
import com.task.task.repos.CurrencyRepository;
import com.task.task.service.CryptoPriceService;
import com.task.task.service.CsvWriterService;
import com.task.task.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cryptocurrencies")
@RequiredArgsConstructor
public class CryptoCurrenciesController {

    private final CryptoPriceService cryptoPriceService;
    private final CurrencyService currencyService;
    private final CsvWriterService csvWriterService;

    @GetMapping("minprice/{currencyName}")
    public MinPriceDto getMinPrice(@PathVariable String currencyName) {
        Currency currency = currencyService.findByName(currencyName);
        CryptoPrice cryptoPrice = cryptoPriceService.findMinPrice(currency);
        return new MinPriceDto(currencyName, cryptoPrice.getPrice().toString());
    }

    @GetMapping("maxprice/{currencyName}")
    public MaxPriceDto getMaxPrice(@PathVariable String currencyName) {
        Currency currency = currencyService.findByName(currencyName);
        CryptoPrice cryptoPrice = cryptoPriceService.findMaxPrice(currency);
        return new MaxPriceDto(currencyName, cryptoPrice.getPrice().toString());
    }

    @GetMapping
    public PricesDto getCryptocurrencies(@RequestParam(required = false) String name, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        if (name == null) throw new RuntimeException("Request parameter name was not found");
        Currency currency = currencyService.findByName(name);
        Pageable pagging = PageRequest.of(page, size);
        return new PricesDto(currency.getName(), cryptoPriceService.cryptoPriceList(currency, pagging).stream()
                .map(cryptoPrice -> cryptoPrice.getPrice().toString()).collect(Collectors.toList()));
    }

    @GetMapping("csv")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=crypto.csv";
        response.setHeader(headerKey, headerValue);
        csvWriterService.writeCsv(response.getWriter());
    }

}
