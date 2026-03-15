package com.code.lambok.services;

import com.code.lambok.model.BeerDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeerService {
    List<BeerDto> listBeers();

    Optional<BeerDto> getBeerById(UUID id);

    BeerDto saveNewBeer(BeerDto beerDto);

    void updateBeerById(UUID beerId, BeerDto beerDto);

    void deleteById(UUID beerId);

    void patchBeerById(UUID beerId, BeerDto beerDto);
}
