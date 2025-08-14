package edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.SuperBO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.OrderDto;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.tm.AddToCartTM;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public interface OrderBO extends SuperBO {
    boolean placeOrder(OrderDto orderDTO, ObservableList<AddToCartTM> orderDetailsList) throws Exception;
    String getNextOrderID() throws SQLException, ClassNotFoundException;
    List<OrderDto> getAllOrders() throws SQLException, ClassNotFoundException;

}
