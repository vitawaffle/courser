package by.vitalylobatsevich.courser.http.controller;

import org.springframework.security.core.context.SecurityContextHolder;

public abstract class AppRestController {

    public String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
