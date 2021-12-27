package by.vitalylobatsevich.courser.application.service.implementation;

import by.vitalylobatsevich.courser.application.event.SigninEvent;
import by.vitalylobatsevich.courser.application.security.JwtUtils;
import by.vitalylobatsevich.courser.application.service.AuthService;
import by.vitalylobatsevich.courser.database.entity.Role;
import by.vitalylobatsevich.courser.database.entity.User;
import by.vitalylobatsevich.courser.database.repository.UserRepository;
import by.vitalylobatsevich.courser.http.request.ChangePasswordRequest;
import by.vitalylobatsevich.courser.http.request.LoginRequest;
import by.vitalylobatsevich.courser.http.request.SigninRequest;

import lombok.RequiredArgsConstructor;
import lombok.val;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public String signin(final SigninRequest signinRequest, final Locale locale) {
        applicationEventPublisher.publishEvent(new SigninEvent(locale, userRepository.save(new User(
                null,
                signinRequest.getEmail(),
                passwordEncoder.encode(signinRequest.getPassword()),
                Arrays.asList(new Role(1L)),
                null
        ))));

        return login(new LoginRequest(signinRequest));
    }

    @Override
    public String login(final LoginRequest loginRequest) {
        val authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtUtils.generate(authentication);
    }

    @Override
    public void changePassword(final ChangePasswordRequest changePasswordRequest) {
    }

}
