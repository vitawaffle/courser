package by.vitalylobatsevich.courser.database.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "email_confirmation_tokens")
@Data
@NoArgsConstructor
public class EmailConfirmationToken extends LongIdEntity {

    private String token;

    @OneToOne
    private User user;

    private Instant expirationDate;

    private Instant canBeResend;

    public EmailConfirmationToken(
            final Long id,
            final String token,
            final User user,
            final Instant expirationDate,
            final Instant canBeResend
    ) {
        super(id);
        this.token = token;
        this.user = user;
        this.expirationDate = expirationDate;
        this.canBeResend = canBeResend;
    }

    public EmailConfirmationToken(final Long id) {
        super(id);
    }

    @Override
    public EmailConfirmationToken updateId(final Long id) {
        super.updateId(id);
        return this;
    }

    public EmailConfirmationToken updateToken(final String token) {
        this.token = token;
        return this;
    }

    public EmailConfirmationToken updateUser(final User user) {
        this.user = user;
        return this;
    }

    public EmailConfirmationToken updateExpirationDate(final Instant expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public EmailConfirmationToken updateCanBeResend(final Instant canBeResend) {
        this.canBeResend = canBeResend;
        return this;
    }

}
