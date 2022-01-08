package by.vitalylobatsevich.courser.application.service;

import by.vitalylobatsevich.courser.http.dto.ChangePasswordDTO;
import by.vitalylobatsevich.courser.http.dto.LoginCredentialsDTO;
import by.vitalylobatsevich.courser.http.dto.SigninCredentialsDTO;

import org.springframework.http.ResponseEntity;

import java.util.Locale;

public interface AuthService extends Service {

    String signin(SigninCredentialsDTO signinCredentialsDTO, Locale locale);

    String login(LoginCredentialsDTO loginCredentialsDTO);

    ResponseEntity<Object> changePassword(ChangePasswordDTO changePasswordDTO, String email, Locale locale);

}
