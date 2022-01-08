package by.vitalylobatsevich.courser.application.service;

import by.vitalylobatsevich.courser.database.entity.Name;
import by.vitalylobatsevich.courser.database.entity.NameId;

public interface NameService extends CollectionService<Name, NameId> {

    Name save(Name name, String username);

}
