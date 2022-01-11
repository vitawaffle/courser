package by.vitalylobatsevich.courser.http.dto;

import by.vitalylobatsevich.courser.application.validation.ExistingLanguageId;
import by.vitalylobatsevich.courser.database.entity.Name;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NameDTO extends DTO {

    private String firstName;

    private String lastName;

    private String patronymic;

    @NotNull(message = "{validation.not-null}")
    @ExistingLanguageId(message = "{validation.existing-language-id}")
    private Long languageId;

    public NameDTO(final Name name) {
        this.firstName = name.getFirstName();
        this.lastName = name.getLastName();
        this.patronymic = name.getPatronymic();
        this.languageId = name.getId().getLanguageId();
    }

}
