package by.vitalylobatsevich.courser.http.dto;

import by.vitalylobatsevich.courser.database.entity.Role;
import by.vitalylobatsevich.courser.database.entity.User;

import lombok.*;

import java.time.Instant;
import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO extends DTO {

    private Long id;

    private String email;

    private Collection<Role> roles;

    private Instant emailConfirmedAt;

    public UserDTO(final User user) {
        id = user.getId();
        email = user.getEmail();
        roles = user.getRoles();
        emailConfirmedAt = user.getEmailConfirmedAt();
    }

}
