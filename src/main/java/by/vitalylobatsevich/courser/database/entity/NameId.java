package by.vitalylobatsevich.courser.database.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NameId implements Serializable {

    private Long languageId;

    private Long userId;

}
