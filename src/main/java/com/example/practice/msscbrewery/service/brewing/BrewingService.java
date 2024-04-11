package com.example.practice.msscbrewery.service.brewing;

import com.example.practice.model.BeerDTO;
import com.example.practice.model.events.BrewBeerEvent;
import com.example.practice.msscbrewery.config.JmsConfig;
import com.example.practice.msscbrewery.domain.Beer;
import com.example.practice.msscbrewery.mapper.BeerMapper;
import com.example.practice.msscbrewery.respositories.BeerRepository;
import com.example.practice.msscbrewery.service.inventory.BeerInventoryServiceClient;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingService {
    private final BeerRepository beerRepository;
    private final BeerInventoryServiceClient beerInventoryServiceClient;
    private final BeerMapper beerMapper;
    private final JmsTemplate jmsTemplate;

    @Scheduled(fixedRate = 5000)
    public void checkLowInventory(){
        List<Beer> beers = beerRepository.findAll();
        beers.forEach(beer -> {
            Integer invQOH = beerInventoryServiceClient.getInventoryOnHand(beer.getId());
            log.debug("Min on hand is: {}", beer.getMinOnHand());
            log.debug("Inventory is: {}", invQOH);
            if (beer.getMinOnHand() >= invQOH){
                BeerDTO beerDTO = beerMapper.beerToBeerDTO(beer);
                BrewBeerEvent brewBeerEvent = new BrewBeerEvent(beerDTO);
                jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE, brewBeerEvent);
            }
        });
    }
}
