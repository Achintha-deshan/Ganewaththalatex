package edu.lk.ijse.ganewaththalatex.ganewaththalatex.controller;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom.FactoryBO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom.InventoryBO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom.OrderBO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom.impl.FactoryBOImpl;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom.impl.InventoryBOImpl;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom.impl.OrderBOImpl;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.db.DBConnection;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.*;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.tm.AddToCartTM;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.tm.OrderTM;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.tm.PaymentTM;
//import edu.lk.ijse.ganewaththalatex.ganewaththalatex.model.FactoryModel;
//import edu.lk.ijse.ganewaththalatex.ganewaththalatex.model.InventoryModel;
//import edu.lk.ijse.ganewaththalatex.ganewaththalatex.model.OrderModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class OrderController implements Initializable {
    private final FactoryBO factoryBO = new FactoryBOImpl();
    private final InventoryBO inventoryBO = new InventoryBOImpl();
    private final OrderBO orderBO = new OrderBOImpl();
   // private List<CartTM> cart = new ArrayList<>();


    @FXML private TextField txtrateL;
    @FXML private TableColumn<AddToCartTM, String> colnewOrderID;
    @FXML private TableColumn<AddToCartTM, String> colnewInventoryID;
    @FXML private TableColumn<AddToCartTM, Double> colnewQTY;
    @FXML private TableColumn<AddToCartTM, Double> colnewPrice;
    @FXML private TableColumn<AddToCartTM, Button> colnewAction;
    @FXML private TableView<AddToCartTM> tblAddtocart;

    @FXML private Label lblfultotal;
    @FXML private Label OrderID;
    @FXML private ComboBox<String> cobFacID;
    @FXML private ComboBox<String> cobIntId;
    @FXML private Label lblfacname;
    @FXML private TextField txtOrderDate;
    @FXML private TextField txtQTYNeed;
    @FXML private Label txtQtyINT;

    @FXML private TableColumn<OrderTM, String> colOrderID;
    @FXML private TableColumn<OrderTM, String> colFactoryName;
    @FXML private TableColumn<OrderTM, String> colQTY;
    @FXML private TableColumn<OrderTM, String> colOrderdate;
    @FXML private TableView<OrderTM> tblOrder;

    private final ObservableList<AddToCartTM> cart = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colnewOrderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        colnewInventoryID.setCellValueFactory(new PropertyValueFactory<>("inventoryID"));
        colnewQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colnewPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colnewAction.setCellValueFactory(new PropertyValueFactory<>("action"));

        // FIX — set columns for order table
        colOrderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        colFactoryName.setCellValueFactory(new PropertyValueFactory<>("factoryName"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colOrderdate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));

        loadFactoryIds();
        loadInventoryIds();
        loadNextOrderID();
        loadData();

        loadOrderData();
        txtOrderDate.setText(LocalDate.now().toString());

        tblAddtocart.setItems(cart);
    }

    private void loadOrderData() {
        try {
            List<OrderDto> allOrders = orderBO.getAllOrders(); // fetch from BO
            ObservableList<OrderTM> orderList = FXCollections.observableArrayList();

            for (OrderDto order : allOrders) {
                orderList.add(new OrderTM(
                        order.getOrderID(),
                        order.getFactoryName(),
                        String.valueOf(order.getQty()),
                        order.getOrderDate(),
                        order.getInventoryID(),
                        String.valueOf(order.getInventoryQTY()),
                        order.getFactoryID(),
                        String.valueOf(order.getHalfPayment()),
                        String.valueOf(order.getFullTotal())
                ));
            }

            tblOrder.setItems(orderList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @FXML
    void cmbsetFactoryId(ActionEvent event) {
        try {
            String selectedFactoryId = cobFacID.getValue();
            if (selectedFactoryId != null) {
                FactoryDto factory = factoryBO.findFactoryById(selectedFactoryId);
                lblfacname.setText(factory != null ? factory.getFactoryName() : "Factory Not Found");
            }
        } catch (SQLException | ClassNotFoundException e) {
            lblfacname.setText("Error loading factory");
            e.printStackTrace();
        }
    }

    @FXML
    void cmbsetInventoryid(ActionEvent event) {
        try {
            String selectedInventoryId = cobIntId.getValue();
            if (selectedInventoryId != null) {
                InventoryDto inventory = inventoryBO.findInventoryById(selectedInventoryId);
                txtQtyINT.setText(inventory != null ? String.valueOf(inventory.getQuantity()) : "Inventory Not Found");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnonActionAddtoCart(ActionEvent event) {
        try{
            colnewOrderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
            colnewInventoryID.setCellValueFactory(new PropertyValueFactory<>("inventoryID"));
            colnewQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
            colnewPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
            colnewAction.setCellValueFactory(new PropertyValueFactory<>("action"));

            tblAddtocart.setVisible(true);

            Button myButton = new Button("remove");

            double qty = Double.parseDouble(txtQTYNeed.getText());
            double rate = Double.parseDouble(txtrateL.getText());

            AddToCartTM orderTM = new AddToCartTM(
                    OrderID.getText(),
                    cobIntId.getValue(),
                    qty,
                    qty*rate,
                    myButton
            );

            myButton.setOnAction(e -> {
                cart.remove(orderTM);
                tblAddtocart.refresh();
            });

            System.out.println(orderTM.getOrderID());
            cart.add(orderTM);

            tblAddtocart.setItems(cart);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnonActionPlaceOrder(ActionEvent event) {
        try {
            String orderId = OrderID.getText().trim();
            String factoryId = cobFacID.getValue();
            String factoryName = lblfacname.getText();
            String orderDate = txtOrderDate.getText();

            tblOrder.setVisible(true);

            if (orderId.isEmpty() || factoryId == null || cart.isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Fill all fields and add items to cart!").show();
                return;
            }

            // Convert String qty to double safely
            double totalQty = cart.stream().mapToDouble(AddToCartTM::getQty).sum();

            OrderDto dto = new OrderDto(
                    orderId,
                    factoryName,
                    String.valueOf(totalQty),
                    orderDate,
                    null, // inventoryId not needed at order level
                    null, // qtyOnInventory not needed at order level
                    factoryId
            );

            boolean isSuccess = orderBO.placeOrder(dto, cart);

            if (isSuccess) {
                new Alert(Alert.AlertType.INFORMATION, "Order placed successfully!").show();
                clearInputs();
                cart.clear();
                loadData();
                loadOrderData();
                cart.clear();
                loadNextOrderID();
                txtOrderDate.setText(LocalDate.now().toString());
            } else {
                new Alert(Alert.AlertType.ERROR, "Order placement failed.").show();
            }

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }



    private void updateFullTotal() {
        double total = 0;
        for (AddToCartTM item : cart) {
            total += item.getPrice();
        }
        lblfultotal.setText("Full Total = " + total);
    }

    private void loadFactoryIds() {
        try {
            List<FactoryDto> factoryList = factoryBO.getAllFactories();
            cobFacID.getItems().clear();
            for (FactoryDto f : factoryList) {
                cobFacID.getItems().add(f.getFactoryID());
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadInventoryIds() {
        try {
            List<InventoryDto> inventoryList = inventoryBO.getAllInventories();
            cobIntId.getItems().clear();
            for (InventoryDto inv : inventoryList) {
                cobIntId.getItems().add(inv.getInventoryID());
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadNextOrderID() {
        try {
            String nextId = orderBO.getNextOrderID();
            OrderID.setText(nextId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        try {
            List<OrderDto> orderList = orderBO.getAllOrders();
            ObservableList<OrderTM> orders = FXCollections.observableArrayList();
            for (OrderDto order : orderList) {
                orders.add(new OrderTM(
                        order.getOrderID(),
                        order.getFactoryName(),
                        order.getQty(),
                        order.getOrderDate(),
                        order.getInventoryID(),
                        order.getInventoryQTY(),
                        order.getFactoryID(),
                        "-", // fullTotal placeholder if needed
                        "-"  // halfPayment placeholder if needed
                ));
            }
            tblOrder.setItems(orders);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearInputs() {
        cobFacID.setValue(null);
        cobIntId.setValue(null);
        lblfacname.setText("");
        txtOrderDate.setText(LocalDate.now().toString());
        txtQTYNeed.clear();
        txtQtyINT.setText("");
        lblfultotal.setText("Full Total = 0");
    }
    @FXML
    void btnonActionreset(ActionEvent event) {
        clearInputs();
        cart.clear();
        tblAddtocart.refresh();
        loadNextOrderID();
    }


    public void btnonactionReport(ActionEvent actionEvent) {
    }
}



