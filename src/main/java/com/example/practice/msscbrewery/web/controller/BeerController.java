package com.example.practice.msscbrewery.web.controller;

import com.example.practice.msscbrewery.service.BeerService;
import com.example.practice.msscbrewery.web.model.BeerDTO;
import com.example.practice.msscbrewery.web.model.BeerPagedList;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/beer")
@RequiredArgsConstructor
public class BeerController {

    private final BeerService beerService;

    @GetMapping
    public ResponseEntity<BeerPagedList> listBeers(){
        return ResponseEntity.ok(beerService.listBeers());
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDTO> getBeerById(@PathVariable("beerId") UUID beerId) {
        return ResponseEntity.ok(beerService.getBeerById(beerId));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createBeer(@Validated @RequestBody BeerDTO beerDTO) {
        BeerDTO savedBeer = beerService.createBeer(beerDTO);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(HttpHeaders.LOCATION, "/api/v1/beer/" + savedBeer.getId());
        return ResponseEntity.status(HttpStatus.CREATED).headers(responseHeaders).build();
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<HttpStatus> updateBeer(@PathVariable("beerId") UUID beerId, @Validated @RequestBody BeerDTO beerDTO){
        BeerDTO savedBeer = beerService.updateBeer(beerId, beerDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable("beerId") UUID beerId){
        beerService.deleteBeer(beerId);
    }
}
