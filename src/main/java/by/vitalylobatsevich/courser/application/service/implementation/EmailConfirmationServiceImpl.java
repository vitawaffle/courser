package by.vitalylobatsevich.courser.application.service.implementation;

import by.vitalylobatsevich.courser.application.service.EmailConfirmationService;
import by.vitalylobatsevich.courser.application.service.implementation.exception.SendingConfirmationEmailException;
import by.vitalylobatsevich.courser.database.entity.EmailConfirmationToken;
import by.vitalylobatsevich.courser.database.entity.User;
import by.vitalylobatsevich.courser.database.repository.EmailConfirmationTokenRepository;
import by.vitalylobatsevich.courser.database.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;

import java.time.Instant;
import java.util.HashMap;
import java.util.Locale;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailConfirmationServiceImpl implements EmailConfirmationService {

    @Value("${courser.email-confirmation.token.lifetime:86400000}")
    private int emailConfirmationTokenLifetime;

    @Value("${courser.email-confirmation.token.resend-delay:60000}")
    private int emailConfirmationTokenResendDelay;

    @Value("${courser.application-url:http://localhost:8080}")
    private String applicationUrl;

    private final EmailConfirmationTokenRepository emailConfirmationTokenRepository;

    private final UserRepository userRepository;

    private final JavaMailSender mailSender;

    private final MessageSource messageSource;

    private final TemplateEngine templateEngine;

    @Override
    public void sendConfirmationEmail(final User user, final Locale locale) {
        val emailConfirmationToken = emailConfirmationTokenRepository.save(
                new EmailConfirmationToken(
                        null,
                        UUID.randomUUID().toString(),
                        user,
                        Instant.now().plusMillis(emailConfirmationTokenLifetime),
                        Instant.now().plusMillis(emailConfirmationTokenResendDelay)
                )
        );

        val context = new Context();
        context.setVariable(
                "emailConfirmationUrl",
                applicationUrl
                        + "/api/email-confirmation/confirm?token="
                        + emailConfirmationToken.getToken()
        );
        context.setVariable("lang", locale.getLanguage());

        val mimeMessage = mailSender.createMimeMessage();
        val mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        try {
            mimeMessageHelper.setTo(user.getEmail());
            mimeMessageHelper.setSubject(messageSource.getMessage(
                    "email-confirmation.email.subject",
                    null,
                    locale
            ));
            mimeMessageHelper.setText(
                    templateEngine.process("emails/confirmation-email", context),
                    true
            );

            mailSender.send(mimeMessage);
        } catch (final MessagingException messagingException) {
            throw new SendingConfirmationEmailException(
                    "Error of sending verification email message.",
                    messagingException
            );
        }
    }

    @Override
    public ModelAndView confirmEmail(final String token, final Locale locale) {
        val model = new HashMap<String, String>();
        model.put("lang", locale.getLanguage());

        val emailConfirmationToken = emailConfirmationTokenRepository.findByToken(token);
        if (emailConfirmationToken.isEmpty()) {
            model.put("errorMessage", messageSource.getMessage(
                    "email-confirmation.error.invalid-link",
                    null,
                    locale
            ));
            return new ModelAndView("error", model);
        }

        val currentTime = Instant.now();
        if (emailConfirmationToken.get().getExpirationDate().compareTo(currentTime) <= 0) {
            model.put("errorMessage", messageSource.getMessage(
                    "email-confirmation.error.link-timeout",
                    null,
                    locale
            ));
            return new ModelAndView("error", model);
        }

        val user = emailConfirmationToken.get().getUser();
        user.setEmailConfirmedAt(currentTime);
        userRepository.save(user);

        emailConfirmationTokenRepository.delete(emailConfirmationToken.get());

        return new ModelAndView("index", model);
    }

    @Override
    public ResponseEntity<String> resendConfirmationEmail(final String email, final Locale locale) {
        val user = userRepository.findByEmail(email)
                .getOrElseThrow(() -> new UsernameNotFoundException(email));
        val emailConfirmationToken = emailConfirmationTokenRepository.findByUser(user);
        if (!emailConfirmationToken.isEmpty()) {
            if (Instant.now().isBefore(emailConfirmationToken.get().getCanBeResend())) {
                return ResponseEntity.badRequest().body(messageSource.getMessage(
                        "email-confirmation.error.can-be-resend",
                        null,
                        locale
                ));
            }
            emailConfirmationTokenRepository.delete(emailConfirmationToken.get());
        }
        sendConfirmationEmail(user, locale);
        return ResponseEntity.ok("");
    }

}
