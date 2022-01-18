package by.vitalylobatsevich.courser.http.dto;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailDTO extends DTO {

    private String email;

}
