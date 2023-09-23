package com.github.lucspb.controller;

import com.github.lucspb.model.User;
import com.github.lucspb.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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
        User user1 = new User();
        BeanUtils.copyProperties(user, user1);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user1));
    }
}
