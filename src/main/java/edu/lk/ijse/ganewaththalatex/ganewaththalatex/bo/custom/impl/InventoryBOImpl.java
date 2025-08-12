package edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom.impl;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom.InventoryBO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.util.InventoryEntityDTOConverter;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.DAOFactory;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.DAOTypes;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.custom.InventoryDAO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.InventoryDto;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.entity.Inventory;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryBOImpl implements InventoryBO {




    private final InventoryDAO inventoryDAO =
            (InventoryDAO) DAOFactory.getInstance().getDAO(DAOTypes.INVENTORY);

    @Override
    public boolean saveInventory(InventoryDto dto) throws SQLException, ClassNotFoundException {
        return inventoryDAO.save(InventoryEntityDTOConverter.toEntity(dto));
    }

    @Override
    public boolean updateInventory(InventoryDto dto) throws SQLException, ClassNotFoundException {
        return inventoryDAO.update(InventoryEntityDTOConverter.toEntity(dto));
    }

    @Override
    public boolean deleteInventory(String id) throws SQLException, ClassNotFoundException {
        return inventoryDAO.delete(id);
    }

    @Override
    public InventoryDto findInventoryById(String id) throws SQLException, ClassNotFoundException {
        return inventoryDAO.findById(id)
                .map(InventoryEntityDTOConverter::toDTO)
                .orElse(null);
    }

    @Override
    public List<InventoryDto> getAllInventories() throws SQLException, ClassNotFoundException {
        return inventoryDAO.findAll().stream()
                .map(InventoryEntityDTOConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public String getNextInventoryId() throws SQLException, ClassNotFoundException {
        // Implement your ID generator logic, e.g., query max id from DB and increment
        String lastId = inventoryDAO.findAll()
                .stream()
                .map(Inventory::getInventoryID)
                .max(String::compareTo)
                .orElse(null);

        if (lastId == null) {
            return "I001";
        } else {
            int lastNum = Integer.parseInt(lastId.substring(1));
            int nextNum = lastNum + 1;
            return String.format("I%03d", nextNum);
        }
    }

    @Override
    public int getTotalQuantityForInventory(String inventoryID) throws SQLException, ClassNotFoundException {
        return inventoryDAO.getTotalQuantityForInventory(inventoryID);
    }
}
