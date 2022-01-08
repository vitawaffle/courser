package by.vitalylobatsevich.courser.http.request;

import by.vitalylobatsevich.courser.application.validation.Password;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangePasswordDTO extends DTO {

    @NotNull(message = "{validation.not-null}")
    private String oldPassword;

    @NotBlank(message = "{validation.not-blank}")
    @Password(message = "{validation.password}")
    private String newPassword;

}
