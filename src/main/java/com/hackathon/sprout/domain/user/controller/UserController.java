package com.hackathon.sprout.domain.user.controller;

import com.hackathon.sprout.domain.user.dto.UserLoginRequest;
import com.hackathon.sprout.domain.user.dto.UserRegisterRequest;
import com.hackathon.sprout.domain.user.dto.UserResponse;
import com.hackathon.sprout.domain.user.dto.UserUpdateRequest;
import com.hackathon.sprout.domain.user.service.UserService;
import com.hackathon.sprout.global.jwt.dto.JwtResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Users", description = "User 관련 API")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "회원가입", description = "새로운 사용자를 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "회원가입 성공"),
            @ApiResponse(responseCode = "404", description = "유효하지 않은 국가 코드 또는 언어 코드", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody UserRegisterRequest userRegisterRequest) {
        userService.register(userRegisterRequest);
        return ResponseEntity.status(201).build();
    }

    @Operation(summary = "로그인", description = "사용자가 로그인을 합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "로그인 성공", content = @Content(schema = @Schema(implementation = JwtResponse.class))),
            @ApiResponse(responseCode = "401", description = "비밀번호 틀림", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
        JwtResponse jwtResponse = userService.login(userLoginRequest);
        return ResponseEntity.status(201).body(jwtResponse);
    }

    @Operation(summary = "회원 정보 수정", description = "사용자의 정보를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원 정보 수정 성공"),
            @ApiResponse(responseCode = "404", description = "유효하지 않은 국가 코드 또는 언어 코드", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PatchMapping
    public ResponseEntity<Void> updateUser(Authentication authentication, @RequestBody UserUpdateRequest userUpdateRequest) {
        userService.updateUser(authentication, userUpdateRequest);
        return ResponseEntity.status(200).build();
    }

    @Operation(summary = "회원 탈퇴", description = "사용자의 계정을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "회원 탈퇴 성공"),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping
    public ResponseEntity<Void> deleteUser(Authentication authentication) {
        userService.deleteUser(authentication);
        return ResponseEntity.status(204).build();
    }

    @Operation(summary = "회원 정보 조회", description = "사용자의 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원 정보 조회 성공", content = @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping
    public ResponseEntity<UserResponse> getUser(Authentication authentication) {
        UserResponse userResponse = userService.getUser(authentication);
        return ResponseEntity.status(200).body(userResponse);
    }
}