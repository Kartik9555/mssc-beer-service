package com.example.practice.msscbrewery.service.brewing;

import com.example.practice.model.BeerDTO;
import com.example.practice.model.events.BrewBeerEvent;
import com.example.practice.model.events.NewInventoryEvent;
import com.example.practice.msscbrewery.config.JmsConfig;
import com.example.practice.msscbrewery.domain.Beer;
import com.example.practice.msscbrewery.respositories.BeerRepository;
import com.example.practice.msscbrewery.web.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class BrewBeerListener {
    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;

    @Transactional
    @JmsListener(destination = JmsConfig.BREWING_REQUEST_QUEUE)
    public void listen(BrewBeerEvent event){
        BeerDTO beerDTO = event.getBeerDTO();
        Beer beer = beerRepository.findById(beerDTO.getId()).orElseThrow(NotFoundException::new);
        beerDTO.setQuantityOnHand(beer.getQuantityToBrew());

        log.debug("Brewed beer {} : QOH: {}",beer.getMinOnHand(), beerDTO.getQuantityOnHand());
        NewInventoryEvent inventoryEvent = new NewInventoryEvent(beerDTO);
        jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE, inventoryEvent);
    }
}
