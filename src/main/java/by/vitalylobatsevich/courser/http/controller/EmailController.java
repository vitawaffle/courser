package by.vitalylobatsevich.courser.http.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;

import by.vitalylobatsevich.courser.application.service.UserService;
import by.vitalylobatsevich.courser.http.dto.EmailDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/emails")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class EmailController {

    private final UserService userService;

    @PostMapping("/exists")
    public boolean exists(@RequestBody @Valid final EmailDTO emailDTO) {
        return userService.existsByEmail(emailDTO.getEmail());
    }

}
