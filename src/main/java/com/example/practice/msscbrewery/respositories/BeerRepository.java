package com.example.practice.msscbrewery.respositories;

import com.example.practice.msscbrewery.domain.Beer;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface BeerRepository extends ListPagingAndSortingRepository<Beer, UUID>, ListCrudRepository<Beer, UUID> {

    Page<Beer> findBeersByBeerNameAndBeerStyle(String beerName, String beerStyle, PageRequest pageRequest);
    Page<Beer> findBeersByBeerName(String beerName, PageRequest pageRequest);
    Page<Beer> findBeersByBeerStyle(String beerStyle, PageRequest pageRequest);
}
