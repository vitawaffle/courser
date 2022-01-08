package by.vitalylobatsevich.courser.application.service;

import by.vitalylobatsevich.courser.database.entity.Name;
import by.vitalylobatsevich.courser.database.entity.NameId;
import by.vitalylobatsevich.courser.http.dto.NameDTO;

public interface NameService extends CollectionService<Name, NameId> {

    Name save(NameDTO nameDTO, String username);

}
