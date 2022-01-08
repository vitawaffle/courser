package by.vitalylobatsevich.courser.database.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Role extends LongIdEntity {

    private String name;

    @Builder
    public Role(final Long id, final String name) {
        super(id);
        this.name = name;
    }

    public RoleUpdater updater() {
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
