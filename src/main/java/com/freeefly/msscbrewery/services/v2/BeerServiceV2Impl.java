package com.freeefly.msscbrewery.services.v2;

import com.freeefly.msscbrewery.web.module.v2.BeerDtoV2;
import com.freeefly.msscbrewery.web.module.v2.BeerStyleEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class BeerServiceV2Impl implements BeerServiceV2 {
    @Override
    public BeerDtoV2 getBeerById(UUID beerId) {
        return BeerDtoV2.builder()
                .id(UUID.randomUUID())
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyleEnum.ALE)
                .build();
    }

    @Override
    public BeerDtoV2 saveNewBeer(BeerDtoV2 beerDto) {
        return BeerDtoV2.builder()
                .id(UUID.randomUUID())
                .build();
    }

    @Override
    public void updateBeer(UUID beerId, BeerDtoV2 beerDto) {
        log.debug("Updating a beer...");
        //TODO impl - would add a real impl

    }

    @Override
    public void deleteBeer(UUID beerId) {
        log.debug("Deleting a beer...");
        // TODO impl - would add a real impl
    }
}