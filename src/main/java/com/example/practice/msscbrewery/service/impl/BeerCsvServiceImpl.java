package com.example.practice.msscbrewery.service.impl;

import com.example.practice.msscbrewery.service.BeerCsvService;
import com.example.practice.msscbrewery.web.model.BeerCSVRecord;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BeerCsvServiceImpl implements BeerCsvService {
    @Override
    public List<BeerCSVRecord> convertCSV(File csvFile) {
        try {
            return new CsvToBeanBuilder<BeerCSVRecord>(new FileReader(csvFile))
                .withType(BeerCSVRecord.class)
                .build()
                .parse();
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
