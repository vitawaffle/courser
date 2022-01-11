package by.vitalylobatsevich.courser.http.controller;

import by.vitalylobatsevich.courser.application.service.EmailConfirmationService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.time.Instant;

@RestController
@RequestMapping("/api/email-confirmation")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class EmailConfirmationController extends AppRestController {

    private final EmailConfirmationService emailConfirmationService;

    @GetMapping("/confirm")
    public ModelAndView confirm(
            @RequestParam("token") final String token,
            final HttpServletRequest request
    ) {
        return emailConfirmationService.confirmEmail(token, request.getLocale());
    }

    @PostMapping("/resend")
    public ResponseEntity<Object> resend(final HttpServletRequest request) {
        return emailConfirmationService.resendConfirmationEmail(getUsername(), request.getLocale());
    }

    @GetMapping("/can-be-resend")
    public Instant getCanBeResend() {
        return emailConfirmationService.whenCanBeResend(getUsername()).getOrNull();
    }

}
