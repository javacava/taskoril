package com.task.task.service;


import com.task.task.dto.CexioResponse;
import com.task.task.model.CryptoPrice;
import com.task.task.model.Currency;
import com.task.task.dto.LastPriceDto;
import com.task.task.repos.CryptoPriceRepository;
import com.task.task.repos.CurrencyRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CexioPriceService {

    private final RestTemplate restTemplate;
    private final CurrencyRepository currencyRepository;
    private final CryptoPriceService cryptoPriceService;
    private final SequenceGeneratorService sequenceGeneratorService;

    @Scheduled(cron = "*/10 * * * * *")
    public void scheduleTaskUsingCronExpression() {
        CexioResponse cexioResponse;
        try {
            cexioResponse = lastPrice().getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        for (CexioResponse.Price price :
                cexioResponse.getData()) {
            for (Currency currency :
                    currencyRepository.findAll()) {
                if (price.getSymbol1().equals(currency.getName())) {
                    CryptoPrice cryptoPrice = new CryptoPrice();
                    cryptoPrice.setCurrency(currency);
                    cryptoPrice.setId(sequenceGeneratorService.generateSequence(Currency.SEQUENCE_NAME));
                    cryptoPrice.setCreatedAt(Instant.now());
                    cryptoPrice.setPrice(price.getLprice());
                    cryptoPriceService.save(cryptoPrice);
                }
            }
        }
    }

    public ResponseEntity<CexioResponse> lastPrice() {
        return restTemplate.getForEntity(String.join("/", "https://cex.io/api/last_prices/USD"), CexioResponse.class);
    }

}