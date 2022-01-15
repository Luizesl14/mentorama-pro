package com.movies.controller;

import com.movies.model.User;
import com.movies.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> listAllUser(){
        return this.userService.listAllUser();
    }

    @PostMapping("/new")
    public ResponseEntity addUser(@RequestBody User user){
        jmsTemplate.convertAndSend("saveMailbox", user);
        LOGGER.info("Post-> user --> Menssagem enviada para Queue");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@RequestBody  User user){
        LOGGER.info("Delete-> user --> Menssagem enviada para Queue");
        jmsTemplate.convertAndSend("deleteMailbox", user);
        return ResponseEntity.ok().build();
    }
}