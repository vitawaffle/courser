package by.vitalylobatsevich.courser.database.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Role extends LongIdEntity {

    private String name;

    @Builder(builderMethodName = "roleBuilder")
    public Role(final Long id, final String name) {
        super(id);
        this.name = name;
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
