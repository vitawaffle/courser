package by.vitalylobatsevich.courser.application.event.listener;

import by.vitalylobatsevich.courser.application.event.SigninEvent;
import by.vitalylobatsevich.courser.application.service.EmailConfirmationService;

import lombok.RequiredArgsConstructor;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SigninListener implements ApplicationListener<SigninEvent> {

    private final EmailConfirmationService emailConfirmationService;

    @Override
    public void onApplicationEvent(final SigninEvent event) {
        emailConfirmationService.sendConfirmationEmail(event.getUser(), event.getLocale());
    }

}
