package com.example.practice.msscbrewery.service;

import com.example.practice.msscbrewery.web.model.BeerDTO;
import com.example.practice.msscbrewery.web.model.BeerPagedList;
import java.util.UUID;

public interface BeerService {
    BeerDTO getBeerById(UUID beerId);

    BeerDTO createBeer(BeerDTO beerDTO);

    BeerDTO updateBeer(UUID beerId, BeerDTO beerDTO);

    void deleteBeer(UUID beerId);

    BeerPagedList listBeers();
}
