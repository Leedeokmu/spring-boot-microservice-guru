package com.freeefly.msscbrewery.web.controller;

import com.freeefly.msscbrewery.services.BeerService;
import com.freeefly.msscbrewery.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {
    private final BeerService beerService;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID beerId) {
        return new ResponseEntity<>(BeerDto.builder().build(), HttpStatus.OK);
    }

    // POST - create new beer
    @PostMapping
    public ResponseEntity saveNewBeer(@RequestBody BeerDto beerDto) {
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeerById(@PathVariable UUID beerId, @RequestBody BeerDto beerDto) {
        return new ResponseEntity(HttpStatus.NO_CONTENT); // 요청이 성공하였고, 바디는 비었음
    }
}


