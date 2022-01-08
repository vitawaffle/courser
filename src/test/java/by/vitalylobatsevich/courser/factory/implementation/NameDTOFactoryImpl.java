package by.vitalylobatsevich.courser.factory.implementation;

import by.vitalylobatsevich.courser.factory.NameDTOFactory;
import by.vitalylobatsevich.courser.http.dto.NameDTO;

public class NameDTOFactoryImpl implements NameDTOFactory {

    @Override
    public NameDTO createValidEntity() {
        return NameDTO.builder()
                .firstName("Test First Name")
                .lastName("Test Last Name")
                .patronymic("Test Patronymic")
                .languageId(1L)
                .build();
    }

}
