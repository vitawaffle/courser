package by.vitalylobatsevich.courser.database.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
@Data
public class Role extends LongIdEntity {

    private String name;

}
