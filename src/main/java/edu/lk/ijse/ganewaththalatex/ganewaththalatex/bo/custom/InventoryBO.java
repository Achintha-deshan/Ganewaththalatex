package edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.SuperBO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.InventoryDto;

import java.sql.SQLException;
import java.util.List;

public interface InventoryBO extends SuperBO {
    boolean saveInventory(InventoryDto dto) throws SQLException, ClassNotFoundException;
    boolean updateInventory(InventoryDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteInventory(String id) throws SQLException, ClassNotFoundException;
    InventoryDto findInventoryById(String id) throws SQLException, ClassNotFoundException;
    List<InventoryDto> getAllInventories() throws SQLException, ClassNotFoundException;
    String getNextInventoryId() throws SQLException, ClassNotFoundException;

    int getTotalQuantityForInventory(String inventoryID) throws SQLException, ClassNotFoundException;


}
