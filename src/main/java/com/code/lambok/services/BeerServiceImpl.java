package com.code.lambok.services;

import com.code.lambok.model.BeerDTO;
import com.code.lambok.model.BeerStyle;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class BeerServiceImpl implements BeerService{
    private final Map<UUID, BeerDTO> beerMap;
    public BeerServiceImpl(){
        this.beerMap = new HashMap<>();
        BeerDTO beerDTO1 = BeerDTO.builder()
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

        BeerDTO beerDTO2 = BeerDTO.builder()
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

        BeerDTO beerDTO3 = BeerDTO.builder()
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

        beerMap.put(beerDTO1.getId(), beerDTO1);
        beerMap.put(beerDTO2.getId(), beerDTO2);
        beerMap.put(beerDTO3.getId(), beerDTO3);
    }

    @Override
    public List<BeerDTO> listBeers(){
        return new ArrayList<>(beerMap.values());
    }

    @Override
    public Optional<BeerDTO> getBeerById(UUID id) {
        return Optional.ofNullable(beerMap.get(id));
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beerDto){
        BeerDTO savedBeerDTO = BeerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName(beerDto.getBeerName())
                .beerStyle(beerDto.getBeerStyle())
                .upc(beerDto.getUpc())
                .quantityOnHand(beerDto.getQuantityOnHand())
                .price(beerDto.getPrice())
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now()).build();

        beerMap.put(savedBeerDTO.getId(), savedBeerDTO);
        return savedBeerDTO;
    }

    @Override
    public Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beerDto) {
        BeerDTO existing =beerMap.get(beerId);
        existing.setBeerName(beerDto.getBeerName());
        existing.setPrice(beerDto.getPrice());
        existing.setUpc(beerDto.getUpc());
        existing.setQuantityOnHand(beerDto.getQuantityOnHand());
        existing.setVersion(beerDto.getVersion());
        beerMap.put(existing.getId(),existing);

        return Optional.of(existing);
    }

    @Override
    public boolean deleteById(UUID beerId) {
        beerMap.remove(beerId);
        return true;
    }

    @Override
    public Optional<BeerDTO> patchBeerById(UUID beerId, BeerDTO beerDto) {
        BeerDTO existing =beerMap.get(beerId);
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
        return null;
    }

}
