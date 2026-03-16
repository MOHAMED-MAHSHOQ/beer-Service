package com.code.lambok.mapper;

import com.code.lambok.entities.Beer;
import com.code.lambok.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {
    Beer toEntity(BeerDTO dto);

    BeerDTO toDto(Beer beer);

}
