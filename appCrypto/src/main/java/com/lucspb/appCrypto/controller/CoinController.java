package com.lucspb.appCrypto.controller;


import com.lucspb.appCrypto.entity.Coin;
import com.lucspb.appCrypto.repository.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@RestController
public class CoinController {

    @Autowired
    private CoinRepository coinRepository;

    @GetMapping("/coin")
    public ResponseEntity get(){
        return ResponseEntity.status(HttpStatus.OK).body(coinRepository.getAll());
    }

    @GetMapping("/coin/{name}")
    public ResponseEntity get(@PathVariable String name){
        List<Coin> coinList = coinRepository.getByName(name);
        if(coinList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Coin not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(coinRepository.getByName(name));
    }

    @PostMapping("/coin")
    public ResponseEntity post(@RequestBody Coin coin){
        coin.setDateTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.status(HttpStatus.CREATED).body(coinRepository.insert(coin));
    }

    @DeleteMapping("/coin/{id}")
    public ResponseEntity delete(@PathVariable int id){
        List<Coin> novaCoin = coinRepository.getById(id);
        if(novaCoin.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Coin not found");
        }
        coinRepository.remove(id);
        return ResponseEntity.status(HttpStatus.OK).body("Coin deleted sucessfully");
    }

    @PutMapping("/coin")
    public ResponseEntity put(@RequestBody Coin coin){
        List<Coin> novaCoin = coinRepository.getById(coin.getId());
        if(novaCoin.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Coin not found");
        }
        coin.setDateTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.status(HttpStatus.OK).body(coinRepository.update(coin));
    }

}
