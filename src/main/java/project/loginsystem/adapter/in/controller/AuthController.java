package project.loginsystem.adapter.in.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.loginsystem.application.dto.AuthRequestDto;
import project.loginsystem.application.dto.AuthResponseDto;
import project.loginsystem.application.dto.UserDto;
import project.loginsystem.application.service.AuthService;
import project.loginsystem.application.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody AuthRequestDto request) {
        UserDto user = userService.register(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto request) {
        String token = authService.authenticate(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(new AuthResponseDto(token));
    }
}
