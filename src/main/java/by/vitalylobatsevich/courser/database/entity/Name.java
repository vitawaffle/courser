package by.vitalylobatsevich.courser.database.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "names")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "language", "user" })
@Builder(builderMethodName = "nameBuilder")
public class Name extends CourserEntity {

    @EmbeddedId
    private NameId nameId;

    private String firstName;

    private String lastName;

    private String patronymic;

    @ManyToOne
    @MapsId("languageId")
    private Language language;

    @ManyToOne
    @MapsId("userId")
    private User user;

    public NameUpdater nameUpdater() {
        return new NameUpdater();
    }

    public class NameUpdater implements Updater<Name> {

        public NameUpdater firstName(final String firstName) {
            Name.this.firstName = firstName;
            return this;
        }

        public NameUpdater lastName(final String lastName) {
            Name.this.lastName = lastName;
            return this;
        }

        public NameUpdater patronymic(final String patronymic) {
            Name.this.patronymic = patronymic;
            return this;
        }

        @Override
        public Name update() {
            return Name.this;
        }

    }

}
