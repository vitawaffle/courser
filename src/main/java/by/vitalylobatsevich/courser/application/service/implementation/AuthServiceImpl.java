package by.vitalylobatsevich.courser.application.service.implementation;

import by.vitalylobatsevich.courser.application.event.SigninEvent;
import by.vitalylobatsevich.courser.application.helper.Auth;
import by.vitalylobatsevich.courser.application.security.JwtUtils;
import by.vitalylobatsevich.courser.application.service.AuthService;
import by.vitalylobatsevich.courser.database.entity.Role;
import by.vitalylobatsevich.courser.database.entity.User;
import by.vitalylobatsevich.courser.database.repository.UserRepository;
import by.vitalylobatsevich.courser.http.dto.ChangePasswordDTO;
import by.vitalylobatsevich.courser.http.dto.LoginCredentialsDTO;
import by.vitalylobatsevich.courser.http.dto.SigninCredentialsDTO;

import lombok.RequiredArgsConstructor;
import lombok.val;

import org.springframework.context.ApplicationEventPublisher;
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

    @Override
    public User getUser() {
        val username = Auth.getUsername();
        return userRepository.findByEmail(username).getOrElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public String signin(final SigninCredentialsDTO signinCredentialsDTO, final Locale locale) {
        applicationEventPublisher.publishEvent(
                SigninEvent.signinEventBuilder()
                        .locale(locale)
                        .user(
                                userRepository.save(
                                        User.builder()
                                                .email(signinCredentialsDTO.getEmail())
                                                .password(passwordEncoder.encode(signinCredentialsDTO.getPassword()))
                                                .roles(List.of(Role.builder().id(1L).build()))
                                                .build()
                                )
                        )
                        .build()
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
    public void changePassword(final ChangePasswordDTO changePasswordDTO) {
        userRepository.save(
                getUser().updater()
                        .password(passwordEncoder.encode(changePasswordDTO.getNewPassword()))
                        .update()
        );
    }

}
