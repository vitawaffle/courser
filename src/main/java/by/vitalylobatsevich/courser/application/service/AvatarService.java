package by.vitalylobatsevich.courser.application.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import by.vitalylobatsevich.courser.database.entity.Avatar;
import by.vitalylobatsevich.courser.database.entity.User;

import io.vavr.control.Option;

public interface AvatarService extends CollectionService<Avatar, Long> {

    Option<Resource> loadCurrent(User user);

    Option<Resource> loadCurrentForCurrentUser();

    Avatar store(MultipartFile file, User user);

    Avatar storeForCurrentUser(MultipartFile file);

    void setCurrent(Avatar avatar, User user);

    void setCurrentForCurrentUser(Avatar avatar);

    void set(MultipartFile file, User user);

    void setForCurrentUser(MultipartFile file);

}
