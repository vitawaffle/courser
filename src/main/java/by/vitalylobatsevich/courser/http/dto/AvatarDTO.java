package by.vitalylobatsevich.courser.http.dto;

import lombok.*;

import by.vitalylobatsevich.courser.database.entity.Avatar;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvatarDTO extends DTO {

    private Long id;

    private FileDTO file;

    public AvatarDTO(final Avatar avatar) {
        id = avatar.getId();
        file = new FileDTO(avatar.getFile());
    }

}
