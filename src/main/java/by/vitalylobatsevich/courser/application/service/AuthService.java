package by.vitalylobatsevich.courser.application.service;

import by.vitalylobatsevich.courser.http.request.LoginRequest;
import by.vitalylobatsevich.courser.http.request.SigninRequest;

public interface AuthService extends Service {

    String signin(SigninRequest signinRequest);

    String login(LoginRequest loginRequest);

}
