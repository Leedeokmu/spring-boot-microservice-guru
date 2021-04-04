package com.freeefly.msscbrewery.bootstrap;

import com.freeefly.msscbrewery.domain.Beer;
import com.freeefly.msscbrewery.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
@RequiredArgsConstructor
public class BeerLoader implements CommandLineRunner {
    private final BeerRepository beerRepository;

    public static final String BEER_1_UPC = "01928739120036";
    public static final String BEER_2_UPC = "01928739120019";
    public static final String BEER_3_UPC = "00928739124313";
    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }

    private void loadBeerObjects() {
        if(beerRepository.count() == 0){
            beerRepository.save(Beer.builder()
                    .beerName("Mango Bobs")
                    .beerStyle("IPA")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(BEER_1_UPC)
                    .price(new BigDecimal("12.95"))
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Galaxy Cat")
                    .beerStyle("PALE_AXE")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(BEER_2_UPC)
                    .price(new BigDecimal("11.95"))
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("No Hammers On the Bar")
                    .beerStyle("PALE_AXE")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(BEER_3_UPC)
                    .price(new BigDecimal("11.80"))
                    .build());
        }
        log.debug("Loaded Beers : " + beerRepository.count());
    }
}
