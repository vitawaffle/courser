package by.vitalylobatsevich.courser.application.service;

import by.vitalylobatsevich.courser.database.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

public interface EmailConfirmationService extends Service {

    void sendConfirmationEmail(User user, Locale locale);

    ModelAndView confirmEmail(String token, Locale locale);

    ResponseEntity<String> resendConfirmationEmail(String email, Locale locale);

}
