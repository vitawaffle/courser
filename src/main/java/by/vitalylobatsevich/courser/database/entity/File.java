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

    private String name;

    @ManyToOne
    private User user;

    @Builder
    public File(final Long id, final String name, final User user) {
        super(id);
        this.name = name;
        this.user = user;
    }

    public FileUpdater updater() {
        return new FileUpdater();
    }

    public class FileUpdater implements Updater<File> {

        public FileUpdater id(final Long id) {
            setId(id);
            return this;
        }

        public FileUpdater name(final String name) {
            File.this.name = name;
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
