package com.freeefly.msscbrewery.web.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

@JsonTest
class BeerDtoTest extends BaseTest{
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testSerializeDto () throws JsonProcessingException {
        BeerDto beerDto = getDto();
        String jsonString = objectMapper.writeValueAsString(beerDto);
        System.out.println(jsonString);

    }
    @Test
    void testDeserialize () throws JsonProcessingException {
        String json = "{\"id\":\"f30c2c64-3f4d-4cf4-b66b-588d155e2dcb\",\"version\":null,\"createdDate\":\"2021-04-04T16:13:08+0900\",\"lastModifiedDate\":\"2021-04-04T16:13:08.278045+09:00\",\"beerName\":\"beerName\",\"beerStyle\":\"ALE\",\"upc\":312412413136,\"price\":\"12.99\",\"quantityOnHand\":null,\"myLocalDate\":\"20210404\"}\n";
        BeerDto dto = objectMapper.readValue(json, BeerDto.class);
        System.out.println(dto);
    }
}