package com.example.practice.msscbrewery.service.inventory.impl;

import com.example.practice.msscbrewery.service.inventory.BeerInventoryServiceClient;
import com.example.practice.msscbrewery.service.inventory.model.BeerInventoryDto;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
@ConfigurationProperties(value = "brewery.inventory", ignoreUnknownFields = false)
public class BeerInventoryServiceRestTemplateClientImpl implements BeerInventoryServiceClient {


    @Setter
    private String apiHost;
    private final String INVENTORY_PATH = "/api/v1/beer/{beerId}/inventory";
    private final RestTemplate restTemplate;


    @Autowired
    public BeerInventoryServiceRestTemplateClientImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    @Override
    public Integer getInventoryOnHand(UUID beerId) {
        log.debug("Calling Inventory Service");
        ResponseEntity<List<BeerInventoryDto>> response = restTemplate.exchange(
            apiHost + INVENTORY_PATH,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<BeerInventoryDto>>() {},
            beerId
        );
        return Objects.requireNonNull(response.getBody())
            .stream()
            .mapToInt(BeerInventoryDto::getQuantityOnHand)
            .sum();
    }
}
