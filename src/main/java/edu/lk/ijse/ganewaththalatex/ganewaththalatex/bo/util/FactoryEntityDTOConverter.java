package edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.util;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.FactoryDto;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.entity.Factory;

public class FactoryEntityDTOConverter {

    // Convert FactoryDto to Factory entity
    public Factory getFactoryEntity(FactoryDto dto) {
        if (dto == null) return null;
        return new Factory(
                dto.getFactoryID(),
                dto.getFactoryName(),
                dto.getPhonenumber(),
                dto.getAddress()
        );
    }

    // Convert Factory entity to FactoryDto
    public FactoryDto getFactoryDto(Factory entity) {
        if (entity == null) return null;
        return new FactoryDto(
                entity.getFactoryID(),
                entity.getFactoryName(),
                entity.getPhoneNumber(),
                entity.getAddress()
        );
    }
}
