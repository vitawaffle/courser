package by.vitalylobatsevich.courser.application.service.implementation;

import by.vitalylobatsevich.courser.application.service.AuthService;
import by.vitalylobatsevich.courser.application.service.AvatarService;
import by.vitalylobatsevich.courser.application.service.FileService;
import by.vitalylobatsevich.courser.database.entity.Avatar;
import by.vitalylobatsevich.courser.database.entity.User;
import by.vitalylobatsevich.courser.database.repository.AvatarRepository;
import by.vitalylobatsevich.courser.database.repository.UserRepository;

import io.vavr.collection.Seq;
import io.vavr.control.Option;

import lombok.RequiredArgsConstructor;

import org.springframework.core.io.Resource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class AvatarServiceImpl implements AvatarService {

    private final AvatarRepository avatarRepository;

    private final UserRepository userRepository;

    private final FileService fileService;

    private final AuthService authService;

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
    public void delete(final Avatar avatar) {
        avatarRepository.delete(avatar);
    }

    @Override
    public void deleteById(final Long id) {
        try {
            avatarRepository.deleteById(id);
        } catch (final EmptyResultDataAccessException ignore) {
        }
    }

    @Override
    public Option<Resource> loadCurrentForUser(final User user) {
        return Option.of(user.getAvatar()).map(avatar -> fileService.load(avatar.getFile()));
    }

    @Override
    public Option<Resource> loadCurrentForCurrentUser() {
        return loadCurrentForUser(authService.getUser());
    }

    @Override
    public Option<Resource> loadById(final Long id) {
        return avatarRepository.findById(id).map(avatar -> fileService.load(avatar.getFile()));
    }

    @Override
    public Avatar storeForUser(final MultipartFile file, final User user) {
        return save(
                Avatar.builder()
                        .user(user)
                        .file(fileService.storeForUser(file, user))
                        .build()
        );
    }

    @Override
    public Avatar storeForCurrentUser(final MultipartFile file) {
        return storeForUser(file, authService.getUser());
    }

    @Override
    public User setCurrentForUser(final Avatar avatar, final User user) {
        return userRepository.save(user.updater().avatar(avatar).update());
    }

    @Override
    public User setCurrentForCurrentUser(final Avatar avatar) {
        return setCurrentForUser(avatar, authService.getUser());
    }

    @Override
    public User storeAndSetCurrentForUser(final MultipartFile file, final User user) {
        return setCurrentForUser(storeForUser(file, user), user);
    }

    @Override
    public User storeAndSetCurrentForCurrentUser(final MultipartFile file) {
        return setCurrentForCurrentUser(storeForCurrentUser(file));
    }

    @Override
    public Seq<Avatar> getAllByUser(final User user) {
        return avatarRepository.findByUser(user);
    }

    @Override
    public Seq<Avatar> getAllForCurrentUser() {
        return getAllByUser(authService.getUser());
    }

    @Override
    public void deleteCurrentForUserWithFile(final User user) {
        Option.of(user.getAvatar()).peek(avatar -> {
            userRepository.save(user.updater().avatar(null).update());
            delete(avatar);
            fileService.deleteFromStorage(avatar.getFile());
        });
    }

    @Override
    public void deleteCurrentForCurrentUserWithFile() {
        deleteCurrentForUserWithFile(authService.getUser());
    }

    @Override
    public void deleteByIdAndUserWithFile(final Long id, final User user) {
        avatarRepository.findByIdAndUser(id, user).peek(avatar -> {
            delete(avatar);
            fileService.deleteFromStorage(avatar.getFile());
        });
    }

    @Override
    public void deleteByIdForCurrentUserWithFile(final Long id) {
        deleteByIdAndUserWithFile(id, authService.getUser());
    }

}
