package by.vitalylobatsevich.courser.application.service;

import by.vitalylobatsevich.courser.database.entity.User;
import by.vitalylobatsevich.courser.http.dto.ChangePasswordDTO;
import by.vitalylobatsevich.courser.http.dto.LoginCredentialsDTO;
import by.vitalylobatsevich.courser.http.dto.SigninCredentialsDTO;

import java.util.Locale;

public interface AuthService extends AppService {

    User getUser();

    String signin(SigninCredentialsDTO signinCredentialsDTO, Locale locale);

    String login(LoginCredentialsDTO loginCredentialsDTO);

    void changePassword(ChangePasswordDTO changePasswordDTO);

}
