package com.example.practice.msscbrewery.service.inventory;

import java.util.UUID;

public interface BeerInventoryServiceClient {
    Integer getInventoryOnHand(UUID beerId);
}
