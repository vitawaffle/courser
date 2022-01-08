package by.vitalylobatsevich.courser.application.service;

import by.vitalylobatsevich.courser.http.request.ChangePasswordDTO;
import by.vitalylobatsevich.courser.http.request.LoginCredentialsDTO;
import by.vitalylobatsevich.courser.http.request.SigninCredentialsDTO;

import org.springframework.http.ResponseEntity;

import java.util.Locale;

public interface AuthService extends Service {

    String signin(SigninCredentialsDTO signinCredentialsDTO, Locale locale);

    String login(LoginCredentialsDTO loginCredentialsDTO);

    ResponseEntity<Object> changePassword(ChangePasswordDTO changePasswordDTO, String email, Locale locale);

}
