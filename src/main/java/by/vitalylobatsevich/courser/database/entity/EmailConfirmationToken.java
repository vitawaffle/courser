package by.vitalylobatsevich.courser.database.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "email_confirmation_tokens")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "user")
public class EmailConfirmationToken extends LongIdEntity {

    private String token;

    @OneToOne
    private User user;

    private Instant expirationDate;

    private Instant canBeResend;

    @Builder
    public EmailConfirmationToken(final Long id,
                                  final String token,
                                  final User user,
                                  final Instant expirationDate,
                                  final Instant canBeResend) {
        super(id);
        this.token = token;
        this.user = user;
        this.expirationDate = expirationDate;
        this.canBeResend = canBeResend;
    }

    public EmailConfirmationTokenUpdater updater() {
        return new EmailConfirmationTokenUpdater();
    }

    public class EmailConfirmationTokenUpdater implements Updater<EmailConfirmationToken> {

        public EmailConfirmationTokenUpdater id(final Long id) {
            setId(id);
            return this;
        }

        public EmailConfirmationTokenUpdater token(final String token) {
            EmailConfirmationToken.this.token = token;
            return this;
        }

        public EmailConfirmationTokenUpdater user(final User user) {
            EmailConfirmationToken.this.user = user;
            return this;
        }

        public EmailConfirmationTokenUpdater expirationDate(final Instant expirationDate) {
            EmailConfirmationToken.this.expirationDate = expirationDate;
            return this;
        }

        public EmailConfirmationTokenUpdater canBeResend(final Instant canBeResend) {
            EmailConfirmationToken.this.canBeResend = canBeResend;
            return this;
        }

        @Override
        public EmailConfirmationToken update() {
            return EmailConfirmationToken.this;
        }

    }

}
