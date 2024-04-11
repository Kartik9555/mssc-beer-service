package com.example.practice.msscbrewery.mapper;

import com.example.practice.model.BeerDTO;
import com.example.practice.msscbrewery.domain.Beer;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = DateMapper.class)
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {
    @Mapping(target = "quantityOnHand", source = "minOnHand")
    BeerDTO beerToBeerDTO(Beer beer);
    BeerDTO beerToBeerDTOWithInventory(Beer beer);
    @Mapping(target = "minOnHand", source = "quantityOnHand")
    Beer beerDTOToBeer(BeerDTO beerDTO);
}
