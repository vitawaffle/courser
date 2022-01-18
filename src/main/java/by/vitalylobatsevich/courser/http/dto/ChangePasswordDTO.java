package by.vitalylobatsevich.courser.http.dto;

import by.vitalylobatsevich.courser.application.validation.OldPassword;
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
    @OldPassword(message = "{validation.old-password}")
    private String oldPassword;

    @NotBlank(message = "{validation.not-blank}")
    @Password(message = "{validation.password}")
    private String newPassword;

}
