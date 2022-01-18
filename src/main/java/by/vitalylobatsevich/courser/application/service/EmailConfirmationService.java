package by.vitalylobatsevich.courser.application.service;

import by.vitalylobatsevich.courser.database.entity.User;

import io.vavr.control.Option;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import java.time.Instant;
import java.util.Locale;

public interface EmailConfirmationService extends AppService {

    void sendConfirmationEmail(User user, Locale locale);

    ModelAndView confirmEmail(String token, Locale locale);

    ResponseEntity<?> resendConfirmationEmail(Locale locale);

    Option<Instant> whenCanBeResend();

}
