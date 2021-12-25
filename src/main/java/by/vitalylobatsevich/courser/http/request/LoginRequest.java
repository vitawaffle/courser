package by.vitalylobatsevich.courser.http.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest extends Request {

    @NotNull
    private String email;

    @NotNull
    private String password;

    public LoginRequest(final SigninRequest signinRequest) {
        email = signinRequest.getEmail();
        password = signinRequest.getPassword();
    }

}
