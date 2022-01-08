package by.vitalylobatsevich.courser.application.service.implementation;

import by.vitalylobatsevich.courser.application.service.NameService;
import by.vitalylobatsevich.courser.database.entity.Language;
import by.vitalylobatsevich.courser.database.entity.Name;
import by.vitalylobatsevich.courser.database.entity.NameId;
import by.vitalylobatsevich.courser.database.repository.NameRepository;
import by.vitalylobatsevich.courser.database.repository.UserRepository;
import by.vitalylobatsevich.courser.http.dto.NameDTO;

import io.vavr.collection.Seq;
import io.vavr.control.Option;

import lombok.RequiredArgsConstructor;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NameServiceImpl implements NameService {

    private final NameRepository nameRepository;

    private final UserRepository userRepository;

    @Override
    public Seq<Name> getAll() {
        return nameRepository.findAll();
    }

    @Override
    public Option<Name> getById(final NameId nameId) {
        return nameRepository.findById(nameId);
    }

    @Override
    public Name save(final Name name) {
        return nameRepository.save(name);
    }

    @Override
    public void deleteById(final NameId nameId) {
        try {
            nameRepository.deleteById(nameId);
        } catch (final EmptyResultDataAccessException ignore) {
        }
    }

    @Override
    public Name save(final NameDTO nameDTO, final String username) {
        return save(
                Name.builder()
                        .firstName(nameDTO.getFirstName())
                        .lastName(nameDTO.getLastName())
                        .patronymic(nameDTO.getPatronymic())
                        .language(Language.builder().id(nameDTO.getLanguageId()).build())
                        .user(userRepository.findByEmail(username)
                                .getOrElseThrow(() -> new UsernameNotFoundException(username)))
                        .build());
    }

}
