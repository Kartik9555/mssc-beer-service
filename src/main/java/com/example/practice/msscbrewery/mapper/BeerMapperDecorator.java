package com.example.practice.msscbrewery.mapper;

import com.example.practice.model.BeerDTO;
import com.example.practice.msscbrewery.domain.Beer;
import com.example.practice.msscbrewery.service.inventory.BeerInventoryServiceClient;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BeerMapperDecorator implements BeerMapper {
    private BeerInventoryServiceClient beerInventoryServiceClient;
    private BeerMapper beerMapper;


    @Autowired
    public void setBeerInventoryServiceClient(BeerInventoryServiceClient beerInventoryServiceClient) {
        this.beerInventoryServiceClient = beerInventoryServiceClient;
    }


    @Autowired
    public void setBeerMapper(BeerMapper beerMapper) {
        this.beerMapper = beerMapper;
    }

    @Override
    public BeerDTO beerToBeerDTO(Beer beer){
        return beerMapper.beerToBeerDTO(beer);
    }

    @Override
    public BeerDTO beerToBeerDTOWithInventory(Beer beer){
        BeerDTO beerDTO = beerMapper.beerToBeerDTO(beer);
        beerDTO.setQuantityOnHand(beerInventoryServiceClient.getInventoryOnHand(beer.getId()));
        return beerDTO;
    }

    @Override
    public Beer beerDTOToBeer(BeerDTO beerDTO){
        return beerMapper.beerDTOToBeer(beerDTO);
    }
}
