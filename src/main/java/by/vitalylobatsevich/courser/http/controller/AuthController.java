package by.vitalylobatsevich.courser.http.controller;

import by.vitalylobatsevich.courser.application.service.AuthService;
import by.vitalylobatsevich.courser.http.request.LoginRequest;
import by.vitalylobatsevich.courser.http.request.SigninRequest;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signin")
    public String signin(
            @RequestBody @Valid final SigninRequest signinRequest,
            final HttpServletRequest request
    ) {
        return authService.signin(signinRequest, request.getLocale());
    }

    @PostMapping("/login")
    public String login(@RequestBody @Valid final LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

}
