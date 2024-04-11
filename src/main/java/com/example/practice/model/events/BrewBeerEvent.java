package com.example.practice.model.events;

import com.example.practice.model.BeerDTO;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent{
    public BrewBeerEvent(BeerDTO beerDTO) {
        super(beerDTO);
    }
}
