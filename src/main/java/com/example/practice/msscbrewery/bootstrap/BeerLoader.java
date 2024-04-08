package com.example.practice.msscbrewery.bootstrap;

import com.example.practice.msscbrewery.domain.Beer;
import com.example.practice.msscbrewery.respositories.BeerRepository;
import com.example.practice.msscbrewery.service.BeerCsvService;
import com.example.practice.msscbrewery.web.model.BeerCSVRecord;
import com.example.practice.msscbrewery.web.model.BeerStyleEnum;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

//@Component
@RequiredArgsConstructor
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository beerRepository;
    private final BeerCsvService beerCsvService;

    @Override
    public void run(String... args) throws Exception {
        if(beerRepository.count()==0){
            loadBeerData();
        }
    }


    private void loadBeerData() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:csvdata/beers.csv");
        List<BeerCSVRecord> records = beerCsvService.convertCSV(file);
        records.forEach(record -> {
            BeerStyleEnum beerStyle = switch (record.getStyle()) {
                case "American Pale Lager" -> BeerStyleEnum.LAGER;
                case "American Pale Ale (APA)", "American Black Ale", "Belgian Dark Ale", "American Blonde Ale" -> BeerStyleEnum.ALE;
                case "American IPA", "American Double / Imperial IPA", "Belgian IPA" -> BeerStyleEnum.IPA;
                case "American Porter" -> BeerStyleEnum.PORTER;
                case "Oatmeal Stout", "American Stout" -> BeerStyleEnum.STOUT;
                case "Saison / Farmhouse Ale" -> BeerStyleEnum.SAISON;
                case "Fruit / Vegetable Beer", "Winter Warmer", "Berliner Weissbier" -> BeerStyleEnum.WHEAT;
                case "English Pale Ale" -> BeerStyleEnum.PALE_ALE;
                default -> BeerStyleEnum.PILSNER;
            };
            beerRepository.save(
                Beer.builder()
                    .beerName(StringUtils.abbreviate(record.getBeer(), 50))
                    .beerStyle(beerStyle.name())
                    .upc(String.valueOf(record.getRow()))
                    .quantityOnHand(record.getCount())
                    .price(BigDecimal.TEN)
                    .build()
            );
        });
        System.out.println("Loaded beers" + beerRepository.count());
    }
}
