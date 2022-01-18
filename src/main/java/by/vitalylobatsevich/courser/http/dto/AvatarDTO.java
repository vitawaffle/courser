package by.vitalylobatsevich.courser.http.dto;

import by.vitalylobatsevich.courser.application.validation.ExistingFileId;
import by.vitalylobatsevich.courser.application.validation.IsFileOwner;
import by.vitalylobatsevich.courser.database.entity.Avatar;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvatarDTO extends DTO {

    private Long id;

    @ExistingFileId(message = "{validation.existing-file-id}")
    @IsFileOwner(message = "{validation.is-file-owner}")
    private Long fileId;

    public AvatarDTO(final Avatar avatar) {
        id = avatar.getId();
        fileId = avatar.getFile().getId();
    }

}
