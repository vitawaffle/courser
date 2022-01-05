package by.vitalylobatsevich.courser.database.entity;

import lombok.*;

import javax.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NameId implements Serializable {

    private Long languageId;

    private Long userId;

}
