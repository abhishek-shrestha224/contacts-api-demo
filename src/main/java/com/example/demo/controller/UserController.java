package com.example.demo.controller;


import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody User user) {
        User userFind = userService.findByUserName(user.getUserName());
        if (userFind != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse<>("User Already Exists.", null));
        }
        userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("User Created Successfully.", user));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<User>> updateUser(@RequestBody User user) {
        User userFind = userService.findByUserName(user.getUserName());
        if (userFind == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse<>("Username Not Found", null));
        }
        userFind.setUserName(user.getUserName());
        userFind.setPassword(user.getPassword());
        userService.saveUser(userFind);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Password Updated", userFind));

    }
}
