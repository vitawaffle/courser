package by.vitalylobatsevich.courser.application.service.implementation;

import by.vitalylobatsevich.courser.application.event.SigninEvent;
import by.vitalylobatsevich.courser.application.security.JwtUtils;
import by.vitalylobatsevich.courser.application.service.AuthService;
import by.vitalylobatsevich.courser.database.entity.Role;
import by.vitalylobatsevich.courser.database.entity.User;
import by.vitalylobatsevich.courser.database.repository.UserRepository;
import by.vitalylobatsevich.courser.http.dto.ChangePasswordDTO;
import by.vitalylobatsevich.courser.http.dto.LoginCredentialsDTO;
import by.vitalylobatsevich.courser.http.dto.SigninCredentialsDTO;

import io.vavr.collection.HashMap;

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
    public String signin(final SigninCredentialsDTO signinCredentialsDTO, final Locale locale) {
        applicationEventPublisher.publishEvent(
                SigninEvent.signinEventBuilder().locale(locale).user(
                        userRepository.save(User.builder()
                                .email(signinCredentialsDTO.getEmail())
                                .password(passwordEncoder.encode(signinCredentialsDTO.getPassword()))
                                .roles(List.of(Role.builder().id(1L).build())).build())
                )
        );

        return login(new LoginCredentialsDTO(signinCredentialsDTO));
    }

    @Override
    public String login(final LoginCredentialsDTO loginCredentialsDTO) {
        val authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginCredentialsDTO.getEmail(),
                loginCredentialsDTO.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtUtils.generate(authentication);
    }

    @Override
    public ResponseEntity<Object> changePassword(
            final ChangePasswordDTO changePasswordDTO,
            final String email,
            final Locale locale
    ) {
        val user = userRepository.findByEmail(email).getOrElseThrow(() -> new UsernameNotFoundException(email));

        if (!passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body(HashMap.of("oldPassword", messageSource.getMessage(
                    "validation.old-password",
                    null,
                    locale
            )));
        }

        userRepository.save(
                user.updater()
                        .password(passwordEncoder.encode(changePasswordDTO.getNewPassword()))
                        .update()
        );

        return ResponseEntity.ok(null);
    }

}
