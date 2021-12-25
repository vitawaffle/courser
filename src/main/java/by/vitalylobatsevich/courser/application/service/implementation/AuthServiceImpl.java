package by.vitalylobatsevich.courser.application.service.implementation;

import by.vitalylobatsevich.courser.application.security.JwtUtils;
import by.vitalylobatsevich.courser.application.service.AuthService;
import by.vitalylobatsevich.courser.database.entity.Role;
import by.vitalylobatsevich.courser.database.entity.User;
import by.vitalylobatsevich.courser.database.repository.UserRepository;
import by.vitalylobatsevich.courser.http.request.LoginRequest;
import by.vitalylobatsevich.courser.http.request.SigninRequest;

import lombok.RequiredArgsConstructor;
import lombok.val;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Override
    public String signin(final SigninRequest signinRequest) {
        val studentRole = new Role();
        studentRole.setId(1L);

        val user = new User();
        user.setEmail(signinRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signinRequest.getPassword()));
        user.setRoles(Arrays.asList(studentRole));

        userRepository.save(user);

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

}
