package com.lucspb.appCrypto.controller;

import com.lucspb.appCrypto.dto.CoinDTO;
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
import java.util.Optional;

@RestController
public class CoinController {

    @Autowired
    private CoinRepository coinRepository;

    @Bean
    public Coin init(){
        Coin c1 = new Coin();
        c1.setName("BITCOIN");
        c1.setPrice(new BigDecimal(100));
        c1.setQtd(new BigDecimal(0.0001));
        c1.setDateTime(new Timestamp(System.currentTimeMillis()));

        Coin c2 = new Coin();
        c2.setName("BITCOIN");
        c2.setPrice(new BigDecimal(300));
        c2.setQtd(new BigDecimal(0.0003));
        c2.setDateTime(new Timestamp(System.currentTimeMillis()));

        Coin c3 = new Coin();
        c3.setName("ETHERIUM");
        c3.setPrice(new BigDecimal(500));
        c3.setQtd(new BigDecimal(0.0025));
        c3.setDateTime(new Timestamp(System.currentTimeMillis()));

        Coin c4 = new Coin();
        c4.setName("RIPPLE");
        c4.setPrice(new BigDecimal(600));
        c4.setQtd(new BigDecimal(0.0035));
        c4.setDateTime(new Timestamp(System.currentTimeMillis()));

        Coin c5 = new Coin();
        c5.setName("CARDANO");
        c5.setPrice(new BigDecimal(800));
        c5.setQtd(new BigDecimal(0.0055));
        c5.setDateTime(new Timestamp(System.currentTimeMillis()));

        coinRepository.insert(c1);
        coinRepository.insert(c2);
        coinRepository.insert(c3);
        coinRepository.insert(c4);
        coinRepository.insert(c5);
        return c1;
    }


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
}
