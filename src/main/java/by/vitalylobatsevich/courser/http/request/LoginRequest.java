package by.vitalylobatsevich.courser.http.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "loginRequestBuilder")
public class LoginRequest extends Request {

    @NotNull(message = "{validation.not-null}")
    private String email;

    @NotNull(message = "{validation.not-null}")
    private String password;

    public LoginRequest(final SigninRequest signinRequest) {
        email = signinRequest.getEmail();
        password = signinRequest.getPassword();
    }

}
