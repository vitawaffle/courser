package by.vitalylobatsevich.courser.database.entity;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User extends LongIdEntity {

    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> roles = Collections.emptySet();

    private Instant emailConfirmedAt;

    @Builder(builderMethodName = "userBuilder")
    public User(
            final Long id,
            final String email,
            final String password,
            final Collection<Role> roles,
            final Instant emailConfirmedAt
    ) {
        super(id);
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.emailConfirmedAt = emailConfirmedAt;
    }

    public UserUpdater userUpdater() {
        return new UserUpdater();
    }

    public class UserUpdater implements Updater<User> {

        public UserUpdater id(final Long id) {
            setId(id);
            return this;
        }

        public UserUpdater email(final String email) {
            User.this.email = email;
            return this;
        }

        public UserUpdater password(final String password) {
            User.this.password = password;
            return this;
        }

        public UserUpdater roles(final Collection<Role> roles) {
            User.this.roles = roles;
            return this;
        }

        public UserUpdater emailConfirmedAt(final Instant emailConfirmedAt) {
            User.this.emailConfirmedAt = emailConfirmedAt;
            return this;
        }

        @Override
        public User update() {
            return User.this;
        }

    }

}
