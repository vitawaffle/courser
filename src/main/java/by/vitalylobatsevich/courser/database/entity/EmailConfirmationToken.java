package by.vitalylobatsevich.courser.database.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "email_confirmation_tokens")
@Data
public class EmailConfirmationToken extends LongIdEntity {

    private String token;

    @OneToOne
    private User user;

    private Instant expirationDate;

    private Instant canBeResend;

}
