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
@ToString(exclude = "roles")
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

    @Override
    public User updateId(final Long id) {
        super.updateId(id);
        return this;
    }

    public User updateEmail(final String email) {
        this.email = email;
        return this;
    }

    public User updatePassword(final String password) {
        this.password = password;
        return this;
    }

    public User updateRoles(final Collection<Role> roles) {
        this.roles = roles;
        return this;
    }

    public User updateEmailConfirmedAt(final Instant emailConfirmedAt) {
        this.emailConfirmedAt = emailConfirmedAt;
        return this;
    }

}
