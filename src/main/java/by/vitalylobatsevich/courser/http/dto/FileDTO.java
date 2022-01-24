package by.vitalylobatsevich.courser.http.dto;

import lombok.*;

import by.vitalylobatsevich.courser.database.entity.File;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileDTO extends DTO {

    private Long id;

    private String name;

    public FileDTO(final File file) {
        id = file.getId();
        name = file.getName();
    }

}
