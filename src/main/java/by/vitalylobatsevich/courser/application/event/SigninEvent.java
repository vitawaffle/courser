package by.vitalylobatsevich.courser.application.event;

import by.vitalylobatsevich.courser.database.entity.User;

import lombok.*;

import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class SigninEvent extends ApplicationEvent {

    private Locale locale;

    private User user;

    @Builder(builderMethodName = "signinEventBuilder")
    public SigninEvent(final Locale locale, final User user) {
        super(user);
        this.locale = locale;
        this.user = user;
    }

}
