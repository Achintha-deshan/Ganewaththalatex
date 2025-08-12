package edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom.impl;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom.OrderBO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.util.OrderEntityDtoConverter;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.DAOFactory;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.DAOTypes;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.custom.InventoryDAO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.custom.OrderDAO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.custom.OrderDetailsDAO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.db.DBConnection;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.OrderDetailsDto;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.OrderDto;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.entity.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderBOImpl implements OrderBO {

    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().getDAO(DAOTypes.ORDER);
    private final OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getInstance().getDAO(DAOTypes.ORDER_DETAILS);
    private final InventoryDAO inventoryDAO = (InventoryDAO) DAOFactory.getInstance().getDAO(DAOTypes.INVENTORY);

    public boolean placeOrder(OrderDto orderDTO, List<OrderDetailsDto> orderDetailsList) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            // Save Order entity
            boolean orderSaved = orderDAO.save(OrderEntityDtoConverter.toEntity(orderDTO), connection);
            if (!orderSaved) {
                connection.rollback();
                return false;
            }

            // Save each OrderDetails
            for (OrderDetailsDto detailsDTO : orderDetailsList) {
                boolean detailsSaved = orderDetailsDAO.save(OrderEntityDtoConverter.toEntity(detailsDTO), connection);
                if (!detailsSaved) {
                    connection.rollback();
                    return false;
                }

                // Update inventory quantity (subtract order qty)
                boolean inventoryUpdated = inventoryDAO.updateQuantity(
                        detailsDTO.getInventoryID(),
                        -detailsDTO.getQty(),
                        connection
                );
                if (!inventoryUpdated) {
                    connection.rollback();
                    return false;
                }
            }

            connection.commit();
            return true;

        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public String getNextOrderID() throws SQLException, ClassNotFoundException {
        String nextID = null;  // or however you get the next ID
        try {
            nextID = orderDAO.getNextOrderId();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return nextID != null ? nextID : "O001";
    }

    @Override
    public List<OrderDto> getAllOrders() throws SQLException, ClassNotFoundException {
        try {
            List<Order> orders = orderDAO.findAll();
            List<OrderDto> orderDTOs = new ArrayList<>();
            for (Order order : orders) {
                orderDTOs.add(OrderEntityDtoConverter.fromEntity(order));
            }
            return orderDTOs;
        } catch (Exception e) {
            throw new RuntimeException(e); // or handle more gracefully
        }
    }


}
