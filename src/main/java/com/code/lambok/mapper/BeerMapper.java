package com.code.lambok.mapper;

import com.code.lambok.entities.Beer;
import com.code.lambok.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {
    Beer beerDtoToBeer(BeerDto dto);

    BeerDto beerToBeerDto(Beer beer);

}
