package com.freeefly.msscbreweryclient.web.client;

import com.freeefly.msscbreweryclient.web.model.BeerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class BreweryClientTest {
    @Autowired
    BreweryClient breweryClient;

    @Test
    void getBeerById () {
        // given
        BeerDto dto = breweryClient.getBeerById(UUID.randomUUID());
        assertNotNull(dto);
    }

    @Test
    void testSaveNewBeer () {
        // given
        BeerDto beerDto = BeerDto.builder()
                .beerName("New Beer")
                .build();
        // when
        HttpStatus statusCode = breweryClient.saveNewBeer(beerDto).getStatusCode();
        assertEquals(HttpStatus.CREATED, statusCode);
    }

    @Test
    void testUpdateBeer () {
        // given
        BeerDto beerDto = BeerDto.builder().beerName("New Beer").build();
        // when
        breweryClient.updateBeer(UUID.randomUUID(), beerDto);
        // then

    }
    
    @Test
    public void testDeleteBeer () {
        // given
        breweryClient.deleteBeer(UUID.randomUUID());
        // when
        
        // then
    
    }

}
