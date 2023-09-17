package com.example.class_studen.controller;

import com.example.class_studen.dto.*;
import com.example.class_studen.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "user")
public class UserController {
    private final UserService userService;

    @PostMapping(value = "/register")
    public ResponseDto<UserDto> register(@RequestBody @Valid UserDto userDto) {
        return this.userService.register(userDto);
    }

    @PostMapping(value = "/register-confirm")
    public ResponseDto<TokenResponseDto> registerConfirm(@RequestBody RegisterConfirmDto dto) {
        return this.userService.registerConfirm(dto);
    }

    @PostMapping(value = "/login")
    public ResponseDto<TokenResponseDto> login(@RequestBody LoginDto loginDto) {
        return this.userService.login(loginDto);
    }

    @PostMapping(value = "/logOut")
    public ResponseDto<TokenResponseDto> logOut(@RequestBody LoginDto dto) {
        return this.userService.logOut(dto);
    }

    @GetMapping(value = "/refresh-token")
    public ResponseDto<TokenResponseDto> refreshToken(@RequestParam String token) {
        return this.userService.refreshToken(token);
    }

}
