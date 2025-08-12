//package edu.lk.ijse.ganewaththalatex.ganewaththalatex.model;
//
//import edu.lk.ijse.ganewaththalatex.ganewaththalatex.db.DBConnection;
//import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.OrderDto;
//import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.tm.AddToCartTM;
//import javafx.collections.ObservableList;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//
//public class
//OrderModel {
//
//        public ArrayList<OrderDto> getAllOrders() throws SQLException, ClassNotFoundException {
//            Connection connection = DBConnection.getInstance().getConnection();
//
//            String sql = "SELECT o.Order_ID, o.Factory_ID, o.FactoryName, d.Inventory_ID, d.QTY, d.QtyOnInventory, o.OrderDate  FROM OrderTable o JOIN OrderDetail d ON o.Order_ID = d.Order_ID";
//
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            ArrayList<OrderDto> orderList = new ArrayList<>();
//
//            while (resultSet.next()) {
//                String orderId = resultSet.getString("Order_ID");
//                String factoryId = resultSet.getString("Factory_ID");
//                String factoryName = resultSet.getString("FactoryName");
//                String inventoryId = resultSet.getString("Inventory_ID");
//                String qty = String.valueOf(resultSet.getInt("QTY"));
//                String qtyOnInventory = String.valueOf(resultSet.getInt("QtyOnInventory"));
//                String orderDate = resultSet.getString("OrderDate");
//                //String fullTotal = resultSet.getString("FullTotal");
//                //String rate = String.valueOf(resultSet.getInt("Rate"));
//
//
//                OrderDto order = new OrderDto(orderId,factoryName , qty , orderDate, inventoryId, qtyOnInventory,factoryId);
//                orderList.add(order);
//
//            }
//
//            return orderList;
//        }
//
//
//
//
//    private String generateOrderDetailId(Connection conn) throws SQLException {
//        String sql = "SELECT OrderDetail_ID FROM OrderDetail ORDER BY OrderDetail_ID DESC LIMIT 1";
//        PreparedStatement stmt = conn.prepareStatement(sql);
//        ResultSet rs = stmt.executeQuery();
//        if (rs.next()) {
//            String lastId = rs.getString(1);
//            int num = Integer.parseInt(lastId.substring(2));
//            num++;
//            return String.format("OD%03d", num);
//        } else {
//            return "OD001";
//        }
//    }
//
//
//    public boolean placeOrder(OrderDto orderDto, ObservableList<AddToCartTM> cart) throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getInstance().getConnection();
//
//        try {
//            connection.setAutoCommit(false);
//
//
//            if(orderDto.getOrderID() == null || orderDto.getOrderID().isEmpty()){
//                orderDto.setOrderID(getNextOrderID());
//            }
//
//
//            String orderSql = "INSERT INTO OrderTable (Order_ID, Factory_ID, FactoryName, OrderDate) VALUES (?, ?, ?, ?)";
//            PreparedStatement orderStmt = connection.prepareStatement(orderSql);
//            orderStmt.setString(1, orderDto.getOrderID());
//            orderStmt.setString(2, orderDto.getFactoryID());
//            orderStmt.setString(3, orderDto.getFactoryName());
//            orderStmt.setString(4, orderDto.getOrderDate());
//            if (orderStmt.executeUpdate() <= 0) {
//                connection.rollback();
//                return false;
//            }
//
//
//            String orderDetailId = generateOrderDetailId(connection);
//
//            String orderDetailSql = "INSERT INTO OrderDetail (OrderDetail_ID, Order_ID, Inventory_ID, QTY, QtyOnInventory) VALUES (?, ?, ?, ?, ?)";
//            PreparedStatement detailStmt = connection.prepareStatement(orderDetailSql);
//            detailStmt.setString(1, orderDetailId);
//            detailStmt.setString(2, orderDto.getOrderID());
//            detailStmt.setString(3, orderDto.getInventoryID());
//            detailStmt.setInt(4, Integer.parseInt(orderDto.getQty()));
//            detailStmt.setInt(5, Integer.parseInt(orderDto.getInventoryQTY()));
//
//            if (detailStmt.executeUpdate() <= 0) {
//                connection.rollback();
//                return false;
//            }
//
//
//            String updateInventorySQL = "UPDATE Inventory SET quantity = quantity - ? WHERE Inventory_ID = ?";
//            PreparedStatement updateStmt = connection.prepareStatement(updateInventorySQL);
//            updateStmt.setInt(1, Integer.parseInt(orderDto.getQty()));
//            updateStmt.setString(2, orderDto.getInventoryID());
//
//            if (updateStmt.executeUpdate() <= 0) {
//                connection.rollback();
//                return false;
//            }
//
//
//            connection.commit();
//            return true;
//
//        } catch (Exception e) {
//            connection.rollback();
//            throw new RuntimeException("Transaction Failed: " + e.getMessage());
//        } finally {
//            connection.setAutoCommit(true);
//        }
//    }
//    public static String getNextOrderID() throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getInstance().getConnection();
//
//        String sql = "SELECT Order_ID FROM OrderTable ORDER BY Order_ID DESC LIMIT 1";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        ResultSet resultSet = preparedStatement.executeQuery();
//
//        if (resultSet.next()) {
//            String lastID = resultSet.getString("Order_ID").trim();
//            String lastNumberString = lastID.substring(1);
//            int lastNumber = Integer.parseInt(lastNumberString);
//            int nextNumber = lastNumber + 1;
//            String nextID = String.format("O%03d", nextNumber);
//            return nextID;
//        }
//
//
//        return "O001";
//    }
//
//}
//
