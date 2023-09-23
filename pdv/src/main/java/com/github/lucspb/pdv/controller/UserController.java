package com.github.lucspb.pdv.controller;

import com.github.lucspb.pdv.model.User;
import com.github.lucspb.pdv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserRepository userRepository;

    public UserController(@Autowired UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping()
    public ResponseEntity getAll(){
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody User user){
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }
}
