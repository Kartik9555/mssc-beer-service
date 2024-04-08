package com.example.practice.msscbrewery.service.impl;

import com.example.practice.msscbrewery.domain.Beer;
import com.example.practice.msscbrewery.mapper.BeerMapper;
import com.example.practice.msscbrewery.respositories.BeerRepository;
import com.example.practice.msscbrewery.service.BeerService;
import com.example.practice.msscbrewery.web.exception.NotFoundException;
import com.example.practice.msscbrewery.web.model.BeerDTO;
import com.example.practice.msscbrewery.web.model.BeerPagedList;
import com.example.practice.msscbrewery.web.model.BeerStyleEnum;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDTO getBeerById(UUID beerId, Boolean showInventoryOnHand) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
        return showInventoryOnHand ? beerMapper.beerToBeerDTOWithInventory(beer) : beerMapper.beerToBeerDTO(beer);
    }


    @Override
    public BeerDTO createBeer(BeerDTO beerDTO) {
        return beerMapper.beerToBeerDTO(
            beerRepository.save(beerMapper.beerDTOToBeer(beerDTO))
        );
    }


    @Override
    public BeerDTO updateBeer(UUID beerId, BeerDTO beerDTO) {
        Beer savedBeer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
        savedBeer.setBeerName(beerDTO.getBeerName());
        savedBeer.setBeerStyle(String.valueOf(beerDTO.getBeerStyle()));
        savedBeer.setPrice(beerDTO.getPrice());
        savedBeer.setUpc(beerDTO.getUpc());
        return beerMapper.beerToBeerDTO(savedBeer);
    }


    @Override
    public void deleteBeer(UUID beerId) {
        Beer savedBeer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
        beerRepository.delete(savedBeer);
    }


    @Override
    public BeerPagedList listBeers(Integer pageNumber, Integer pageSize, String beerName, BeerStyleEnum beerStyle, Boolean showInventoryOnHand) {
        Page<Beer> beerPage;
        BeerPagedList beerPagedList;
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        if(StringUtils.hasText(beerName) && beerStyle != null){
            beerPage = beerRepository.findBeersByBeerNameAndBeerStyle(beerName, beerStyle.name(), pageRequest);
        } else if(StringUtils.hasText(beerName) && beerStyle == null) {
            beerPage = beerRepository.findBeersByBeerName(beerName, pageRequest);
        }
        else if (!StringUtils.hasText(beerName) && beerStyle != null) {
            beerPage = beerRepository.findBeersByBeerStyle(beerStyle.name(), pageRequest);
        } else {
            beerPage = beerRepository.findAll(pageRequest);
        }
        beerPagedList = new BeerPagedList(
            beerPage.getContent()
                .stream()
                .map(showInventoryOnHand ? beerMapper::beerToBeerDTOWithInventory : beerMapper::beerToBeerDTO)
                .toList(),
            PageRequest.of(beerPage.getPageable().getPageNumber(), beerPage.getPageable().getPageSize()),
            beerPage.getTotalElements()
        );
        return beerPagedList;
    }
}
