package com.example.practice.msscbrewery.service;

import com.example.practice.msscbrewery.web.model.BeerCSVRecord;
import java.io.File;
import java.util.List;

public interface BeerCsvService {
    List<BeerCSVRecord> convertCSV(File csvFile);
}
