package edu.lk.ijse.ganewaththalatex.ganewaththalatex.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AdminController {

    public AnchorPane ancpagelorder;
    public AnchorPane ancAdmindashbord;
    public Button btnVehicle1;

    @FXML
    private Button btnFactory;

    @FXML
    private Button btnDelivary;

    @FXML
    private Button btnEmployee;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnPayment;

    @FXML
    private Button btnQyality;

    @FXML
    private Button btnStock;

    @FXML
    private Button btnSupplier;

    @FXML
    private Button btnVehicle;

    @FXML
    void clickClient(MouseDragEvent event) {

    }

    @FXML
    void clickDelivary(MouseDragEvent event) {

    }

    @FXML
    void clickEmployee(MouseDragEvent event) {

    }

    @FXML
    void clickStock(MouseDragEvent event) {

    }

    @FXML
    void clickVehicle(MouseDragEvent event) {

    }

    @FXML
    void onMouseEnterSuplier(MouseEvent event) {
        btnSupplier.setStyle("-fx-background-color: #f39c12");


    }


    @FXML
    void onMouseEnteredDelivary(MouseEvent event) {
        btnDelivary.setStyle("-fx-background-color: #f39c12");
    }

    @FXML
    void onMouseEnteredEmployee(MouseEvent event) {
        btnEmployee.setStyle("-fx-background-color: #f39c12");
    }

    @FXML
    void onMouseEnteredLogout(MouseEvent event) {
        btnLogout.setStyle("-fx-background-color: #e74c3c");
    }

    @FXML
    void onMouseEnteredPayment(MouseEvent event) {
    btnPayment.setStyle("-fx-background-color: #f39c12");
    }

    @FXML
    void onMouseEnteredQuality(MouseEvent event) {
     btnQyality.setStyle("-fx-background-color: #f39c12");
    }

    @FXML
    void onMouseEnteredStock(MouseEvent event) {
     btnStock.setStyle("-fx-background-color: #f39c12");
    }

    @FXML
    void onMouseEnteredVehicle(MouseEvent event) {
        btnVehicle.setStyle("-fx-background-color: #f39c12");
    }

    @FXML
    void onMouseExiedQuality(MouseEvent event) {
        btnQyality.setStyle("-fx-background-color: #1B3553");
    }

    @FXML
    void onMouseExiteDelivary(MouseEvent event) {

        btnDelivary.setStyle("-fx-background-color: #1B3553");
    }
    @FXML
    void onMouseEnteredClient(MouseEvent event) {btnFactory.setStyle("-fx-background-color: #f39c12");

    }
    @FXML
    void onMouseExitedClient(MouseEvent event) {btnFactory.setStyle("-fx-background-color: #1B3553");

    }
    @FXML
    void onMouseExiteEmployee(MouseEvent event) {
        btnEmployee.setStyle("-fx-background-color: #1B3553");
    }


    @FXML
    void onMouseExitedLogout(MouseEvent event) {
        btnLogout.setStyle("-fx-background-color:#1B3553");
    }

    @FXML
    void onMouseExitedPayment(MouseEvent event) {
     btnPayment.setStyle("-fx-background-color: #1B3553");
    }

    @FXML
    void onMouseExitedStock(MouseEvent event) {
      btnStock.setStyle("-fx-background-color: #1B3553");
    }

    @FXML
    void onMouseExitedSuplier(MouseEvent event) {
       btnSupplier.setStyle("-fx-background-color: #1B3553");
    }

    @FXML
    void onMouseExitedVehical(MouseEvent event) {
           btnVehicle.setStyle("-fx-background-color: #1B3553");
    }


    public void navigate(String path) {
        try{
            ancpagelorder.getChildren().clear();
            Parent parent = FXMLLoader.load(getClass().getResource(path));
            ancpagelorder.getChildren().add(parent);
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onactionmoveSuplierPage(javafx.event.ActionEvent actionEvent) {
        navigate("/view/Suplier.fxml");
    }

    public void btnonActionmoveFactoryManagement(ActionEvent actionEvent) {
        navigate("/view/FactoryView.fxml");
    }

    public void btnonActionmoveEmployeManagement(ActionEvent actionEvent) {
        navigate("/view/Employee.fxml");
    }

    public void btnonActionmoveInventoryManagement(ActionEvent actionEvent) {
        navigate("/view/Inventory.fxml");
    }

    public void btnonActionmoveDelivaryManagement(ActionEvent actionEvent) {
        navigate("/view/Order.fxml");
    }

    public void btnonActionmoveVehicalManagement(ActionEvent actionEvent) {
        navigate("/view/Vehicle.fxml");
    }

    public void btnonActionmoveVehicalRepairManagement(ActionEvent actionEvent) {
        navigate("/view/VehicleRepair.fxml");
    }

    public void btnonActionmoveQuatityManagement(ActionEvent actionEvent) {

    }
    @FXML
    void btnonActionLogout(ActionEvent event) {
        try {

            Parent loginRoot = FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml"));


            Stage stage = (Stage) btnLogout.getScene().getWindow();


            stage.setScene(new Scene(loginRoot));
            stage.setTitle("Login");
            stage.centerOnScreen();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}

