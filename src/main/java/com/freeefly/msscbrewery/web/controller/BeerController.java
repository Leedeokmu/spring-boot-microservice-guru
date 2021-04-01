package com.freeefly.msscbrewery.web.controller;

import com.freeefly.msscbrewery.repositories.BeerRepository;
import com.freeefly.msscbrewery.web.mappers.BeerMapper;
import com.freeefly.msscbrewery.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {
    private final BeerMapper beerMapper;
    private final BeerRepository beerRepository;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@NotNull @PathVariable UUID beerId) {
        return new ResponseEntity<>(beerMapper.beerToBeerDto(beerRepository.findById(beerId).get()), HttpStatus.OK);
    }

    // POST - create new beer
    @PostMapping
    public ResponseEntity saveNewBeer(@Valid @RequestBody BeerDto beerDto) {
        beerRepository.save(beerMapper.beerDtoToBeer(beerDto));
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeerById(@PathVariable UUID beerId, @Validated @RequestBody BeerDto beerDto) {
        beerRepository.findById(beerId).ifPresent(beer -> {
            beer.setBeerName(beerDto.getBeerName());
            beer.setBeerStyle(beerDto.getBeerStyle().name());
            beer.setPrice(beerDto.getPrice());
            beer.setUpc(beerDto.getUpc());
            beerRepository.save(beer);
        });

        return new ResponseEntity(HttpStatus.NO_CONTENT); // 요청이 성공하였고, 바디는 비었음
    }
}


