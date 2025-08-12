package edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.util;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.OrderDetailsDto;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.OrderDto;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.entity.Order;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.entity.OrderDetails;

public class OrderEntityDtoConverter {
    // Convert Entity → DTO
    public static OrderDetailsDto fromEntity(OrderDetails entity) {
        return new OrderDetailsDto(
                entity.getOrderDetailsID(),
                entity.getOrderID(),
                entity.getInventoryID(),
                Integer.parseInt(entity.getQTY()),
                Integer.parseInt(entity.getQtyOnInventory())
        );
    }

    // Convert DTO → Entity
    public static OrderDetails toEntity(OrderDetailsDto dto) {
        return new OrderDetails(
                dto.getOrderDetailsID(),
                dto.getOrderID(),
                dto.getInventoryID(),
                String.valueOf(dto.getQty()),
                String.valueOf(dto.getQtyOnInventory())
        );
    }

    // Add these for Order DTO ↔ Entity
    public static OrderDto fromEntity(Order entity) {
        return new OrderDto(
                entity.getOrderID(),
                entity.getFactoryName(),
                entity.getQty(),
                entity.getOrderDate(),
                entity.getInventoryID(),
                entity.getInventoryQTY(),
                entity.getFactoryID()
        );
    }

    public static Order toEntity(OrderDto dto) {
        return new Order(
                dto.getOrderID(),
                dto.getFactoryName(),
                dto.getQty(),
                dto.getOrderDate(),
                dto.getInventoryID(),
                dto.getInventoryQTY(),
                dto.getFactoryID()
        );
    }
}
