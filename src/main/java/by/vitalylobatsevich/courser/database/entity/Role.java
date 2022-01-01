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

    public RoleUpdater roleUpdater() {
        return new RoleUpdater();
    }

    public class RoleUpdater implements Updater<Role> {

        public RoleUpdater id(final Long id) {
            setId(id);
            return this;
        }

        public RoleUpdater name(final String name) {
            Role.this.name = name;
            return this;
        }

        @Override
        public Role update() {
            return Role.this;
        }

    }

}
