package com.example.practice.model.events;

import com.example.practice.model.BeerDTO;

public class NewInventoryEvent extends BeerEvent{
    public NewInventoryEvent(BeerDTO beerDTO) {
        super(beerDTO);
    }
}
