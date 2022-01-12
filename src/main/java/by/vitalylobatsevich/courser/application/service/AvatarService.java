package by.vitalylobatsevich.courser.application.service;

import by.vitalylobatsevich.courser.database.entity.Avatar;
import by.vitalylobatsevich.courser.http.dto.AvatarDTO;
import org.springframework.http.ResponseEntity;

public interface AvatarService extends CollectionService<Avatar, Long> {

    void add(AvatarDTO avatarDTO, String username);

    ResponseEntity<?> setCurrent(Long id, String username);

}
