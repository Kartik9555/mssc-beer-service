package com.example.practice.msscbrewery.service.inventory;

import com.example.practice.msscbrewery.service.inventory.model.BeerInventoryDto;
import java.util.List;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.example.practice.msscbrewery.service.inventory.impl.BeerInventoryServiceRestTemplateClientImpl.INVENTORY_PATH;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(name = "mssc-beer-inventory-service", fallback = BeerInventoryServiceFeignClientFailover.class)
public interface BeerInventoryServiceFeignClient {

    @RequestMapping(method = GET, value = INVENTORY_PATH)
    ResponseEntity<List<BeerInventoryDto>> getInventoryOnHand(@PathVariable UUID beerId);
}
