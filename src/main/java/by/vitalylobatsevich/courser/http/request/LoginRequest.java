package by.vitalylobatsevich.courser.http.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
