package com.example.practice.msscbrewery.service.inventory;

import com.example.practice.msscbrewery.service.inventory.model.BeerInventoryDto;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(name = "mssc-inventory-failover")
public interface BeerInventoryServiceFailoverFeignClient {

    @RequestMapping(method = GET, value = "/inventory-failover")
    ResponseEntity<List<BeerInventoryDto>> getInventoryOnHand();
}
