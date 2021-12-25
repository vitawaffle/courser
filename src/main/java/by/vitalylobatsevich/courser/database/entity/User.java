package by.vitalylobatsevich.courser.database.entity;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.Entity;

import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "users")
@Data
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

}
