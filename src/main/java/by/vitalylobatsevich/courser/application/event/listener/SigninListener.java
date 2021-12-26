package by.vitalylobatsevich.courser.application.event.listener;

import by.vitalylobatsevich.courser.application.event.SigninEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SigninListener implements ApplicationListener<SigninEvent> {

    @Override
    public void onApplicationEvent(final SigninEvent event) {
    }

}
