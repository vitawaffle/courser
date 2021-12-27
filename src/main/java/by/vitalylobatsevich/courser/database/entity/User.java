package by.vitalylobatsevich.courser.database.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Entity;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
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

    public User(final Long id) {
        super(id);
    }

}
