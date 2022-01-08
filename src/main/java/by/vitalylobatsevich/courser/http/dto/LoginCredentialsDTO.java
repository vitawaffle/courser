package by.vitalylobatsevich.courser.http.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginCredentialsDTO extends DTO {

    @NotNull(message = "{validation.not-null}")
    private String email;

    @NotNull(message = "{validation.not-null}")
    private String password;

    public LoginCredentialsDTO(final SigninCredentialsDTO signinRequest) {
        email = signinRequest.getEmail();
        password = signinRequest.getPassword();
    }

}
