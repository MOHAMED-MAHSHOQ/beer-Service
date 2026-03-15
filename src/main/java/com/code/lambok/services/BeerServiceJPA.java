package com.code.lambok.services;

import com.code.lambok.mapper.BeerMapper;
import com.code.lambok.model.BeerDto;
import com.code.lambok.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
@Primary
@RequiredArgsConstructor
public class BeerServiceJPA implements BeerService {
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;
    @Override
    public List<BeerDto> listBeers() {
        return List.of();
    }

    @Override
    public Optional<BeerDto> getBeerById(UUID id) {
        return Optional.empty();
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return null;
    }

    @Override
    public void updateBeerById(UUID beerId, BeerDto beerDto) {

    }

    @Override
    public void deleteById(UUID beerId) {

    }

    @Override
    public void patchBeerById(UUID beerId, BeerDto beerDto) {

    }
}
