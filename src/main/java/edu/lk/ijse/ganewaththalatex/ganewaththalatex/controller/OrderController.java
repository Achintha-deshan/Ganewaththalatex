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

public class OrderController implements Initializable {
    private final FactoryBO factoryBO = new FactoryBOImpl();
    private final InventoryBO inventoryBO = new InventoryBOImpl();
    private final OrderBO orderBO = new OrderBOImpl();

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

    private ObservableList<AddToCartTM> cart = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize TableView columns
        colnewOrderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        colnewInventoryID.setCellValueFactory(new PropertyValueFactory<>("inventoryID"));
        colnewQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colnewPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colnewAction.setCellValueFactory(new PropertyValueFactory<>("action"));

        colOrderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        colFactoryName.setCellValueFactory(new PropertyValueFactory<>("factoryName"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colOrderdate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));

        // Load initial data
        loadFactoryIds();
        loadInventoryIds();
        loadNextOrderID();
        loadData();

        // Set current date for orderDate
        txtOrderDate.setText(LocalDate.now().toString());

        tblAddtocart.setItems(cart);
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
        try {
            double qty = Double.parseDouble(txtQTYNeed.getText());
            double rate = Double.parseDouble(txtrateL.getText());

            Button btnRemove = new Button("Remove");

            AddToCartTM item = new AddToCartTM(
                    OrderID.getText(),
                    cobIntId.getValue(),
                    qty,
                    qty * rate,
                    btnRemove
            );

            btnRemove.setOnAction(e -> {
                cart.remove(item);
                tblAddtocart.refresh();
                updateFullTotal();
            });

            cart.add(item);
            updateFullTotal();

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING, "Please enter valid quantity and rate").show();
        }
    }

    @FXML
    void btnonActionPlaceOrder(ActionEvent event) {
        try {
            String orderId = OrderID.getText().trim();
            String factoryId = cobFacID.getValue();
            String factoryName = lblfacname.getText();
            String orderDate = txtOrderDate.getText();

            if (orderId.isEmpty() || factoryId == null || factoryName.isEmpty() || orderDate.isEmpty() || cart.isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Please fill all fields and add items to cart").show();
                return;
            }

            OrderDto orderDTO = new OrderDto(orderId, factoryName, "", orderDate, "", "", factoryId);

            List<OrderDetailsDto> orderDetailsList = new ArrayList<>();
            for (AddToCartTM cartItem : cart) {
                OrderDetailsDto detailsDto = new OrderDetailsDto(
                        null, // OrderDetailsID (auto-generated in DAO/BO)
                        orderId,
                        cartItem.getInventoryID(),
                        (int) cartItem.getQty(),
                        0 // qtyOnInventory (optional or you can fetch real value if needed)
                );
                orderDetailsList.add(detailsDto);
            }

            boolean success = orderBO.placeOrder(orderDTO, orderDetailsList);

            if (success) {
                new Alert(Alert.AlertType.INFORMATION, "Order placed successfully!").show();
                clearInputs();
                loadData();
                cart.clear();
                tblAddtocart.refresh();
                loadNextOrderID();
            } else {
                new Alert(Alert.AlertType.ERROR, "Order placement failed!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage()).show();
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



