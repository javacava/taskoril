package com.task.task.service;


import com.task.task.model.CryptoPrice;
import com.task.task.model.Currency;
import com.task.task.model.СsvTemplate;
import com.task.task.repos.CryptoPriceRepository;
import com.task.task.repos.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.PrintWriter;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CsvWriterService {

    private final CryptoPriceRepository cryptoPriceRepository;
    private final CurrencyRepository currencyRepository;

    public void writeCsv(PrintWriter printWriter) {
        ICsvBeanWriter csvWriter = null;
        try {
            csvWriter = new CsvBeanWriter(printWriter, CsvPreference.STANDARD_PREFERENCE);
            String[] csvHeader = {"Cryptocurrency Name", "Min Price", "Max Price"};
            String[] nameMapping = {"name", "minPrice", "maxPrice"};
            csvWriter.writeHeader(csvHeader);
            for (Currency currency : currencyRepository.findAll()) {
                СsvTemplate csvTemplate = new СsvTemplate();
                csvTemplate.setMinPrice(cryptoPriceRepository.findFirstByCurrencyOrderByPriceAsc(currency)
                        .map(CryptoPrice::getPrice).orElse(BigDecimal.ZERO));
                csvTemplate.setMaxPrice(cryptoPriceRepository.findFirstByCurrencyOrderByPriceDesc(currency)
                        .map(CryptoPrice::getPrice).orElse(BigDecimal.ZERO));
                csvTemplate.setName(currency.getName());
                csvWriter.write(csvTemplate, nameMapping);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert csvWriter != null;
                csvWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
