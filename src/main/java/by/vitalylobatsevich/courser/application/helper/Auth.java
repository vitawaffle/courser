package by.vitalylobatsevich.courser.application.helper;

import org.springframework.security.core.context.SecurityContextHolder;

public class Auth {

    public static String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
