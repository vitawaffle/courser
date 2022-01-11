package by.vitalylobatsevich.courser.database.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "files")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "user")
public class File extends LongIdEntity {

    private String path;

    @ManyToOne
    private User user;

    @Builder
    public File(final Long id, final String path, final User user) {
        super(id);
        this.path = path;
        this.user = user;
    }

    public FileUpdater updater() {
        return new FileUpdater();
    }

    public class FileUpdater implements Updater {

        public FileUpdater id(final Long id) {
            setId(id);
            return this;
        }

        public FileUpdater path(final String path) {
            File.this.path = path;
            return this;
        }

        public FileUpdater user(final User user) {
            File.this.user = user;
            return this;
        }

        @Override
        public File update() {
            return File.this;
        }

    }

}
