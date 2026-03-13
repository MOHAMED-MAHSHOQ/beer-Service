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
@RequestMapping("/api/v1/beer")
public class BeerController {
    private final BeerService beerService;
    @GetMapping
    public List<Beer> listBeers(){
        return beerService.listBeers();
    }

    @GetMapping("{beerId}")
    public Beer getBeerById(@PathVariable UUID beerId){
        return beerService.getBeerById(beerId);
    }

    @PostMapping
    public ResponseEntity<Void> handlePost(@RequestBody Beer beer){
        Beer savedBeer= beerService.saveNewBeer(beer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("location","/api/v1/beer/"+savedBeer.getId().toString());
        return new ResponseEntity<>(headers,HttpStatus.CREATED);
    }

    @PutMapping("{beerId}")
    public ResponseEntity<Void> updateById(@PathVariable UUID beerId,@RequestBody Beer beer){
        beerService.updateBeerById(beerId,beer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{beerId}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID beerId){
        beerService.deleteById(beerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("{beerId}")
    public ResponseEntity<Void> updateBeerPatchById(@PathVariable UUID beerId,@RequestBody Beer beer){
        beerService.patchBeerById(beerId,beer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
