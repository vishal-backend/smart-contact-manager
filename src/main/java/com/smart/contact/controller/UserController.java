package com.smart.contact.controller;

import com.smart.contact.dto.UserDTO;
import com.smart.contact.entity.User;
import com.smart.contact.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<Page<UserDTO>> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean asc){

        return ResponseEntity.ok(userService.getUser(page, size, sortBy, asc));
    }

    @PostMapping
    public ResponseEntity<User> addUser(@Valid @RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.addUser(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @Valid @RequestBody User user,
            @PathVariable int id){

        return ResponseEntity.ok(userService.updateUser(id, user));
    }
}