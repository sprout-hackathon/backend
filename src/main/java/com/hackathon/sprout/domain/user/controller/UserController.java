package com.hackathon.sprout.domain.user.controller;

import com.hackathon.sprout.domain.user.dto.UserLoginRequest;
import com.hackathon.sprout.domain.user.dto.UserRegisterRequest;
import com.hackathon.sprout.domain.user.dto.UserResponse;
import com.hackathon.sprout.domain.user.dto.UserUpdateRequest;
import com.hackathon.sprout.domain.user.service.UserService;
import com.hackathon.sprout.global.jwt.dto.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody UserRegisterRequest userRegisterRequest) {
        userService.register(userRegisterRequest);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
        JwtResponse jwtResponse = userService.login(userLoginRequest);
        return ResponseEntity.status(201).body(jwtResponse);
    }

    @PatchMapping
    public ResponseEntity<Void> updateUser(Authentication authentication, @RequestBody UserUpdateRequest userUpdateRequest) {
        userService.updateUser(authentication, userUpdateRequest);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(Authentication authentication) {
        userService.deleteUser(authentication);
        return ResponseEntity.status(204).build();
    }

    @GetMapping
    public ResponseEntity<UserResponse> getUser(Authentication authentication) {
        UserResponse userResponse = userService.getUser(authentication);
        return ResponseEntity.status(200).body(userResponse);
    }
}