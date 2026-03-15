package com.code.lambok.controller;

import com.code.lambok.model.Beer;
import com.code.lambok.services.BeerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    public List<Beer> listBeers(){
        return beerService.listBeers();
    }

    @GetMapping(BEER_PATH_ID)
    public Beer getBeerById(@PathVariable UUID beerId){
        return beerService.getBeerById(beerId).orElseThrow(NotFoundException::new);
    }

    @PostMapping(BEER_PATH)
    public ResponseEntity<Void> handlePost(@RequestBody Beer beer){
        Beer savedBeer= beerService.saveNewBeer(beer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("location",BEER_PATH+"/"+savedBeer.getId().toString());
        return new ResponseEntity<>(headers,HttpStatus.CREATED);
    }

    @PutMapping(BEER_PATH_ID)
    public ResponseEntity<Void> updateById(@PathVariable UUID beerId,@RequestBody Beer beer){
        beerService.updateBeerById(beerId,beer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(BEER_PATH_ID)
    public ResponseEntity<Void> deleteById(@PathVariable UUID beerId){
        beerService.deleteById(beerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(BEER_PATH_ID)
    public ResponseEntity<Void> updateBeerPatchById(@PathVariable UUID beerId,@RequestBody Beer beer){
        beerService.patchBeerById(beerId,beer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
