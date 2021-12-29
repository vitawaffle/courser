package by.vitalylobatsevich.courser.database.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
public class Role extends LongIdEntity {

    private String name;

    public Role(final Long id, final String name) {
        super(id);
        this.name = name;
    }

    public Role(final Long id) {
        super(id);
    }

    @Override
    public Role updateId(final Long id) {
        super.updateId(id);
        return this;
    }

    public Role updateName(final String name) {
        this.name = name;
        return this;
    }

}
