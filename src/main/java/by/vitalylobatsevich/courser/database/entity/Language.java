package by.vitalylobatsevich.courser.database.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "languages")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Language extends LongIdEntity {

    private String code;

    @Builder
    public Language(final Long id, final String code) {
        super(id);
        this.code = code;
    }

    public LanguageUpdater updater() {
        return new LanguageUpdater();
    }

    public class LanguageUpdater implements Updater<Language> {

        public LanguageUpdater id(final Long id) {
            setId(id);
            return this;
        }

        public LanguageUpdater code(final String code) {
            Language.this.code = code;
            return this;
        }

        @Override
        public Language update() {
            return Language.this;
        }

    }

}
