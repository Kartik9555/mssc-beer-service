package com.example.practice.msscbrewery.mapper;

import com.example.practice.msscbrewery.domain.Beer;
import com.example.practice.msscbrewery.web.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface BeerMapper {
    BeerDTO beerToBeerDTO(Beer beer);
    Beer beerDTOToBeer(BeerDTO beerDTO);

}
