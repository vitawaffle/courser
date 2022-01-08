package by.vitalylobatsevich.courser.http.request;

import by.vitalylobatsevich.courser.application.validation.Email;
import by.vitalylobatsevich.courser.application.validation.Password;
import by.vitalylobatsevich.courser.application.validation.UniqueEmail;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SigninCredentialsDTO extends DTO {

    @NotBlank(message = "{validation.not-blank}")
    @Email(message = "{validation.email}")
    @UniqueEmail(message = "{validation.unique-email}")
    private String email;

    @NotBlank(message = "{validation.not-blank}")
    @Password(message = "{validation.password}")
    private String password;

}
