package com.example.practice.msscbrewery.service.inventory.impl;

import com.example.practice.msscbrewery.service.inventory.BeerInventoryServiceClient;
import com.example.practice.msscbrewery.service.inventory.BeerInventoryServiceFeignClient;
import com.example.practice.msscbrewery.service.inventory.model.BeerInventoryDto;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Profile("local-discovery")
@Service
public class BeerInventoryServiceFeignClientImpl implements BeerInventoryServiceClient {

    private final BeerInventoryServiceFeignClient beerInventoryServiceFeignClient;

    @Override
    public Integer getInventoryOnHand(UUID beerId) {
        log.debug("Calling Inventory Service - BeerId: {}", beerId);
        ResponseEntity<List<BeerInventoryDto>> response = beerInventoryServiceFeignClient.getInventoryOnHand(beerId);
        Integer quantityOnHand = Objects.requireNonNull(response.getBody())
            .stream()
            .mapToInt(BeerInventoryDto::getQuantityOnHand)
            .sum();

        log.debug("BeerId: {} On hand is: {}", beerId, quantityOnHand);
        return quantityOnHand;
    }
}
