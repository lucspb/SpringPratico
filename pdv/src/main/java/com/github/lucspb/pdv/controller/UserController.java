package com.github.lucspb.pdv.controller;

import com.github.lucspb.pdv.model.User;
import com.github.lucspb.pdv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public ResponseEntity getAll(){
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity post(@RequestBody User user){
        user.setEnabled(true);
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity put(@RequestBody User user){
        Optional<User> userEdit = userRepository.findById(user.getId());
        if(userEdit.isPresent()){
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            userRepository.delete(optionalUser.get());
            return ResponseEntity.status(HttpStatus.OK).body("User deleted sucessfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");

    }
}
