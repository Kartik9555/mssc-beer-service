package com.example.practice.msscbrewery.web.controller;

import com.example.practice.model.BeerDTO;
import com.example.practice.msscbrewery.service.BeerService;
import com.example.practice.msscbrewery.web.model.BeerStyleEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerService beerService;

    @Test
    void listBeers() throws Exception {
        mockMvc.perform(get("/api/v1/beer"))
            .andExpect(status().isOk());
    }


    @Test
    void getBeerById() throws Exception {
        UUID beerId = UUID.randomUUID();
        mockMvc.perform(get("/api/v1/beer/"+beerId))
            .andExpect(status().isOk());
    }


    @Test
    void createBeer() throws Exception {
        BeerDTO beerDTO = getBeerDTO();
        given(beerService.createBeer(any(BeerDTO.class))).willReturn(beerDTO);
        mockMvc.perform(post("/api/v1/beer")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(beerDTO))
        ).andExpect(status().isCreated());

    }


    @Test
    void updateBeer() throws Exception{
        UUID beerId = UUID.randomUUID();
        BeerDTO beerDTO = getBeerDTO();
        given(beerService.updateBeer(any(), any())).willReturn(beerDTO);
        mockMvc.perform(put("/api/v1/beer/"+beerId)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(beerDTO))
        ).andExpect(status().isNoContent());
    }

    @Test
    void deleteBeer() throws Exception {
        UUID beerId = UUID.randomUUID();
        mockMvc.perform(delete("/api/v1/beer/"+beerId))
            .andExpect(status().isNoContent());
    }

    private static BeerDTO getBeerDTO() {
        return BeerDTO.builder()
            .beerName("Test Name")
            .beerStyle(BeerStyleEnum.LAGER)
            .upc(String.valueOf(Long.MAX_VALUE))
            .price(BigDecimal.TEN)
            .build();
    }
}