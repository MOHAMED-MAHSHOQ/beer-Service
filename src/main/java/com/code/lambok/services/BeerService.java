package com.code.lambok.services;

import com.code.lambok.model.BeerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeerService {
    List<BeerDTO> listBeers();

    Optional<BeerDTO> getBeerById(UUID id);

    BeerDTO saveNewBeer(BeerDTO beerDto);

    Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beerDto);

    boolean deleteById(UUID beerId);

    Optional<BeerDTO> patchBeerById(UUID beerId, BeerDTO beerDto);
}
