package by.vitalylobatsevich.courser.application.service;

import by.vitalylobatsevich.courser.http.request.LoginRequest;
import by.vitalylobatsevich.courser.http.request.SigninRequest;

import java.util.Locale;

public interface AuthService extends Service {

    String signin(SigninRequest signinRequest, Locale locale);

    String login(LoginRequest loginRequest);

}
