package com.example.practice.msscbrewery.service.inventory;

import com.example.practice.msscbrewery.service.inventory.model.BeerInventoryDto;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BeerInventoryServiceFeignClientFailover implements BeerInventoryServiceFeignClient {

    private final BeerInventoryServiceFailoverFeignClient beerInventoryServiceFailoverFeignClient;
    @Override
    public ResponseEntity<List<BeerInventoryDto>> getInventoryOnHand(UUID beerId) {
        return beerInventoryServiceFailoverFeignClient.getInventoryOnHand();
    }
}
