package com.example.practice.msscbrewery.service;

import com.example.practice.msscbrewery.web.model.BeerDTO;
import com.example.practice.msscbrewery.web.model.BeerPagedList;
import com.example.practice.msscbrewery.web.model.BeerStyleEnum;
import java.util.UUID;

public interface BeerService {
    BeerDTO getBeerById(UUID beerId, Boolean showInventoryOnHand);

    BeerDTO createBeer(BeerDTO beerDTO);

    BeerDTO updateBeer(UUID beerId, BeerDTO beerDTO);

    void deleteBeer(UUID beerId);

    BeerPagedList listBeers(Integer pageNumber, Integer pageSize, String beerName, BeerStyleEnum beerStyle, Boolean showInventoryOnHand);
}
