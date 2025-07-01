package edu.lk.ijse.ganewaththalatex.ganewaththalatex.controller;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.db.DBConnection;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.FactoryDto;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.InventoryDto;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.OrderDto;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.PaymentDTO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.tm.AddToCartTM;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.tm.OrderTM;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.tm.PaymentTM;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.model.FactoryModel;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.model.InventoryModel;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.model.OrderModel;
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
private final OrderModel orderModel = new OrderModel();
    public AnchorPane ancpageloader;

    @FXML
    private TextField txtrateL;
    @FXML
    private TableColumn<AddToCartTM, Button> colnewAction;

    @FXML
    private TableColumn<AddToCartTM, String> colnewInventoryID;

    @FXML
    private TableColumn<AddToCartTM, String> colnewOrderID;

    @FXML
    private TableColumn<AddToCartTM, Double> colnewPrice;

    @FXML
    private TableColumn<AddToCartTM, Double> colnewQTY;

    @FXML
    private Label lblfultotal;

    @FXML
    private TableView<AddToCartTM> tblAddtocart;


    @FXML
    private Label OrderID;

    @FXML
    private ComboBox<String> cobFacID;

    @FXML
    private ComboBox<String> cobIntId;


    @FXML
    private TableColumn<PaymentTM, String> colFultotal;

    @FXML
    private TableColumn<PaymentTM, String> colHalfPayment;



    @FXML
    private TableColumn<OrderTM, String> colFactoryName;



    @FXML
    private TableColumn<OrderTM, String> colOrderID;

    @FXML
    private TableColumn<OrderTM, String> colOrderdate;

    @FXML
    private TableColumn<OrderTM, String> colQTY;


    @FXML
    private TableView<OrderTM> tblOrder;

    @FXML
    private Label lblfacname;

    @FXML
    private TextField txtOrderDate;

    @FXML
    private TextField txtQTYNeed;

    @FXML
    private Button btnreport;

    @FXML
    private Label txtQtyINT;

    ObservableList<AddToCartTM> cart = FXCollections.observableArrayList();

    @FXML
  void btnonactionReport(ActionEvent event) {

        OrderTM orderTM = tblOrder.getSelectionModel().getSelectedItem();
        if (orderTM == null) {
            new Alert(Alert.AlertType.WARNING, "Please select an order to generate the report.").show();
            return;
        }

      try {
          JasperReport jasperReport = JasperCompileManager.compileReport(
                  getClass()
                          .getResourceAsStream("/Report/FactoryOrderDetails.jrxml")
          );
          Connection con = DBConnection.getInstance().getConnection();

          Map<String, Object> params = new HashMap<>();

          params.put("P_Date",  LocalDate.now().toString());
          params.put("P_OrderID",orderTM.getOrderID());

          JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, con);
          JasperViewer.viewReport(jasperPrint, false);

      } catch (JRException e) {
          throw new RuntimeException(e);
      }catch (SQLException | ClassNotFoundException e){
          new Alert(Alert.AlertType.ERROR,"DB Error").show();
          e.printStackTrace();
      }
    }

    @FXML
    void btnonActionAttandanceandsalary(ActionEvent event) {
        navigate("");

    }


    @FXML
    void btnonActionAddtoCart(ActionEvent event) throws IOException {
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
            String inventoryId = cobIntId.getValue();
            String qtyOnInventory = txtQtyINT.getText();
            String qtyNeeded = txtQTYNeed.getText();
            String orderDate = txtOrderDate.getText();


            if (orderId.isEmpty() || factoryId == null || inventoryId == null || qtyNeeded.isEmpty() || orderDate.isEmpty()) {
                System.out.println("Please fill all fields!");
                return;
            }

            OrderDto dto = new OrderDto(orderId, factoryName , qtyNeeded , orderDate, inventoryId, qtyOnInventory, factoryId );

            boolean isSuccess = new OrderModel().placeOrder(dto,cart);
            double qty = Double.parseDouble(txtQTYNeed.getText());
            double rate = Double.parseDouble(txtrateL.getText());
            lblfultotal.setText("full Total ="+(qty*rate));

            if (isSuccess) {
                new Alert(Alert.AlertType.INFORMATION, "Order Placed successfully!").show();
                clearInputs();
                loadData();
                tblAddtocart.setVisible(false);
                tblOrder.setVisible(true);

            } else {
                System.out.println("Order placement failed.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadNextOrderID() {
        try {
            String nextID = OrderModel.getNextOrderID();
            OrderID.setText(nextID);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void btnonActionreset(ActionEvent event) {
        clearInputs();

    }

    @FXML
    void cmbsetFactoryId(ActionEvent event) {
        try {
            String selectedFactoryId = cobFacID.getValue();
            if (selectedFactoryId != null) {
                FactoryModel factoryModel = new FactoryModel();
                FactoryDto factory = factoryModel.searchFactory(selectedFactoryId);
                if (factory != null) {
                    lblfacname.setText(factory.getFactoryName());
                } else {
                    lblfacname.setText("Factory Not Found");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            lblfacname.setText("Error loading factory");
        }
    }

    @FXML
    void cmbsetInventoryid(ActionEvent event) {
        try {
            String selectedInventoryId = cobIntId.getValue();
            if (selectedInventoryId != null) {
                InventoryModel inventoryModel = new InventoryModel();
                InventoryDto inventory = InventoryModel.searchInventory(selectedInventoryId);
                if (inventory != null) {
                    txtQtyINT.setText(String.valueOf(inventory.getQuantity()));

                } else {
                    txtQtyINT.setText("Inventory Not Found");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colOrderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        colFactoryName.setCellValueFactory(new PropertyValueFactory<>("factoryName"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colOrderdate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colFultotal.setCellValueFactory(new PropertyValueFactory<>("fullTotal"));
        colHalfPayment.setCellValueFactory(new PropertyValueFactory<>("halfPayment"));


        loadData();
        txtOrderDate.setText(String.valueOf(LocalDate.now()));

        loadFactoryIds();
        loadInventoryIds();
        loadNextOrderID();
    }

    private void loadFactoryIds() {
        try {
            FactoryModel factoryModel = new FactoryModel();
            List<FactoryDto> factoryList = factoryModel.getFactoryList();

            List<String> ids = new ArrayList<>();
            for (FactoryDto factory : factoryList) {
                ids.add(factory.getFactoryID());
            }

            cobFacID.getItems().clear();
            cobFacID.getItems().addAll(ids);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        }
    }

    private void loadInventoryIds() {
        try {
            InventoryModel inventoryModel = new InventoryModel();
            List<InventoryDto> inventoryDtoList = InventoryModel.getAllInventory();

            List<String> ids = new ArrayList<>();
            for (InventoryDto inventory : inventoryDtoList) {
                ids.add(inventory.getInventoryID());
            }
            cobIntId.getItems().clear();
            cobIntId.getItems().addAll(ids);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void navigate(String path) {
        try {
            ancpageloader.getChildren().clear();
            Parent parent = FXMLLoader.load(getClass().getResource(path));
            ancpageloader.getChildren().add(parent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadData(){
        try {
            ArrayList<OrderDto> orderDtos = orderModel.getAllOrders();

            if (!orderDtos.isEmpty()) {
                System.out.println(orderDtos.get(0).getOrderID());
            } else {
                System.out.println("No orders found.");
            }

            ObservableList<OrderTM> orders = FXCollections.observableArrayList();

            for (OrderDto orderDto : orderDtos){
                OrderTM orderTM = new OrderTM(
                        orderDto.getOrderID(),
                        orderDto.getFactoryName(),
                        orderDto.getQty(),
                        orderDto.getOrderDate(),
                        orderDto.getInventoryID(),
                        orderDto.getInventoryQTY(),
                        orderDto.getFactoryID(),
                        "-",
                        "-"
                );
                orders.add(orderTM);
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
      txtOrderDate.clear();
      txtQTYNeed.clear();
      txtQtyINT.setText("");
    }

}



