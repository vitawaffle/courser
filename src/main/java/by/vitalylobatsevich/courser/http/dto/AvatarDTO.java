package by.vitalylobatsevich.courser.http.dto;

import by.vitalylobatsevich.courser.application.validation.ExistingFileId;

import by.vitalylobatsevich.courser.application.validation.IsFileOwner;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvatarDTO extends DTO {

    @ExistingFileId(message = "{validation.existing-file-id}")
    @IsFileOwner(message = "{validation.is-file-owner}")
    private Long fileId;

}
