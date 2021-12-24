package by.vitalylobatsevich.courser.http.request;

import by.vitalylobatsevich.courser.application.validation.Email;
import by.vitalylobatsevich.courser.application.validation.Password;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SigninRequest extends Request {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Password
    private String password;

}
