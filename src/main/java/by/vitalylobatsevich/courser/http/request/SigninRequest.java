package by.vitalylobatsevich.courser.http.request;

import by.vitalylobatsevich.courser.application.validation.Email;
import by.vitalylobatsevich.courser.application.validation.Password;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SigninRequest extends Request {

    @NotBlank(message = "{validation.not-blank}")
    @Email(message = "{validation.email}")
    private String email;

    @NotBlank(message = "{validation.not-blank}")
    @Password(message = "{validation.password}")
    private String password;

}
