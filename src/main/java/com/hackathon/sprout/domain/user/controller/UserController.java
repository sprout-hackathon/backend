package com.hackathon.sprout.domain.user.controller;

import com.hackathon.sprout.domain.user.dto.UserLoginRequest;
import com.hackathon.sprout.domain.user.dto.UserRegisterRequest;
import com.hackathon.sprout.domain.user.service.UserService;
import com.hackathon.sprout.global.jwt.dto.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterRequest userRegisterRequest) {
        userService.register(userRegisterRequest);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest userLoginRequest) {
        JwtResponse jwtResponse = userService.login(userLoginRequest);
        return ResponseEntity.status(201).body(jwtResponse);
    }
}
