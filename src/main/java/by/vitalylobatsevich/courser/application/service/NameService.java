package by.vitalylobatsevich.courser.application.service;

import by.vitalylobatsevich.courser.database.entity.Name;
import by.vitalylobatsevich.courser.database.entity.NameId;
import by.vitalylobatsevich.courser.http.dto.NameDTO;

import io.vavr.collection.Seq;

public interface NameService extends CollectionService<Name, NameId> {

    void save(NameDTO nameDTO, String username);

    Seq<NameDTO> getByUsername(String username);

    void delete(Long languageId, String username);

}
