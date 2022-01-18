package by.vitalylobatsevich.courser.http.controller;

import by.vitalylobatsevich.courser.application.service.AuthService;
import by.vitalylobatsevich.courser.http.dto.UserDTO;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;

    @GetMapping("/me")
    public UserDTO getMe() {
        return new UserDTO(authService.getUser());
    }

}
