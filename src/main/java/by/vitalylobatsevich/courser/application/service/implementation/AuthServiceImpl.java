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
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final MessageSource messageSource;

    @Override
    public String signin(final SigninRequest signinRequest, final Locale locale) {
        applicationEventPublisher.publishEvent(SigninEvent.signinEventBuilder()
                .locale(locale)
                .user(userRepository.save(User.userBuilder()
                        .email(signinRequest.getEmail())
                        .password(passwordEncoder.encode(signinRequest.getPassword()))
                        .roles(List.of(Role.roleBuilder().id(1L).build()))
                        .build()
                ))
        );

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
    public ResponseEntity<String> changePassword(
            final ChangePasswordRequest changePasswordRequest,
            final String email,
            final Locale locale
    ) {
        val user = userRepository.findByEmail(email)
                .getOrElseThrow(() -> new UsernameNotFoundException(email));
        if (!passwordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body(messageSource.getMessage(
                    "validation.old-password",
                    null,
                    locale
            ));
        }
        userRepository.save(
                user.userUpdater()
                        .password(passwordEncoder.encode(changePasswordRequest.getNewPassword()))
                        .update()
        );
        return ResponseEntity.ok("");
    }

}
