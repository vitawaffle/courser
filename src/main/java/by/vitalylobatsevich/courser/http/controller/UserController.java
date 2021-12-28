package by.vitalylobatsevich.courser.http.controller;

import by.vitalylobatsevich.courser.application.service.UserService;
import by.vitalylobatsevich.courser.database.entity.User;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public User getMe() {
        return userService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .getOrNull();
    }

}
