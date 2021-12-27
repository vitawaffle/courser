package by.vitalylobatsevich.courser.http.controller;

import by.vitalylobatsevich.courser.application.service.EmailConfirmationService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/email-confirmation")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class EmailConfirmationController {

    private final EmailConfirmationService emailConfirmationService;

    @GetMapping("/confirm")
    public ModelAndView confirmEmail(
            @RequestParam("token") final String token,
            final HttpServletRequest request
    ) {
        return emailConfirmationService.confirmEmail(token, request.getLocale());
    }

}
