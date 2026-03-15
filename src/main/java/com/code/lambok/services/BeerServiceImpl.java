package com.code.lambok.services;

import com.code.lambok.model.BeerDto;
import com.code.lambok.model.BeerStyle;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class BeerServiceImpl implements BeerService{
    private final Map<UUID, BeerDto> beerMap;
    public BeerServiceImpl(){
        this.beerMap = new HashMap<>();
        BeerDto beerDto1 = BeerDto.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("123456")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(122)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        BeerDto beerDto2 = BeerDto.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Crank")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("123456")
                .price(new BigDecimal("11.99"))
                .quantityOnHand(392)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        BeerDto beerDto3 = BeerDto.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Sunshine City")
                .beerStyle(BeerStyle.IPA)
                .upc("123456")
                .price(new BigDecimal("13.99"))
                .quantityOnHand(144)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        beerMap.put(beerDto1.getId(), beerDto1);
        beerMap.put(beerDto2.getId(), beerDto2);
        beerMap.put(beerDto3.getId(), beerDto3);
    }

    @Override
    public List<BeerDto> listBeers(){
        return new ArrayList<>(beerMap.values());
    }

    @Override
    public Optional<BeerDto> getBeerById(UUID id) {
        return Optional.of(beerMap.get(id));
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto){
        BeerDto savedBeerDto = BeerDto.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName(beerDto.getBeerName())
                .beerStyle(beerDto.getBeerStyle())
                .upc(beerDto.getUpc())
                .quantityOnHand(beerDto.getQuantityOnHand())
                .price(beerDto.getPrice())
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now()).build();

        beerMap.put(savedBeerDto.getId(), savedBeerDto);
        return savedBeerDto;
    }

    @Override
    public void updateBeerById(UUID beerId, BeerDto beerDto) {
        BeerDto existing =beerMap.get(beerId);
        existing.setBeerName(beerDto.getBeerName());
        existing.setPrice(beerDto.getPrice());
        existing.setUpc(beerDto.getUpc());
        existing.setQuantityOnHand(beerDto.getQuantityOnHand());
        existing.setVersion(beerDto.getVersion());
        beerMap.put(existing.getId(),existing);
    }

    @Override
    public void deleteById(UUID beerId) {
        beerMap.remove(beerId);
    }

    @Override
    public void patchBeerById(UUID beerId, BeerDto beerDto) {
        BeerDto existing =beerMap.get(beerId);
        if(StringUtils.hasText(beerDto.getBeerName())){
           existing.setBeerName(beerDto.getBeerName());
        }

        if(beerDto.getPrice() != null){
            existing.setPrice(beerDto.getPrice());
        }

        if(beerDto.getQuantityOnHand() != null){
            existing.setQuantityOnHand(beerDto.getQuantityOnHand());
        }

        if(StringUtils.hasText(beerDto.getUpc())){
            existing.setUpc(beerDto.getUpc());
        }
    }

}
