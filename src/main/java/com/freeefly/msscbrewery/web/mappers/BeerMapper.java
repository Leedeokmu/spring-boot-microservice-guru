package com.freeefly.msscbrewery.web.mappers;

import com.freeefly.msscbrewery.domain.Beer;
import com.freeefly.msscbrewery.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class}, componentModel = "spring")
public interface BeerMapper {
    BeerDto beerToBeerDto(Beer beer);
    Beer beerDtoToBeer(BeerDto dto);

}
