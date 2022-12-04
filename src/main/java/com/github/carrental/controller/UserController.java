package com.github.carrental.controller;

import com.github.carrental.model.dto.UserDto;
import com.github.carrental.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    private ResponseEntity<?> createUser(@RequestBody UserDto user) {
        userService.saveUser(user);
        return ResponseEntity.ok("SUCCESS");
    }
}
