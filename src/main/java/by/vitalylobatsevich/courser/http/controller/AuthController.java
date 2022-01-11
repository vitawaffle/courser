package by.vitalylobatsevich.courser.http.controller;

import by.vitalylobatsevich.courser.application.service.AuthService;
import by.vitalylobatsevich.courser.http.dto.ChangePasswordDTO;
import by.vitalylobatsevich.courser.http.dto.LoginCredentialsDTO;
import by.vitalylobatsevich.courser.http.dto.SigninCredentialsDTO;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class AuthController extends AppRestController {

    private final AuthService authService;

    @PostMapping("/signin")
    public String signin(
            @RequestBody @Valid final SigninCredentialsDTO signinCredentialsDTO,
            final HttpServletRequest request
    ) {
        return authService.signin(signinCredentialsDTO, request.getLocale());
    }

    @PostMapping("/login")
    public String login(@RequestBody @Valid final LoginCredentialsDTO loginCredentialsDTO) {
        return authService.login(loginCredentialsDTO);
    }

    @PostMapping("/change-password")
    public ResponseEntity<Object> changePassword(
            @RequestBody @Valid final ChangePasswordDTO changePasswordDTO,
            final HttpServletRequest request
    ) {
        return authService.changePassword(changePasswordDTO, getUsername(), request.getLocale());
    }

}
