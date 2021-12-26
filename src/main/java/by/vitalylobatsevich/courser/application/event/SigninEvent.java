package by.vitalylobatsevich.courser.application.event;

import by.vitalylobatsevich.courser.database.entity.User;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class SigninEvent extends ApplicationEvent {

    private Locale locale;

    private User user;

    public SigninEvent(final Locale locale, final User user) {
        super(user);
        this.locale = locale;
        this.user = user;
    }

}
