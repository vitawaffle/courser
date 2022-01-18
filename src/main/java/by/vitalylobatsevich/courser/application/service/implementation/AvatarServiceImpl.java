package by.vitalylobatsevich.courser.application.service.implementation;

import by.vitalylobatsevich.courser.application.service.AuthService;
import by.vitalylobatsevich.courser.application.service.AvatarService;
import by.vitalylobatsevich.courser.application.service.FileService;
import by.vitalylobatsevich.courser.application.service.StorageService;
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

    private final StorageService storageService;

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
    public Option<Resource> loadCurrent(final User user) {
        return Option.of(user.getAvatar())
                .map(avatar -> storageService.loadByFilenameAsResource(avatar.getFile().getName()));
    }

    @Override
    public Option<Resource> loadCurrentForCurrentUser() {
        return loadCurrent(authService.getUser());
    }

    @Override
    public Avatar store(final MultipartFile file, final User user) {
        return save(
                Avatar.builder()
                        .user(user)
                        .file(fileService.store(file, user))
                        .build()
        );
    }

    @Override
    public Avatar storeForCurrentUser(final MultipartFile file) {
        return store(file, authService.getUser());
    }

    @Override
    public void setCurrent(final Avatar avatar, final User user) {
        userRepository.save(user.updater().avatar(avatar).update());
    }

    @Override
    public void setCurrentForCurrentUser(final Avatar avatar) {
        setCurrent(avatar, authService.getUser());
    }

    @Override
    public void set(final MultipartFile file, final User user) {
        setCurrent(store(file, user), user);
    }

    @Override
    public void setForCurrentUser(final MultipartFile file) {
        setCurrentForCurrentUser(storeForCurrentUser(file));
    }

}
