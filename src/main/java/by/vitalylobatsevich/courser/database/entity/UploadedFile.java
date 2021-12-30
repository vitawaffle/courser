package by.vitalylobatsevich.courser.database.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "uploaded_files")
@Data
@NoArgsConstructor
public class UploadedFile extends LongIdEntity {

    private String path;

    @ManyToOne
    private User user;

    public UploadedFile(final Long id, final String path, final User user) {
        super(id);
        this.path = path;
        this.user = user;
    }

    public UploadedFile(final Long id) {
        super(id);
    }

    @Override
    public UploadedFile updateId(final Long id) {
        super.updateId(id);
        return this;
    }

    public UploadedFile updatePath(final String path) {
        this.path = path;
        return this;
    }

    public UploadedFile updateUser(final User user) {
        this.user = user;
        return this;
    }

}
