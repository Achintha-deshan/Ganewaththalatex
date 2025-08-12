package edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.custom;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.CrudDAO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.entity.Inventory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface InventoryDAO extends CrudDAO<Inventory> {

    Optional<Inventory> findById(String id) throws SQLException, ClassNotFoundException;
    int getTotalQuantityForInventory(String inventoryID) throws SQLException, ClassNotFoundException;

    List<Inventory> findAll() throws SQLException, ClassNotFoundException;
    boolean updateQuantity(String inventoryId, int changeQty) throws SQLException, ClassNotFoundException;

    boolean updateQuantity(String inventoryID, int qtyChange, Connection connection) throws SQLException;
}
