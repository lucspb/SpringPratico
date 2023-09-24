package com.github.lucspb.pdv.controller;


import com.github.lucspb.pdv.model.Product;
import com.github.lucspb.pdv.model.User;
import com.github.lucspb.pdv.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/product")
    public ResponseEntity getAll(){
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity post(@RequestBody Product product){
        return new ResponseEntity<>(productRepository.save(product), HttpStatus.CREATED);
    }

    @PutMapping("/product")
    public ResponseEntity put(@RequestBody Product product){
        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        if(optionalProduct.isPresent()){
            productRepository.save(product);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");

    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        productRepository.delete(optionalProduct.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted sucessfully");
    }
}
