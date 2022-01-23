package by.vitalylobatsevich.courser.database.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "avatars")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = { "user", "file" })
public class Avatar extends LongIdEntity {

    @ManyToOne
    private User user;

    @ManyToOne
    private File file;

    @Builder
    public Avatar(final Long id, final User user, final File file) {
        super(id);
        this.user = user;
        this.file = file;
    }

    public AvatarUpdater updater() {
        return new AvatarUpdater();
    }

    public class AvatarUpdater implements Updater<Avatar> {

        public AvatarUpdater id(final Long id) {
            setId(id);
            return this;
        }

        public AvatarUpdater user(final User user) {
            Avatar.this.user = user;
            return this;
        }

        public AvatarUpdater file(final File file) {
            Avatar.this.file = file;
            return this;
        }

        @Override
        public Avatar update() {
            return Avatar.this;
        }

    }

    @Override
    public Avatar clone() {
        return Avatar.builder()
                .id(getId())
                .user(user)
                .file(file)
                .build();
    }

}
