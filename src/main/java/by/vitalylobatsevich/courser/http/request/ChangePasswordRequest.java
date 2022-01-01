package by.vitalylobatsevich.courser.http.request;

import by.vitalylobatsevich.courser.application.validation.Password;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequest extends Request {

    @NotNull(message = "{validation.not-null}")
    private String oldPassword;

    @NotBlank(message = "{validation.not-blank}")
    @Password(message = "{validation.password}")
    private String newPassword;

}
