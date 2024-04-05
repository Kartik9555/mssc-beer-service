package com.example.practice.msscbrewery.service.impl;

import com.example.practice.msscbrewery.service.BeerService;
import com.example.practice.msscbrewery.web.model.BeerDTO;
import com.example.practice.msscbrewery.web.model.BeerPagedList;
import com.example.practice.msscbrewery.web.model.BeerStyleEnum;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class BeerServiceImpl implements BeerService {
    @Override
    public BeerDTO getBeerById(UUID beerId) {
        return BeerDTO.builder()
            .id(beerId)
            .beerName("Galaxy Pale")
            .beerStyle(BeerStyleEnum.IPA)
            .upc(1234567L)
            .build();
    }


    @Override
    public BeerDTO createBeer(BeerDTO beerDTO) {
        return BeerDTO.builder()
            .id(UUID.randomUUID())
            .build();
    }


    @Override
    public BeerDTO updateBeer(UUID beerId, BeerDTO beerDTO) {
        return BeerDTO.builder()
            .id(beerId)
            .beerStyle(beerDTO.getBeerStyle())
            .beerName(beerDTO.getBeerName())
            .upc(beerDTO.getUpc())
            .build();
    }


    @Override
    public void deleteBeer(UUID beerId) {
        // Deleting a beer
    }


    @Override
    public BeerPagedList listBeers() {
        return null;
    }
}
