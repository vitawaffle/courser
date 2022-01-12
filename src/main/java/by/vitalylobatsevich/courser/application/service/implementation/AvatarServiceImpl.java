package by.vitalylobatsevich.courser.application.service.implementation;

import by.vitalylobatsevich.courser.application.service.AvatarService;
import by.vitalylobatsevich.courser.database.entity.Avatar;
import by.vitalylobatsevich.courser.database.repository.AvatarRepository;
import by.vitalylobatsevich.courser.database.repository.FileRepository;
import by.vitalylobatsevich.courser.database.repository.UserRepository;
import by.vitalylobatsevich.courser.http.dto.AvatarDTO;

import io.vavr.collection.Seq;
import io.vavr.control.Option;

import lombok.RequiredArgsConstructor;
import lombok.val;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AvatarServiceImpl implements AvatarService {

    private final AvatarRepository avatarRepository;

    private final UserRepository userRepository;

    private final FileRepository fileRepository;

    @Override
    public Seq<Avatar> getAll() {
        return avatarRepository.findAll();
    }

    @Override
    public Option<Avatar> getById(final Long id) {
        return avatarRepository.findById(id);
    }

    @Override
    public Avatar save(final Avatar avatar) {
        return avatarRepository.save(avatar);
    }

    @Override
    public void deleteById(final Long id) {
        try {
            avatarRepository.deleteById(id);
        } catch (final EmptyResultDataAccessException ignore) {
        }
    }

    @Override
    public void add(final AvatarDTO avatarDTO, final String username) {
        val user = userRepository.findByEmail(username)
                .getOrElseThrow(() -> new UsernameNotFoundException(username));
        fileRepository.findById(avatarDTO.getFileId()).peek(file -> avatarRepository.save(
                Avatar.builder()
                        .user(user)
                        .file(file)
                        .build()
        ));
    }

    public ResponseEntity<?> setCurrent(final Long id, final String username) {
        val user  = userRepository.findByEmail(username)
                .getOrElseThrow(() -> new UsernameNotFoundException(username));
        return avatarRepository.findByIdAndUser(id, user).map(avatar -> {
            userRepository.save(user.updater().avatar(avatar).update());
            return ResponseEntity.ok(null);
        }).getOrElse(ResponseEntity.notFound().build());
    }

}
