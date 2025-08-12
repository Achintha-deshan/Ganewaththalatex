package edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.util;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.InventoryDto;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.entity.Inventory;

public class InventoryEntityDTOConverter {
    public static Inventory toEntity(InventoryDto dto) {
        if (dto == null) return null;
        return new Inventory(
                dto.getInventoryID(),
                dto.getSupplierID(),
                dto.getSupplierName(),
                dto.getDateAdded(),
                dto.getQuantity(),
                dto.getRate(),
                dto.getPrice()
        );
    }

    public static InventoryDto toDTO(Inventory entity) {
        if (entity == null) return null;
        return new InventoryDto(
                entity.getInventoryID(),
                entity.getSupplierID(),
                entity.getSupplierName(),
                entity.getDateAdded(),
                entity.getQuantity(),
                entity.getRate(),
                entity.getPrice()
        );
    }
}
