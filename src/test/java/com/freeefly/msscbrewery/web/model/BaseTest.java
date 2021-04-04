package com.freeefly.msscbrewery.web.model;

import com.freeefly.msscbrewery.bootstrap.BeerLoader;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

public class BaseTest {
    BeerDto getDto() {
        return BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("beerName")
                .beerStyle(BeerStyleEnum.ALE)
                .createdDate(OffsetDateTime.now())
                .lastModifiedDate(OffsetDateTime.now())
                .price(new BigDecimal("12.99"))
                .upc(BeerLoader.BEER_1_UPC)
                .myLocalDate(LocalDate.now())
                .build();

    }
}
