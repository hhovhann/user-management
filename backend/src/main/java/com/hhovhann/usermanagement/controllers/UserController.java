package com.hhovhann.usermanagement.controllers;

import com.hhovhann.usermanagement.entities.User;
import com.hhovhann.usermanagement.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api")
public class UserController {
    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/users")
    @CrossOrigin(origins = "http://localhost:5000")
    public List<?> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/user/{id}")
    @CrossOrigin(origins = "http://localhost:5000")
    ResponseEntity<?> getUserById(@PathVariable final Long id) {
        return userService.findUserByUserId(id).map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:5000")
    public ResponseEntity<?> addUsers(@RequestBody final User user) {
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    @CrossOrigin(origins = "http://localhost:5000")
    public ResponseEntity<Void> deleteUser(@PathVariable final Long id) {
        log.debug("REST request to delete User : {}", id);
        userService.deleteUserById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
