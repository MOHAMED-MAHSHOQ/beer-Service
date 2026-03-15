package com.code.lambok.controller;

import com.code.lambok.model.BeerDto;
import com.code.lambok.services.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class BeerController {
    private final BeerService beerService;

    public static final String BEER_PATH="/api/v1/beer";
    public static final String BEER_PATH_ID=BEER_PATH+"/{beerId}";

    @GetMapping(BEER_PATH)
    public List<BeerDto> listBeers(){
        return beerService.listBeers();
    }

    @GetMapping(BEER_PATH_ID)
    public BeerDto getBeerById(@PathVariable UUID beerId){
        return beerService.getBeerById(beerId).orElseThrow(NotFoundException::new);
    }

    @PostMapping(BEER_PATH)
    public ResponseEntity<Void> handlePost(@RequestBody BeerDto beerDto){
        BeerDto savedBeerDto = beerService.saveNewBeer(beerDto);

        HttpHeaders headers = new HttpHeaders();
        headers.add("location",BEER_PATH+"/"+ savedBeerDto.getId().toString());
        return new ResponseEntity<>(headers,HttpStatus.CREATED);
    }

    @PutMapping(BEER_PATH_ID)
    public ResponseEntity<Void> updateById(@PathVariable UUID beerId,@RequestBody BeerDto beerDto){
        beerService.updateBeerById(beerId, beerDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(BEER_PATH_ID)
    public ResponseEntity<Void> deleteById(@PathVariable UUID beerId){
        beerService.deleteById(beerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(BEER_PATH_ID)
    public ResponseEntity<Void> updateBeerPatchById(@PathVariable UUID beerId,@RequestBody BeerDto beerDto){
        beerService.patchBeerById(beerId, beerDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
