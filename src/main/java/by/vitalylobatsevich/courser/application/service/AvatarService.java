package by.vitalylobatsevich.courser.application.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import by.vitalylobatsevich.courser.database.entity.Avatar;
import by.vitalylobatsevich.courser.database.entity.User;

import io.vavr.collection.Seq;
import io.vavr.control.Option;

public interface AvatarService extends CollectionService<Avatar, Long> {

    Option<Resource> loadCurrentForUser(User user);

    Option<Resource> loadCurrentForCurrentUser();

    Option<Resource> loadById(Long id);

    Avatar storeForUser(MultipartFile file, User user);

    Avatar storeForCurrentUser(MultipartFile file);

    User setCurrentForUser(Avatar avatar, User user);

    User setCurrentForCurrentUser(Avatar avatar);

    User storeAndSetCurrentForUser(MultipartFile file, User user);

    User storeAndSetCurrentForCurrentUser(MultipartFile file);

    Seq<Avatar> getAllByUser(User user);

    Seq<Avatar> getAllForCurrentUser();

    void deleteCurrentForUserWithFile(User user);

    void deleteCurrentForCurrentUserWithFile();

    void deleteByIdAndUserWithFile(Long id, User user);

    void deleteByIdForCurrentUserWithFile(Long id);

}
