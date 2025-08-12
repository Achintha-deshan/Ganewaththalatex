package edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.SuperBO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.OrderDetailsDto;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.OrderDto;

import java.sql.SQLException;
import java.util.List;

public interface OrderBO extends SuperBO {
    boolean placeOrder(OrderDto orderDTO, List<OrderDetailsDto> orderDetailsList) throws Exception;
    String getNextOrderID() throws SQLException, ClassNotFoundException;
    List<OrderDto> getAllOrders() throws SQLException, ClassNotFoundException;

}
