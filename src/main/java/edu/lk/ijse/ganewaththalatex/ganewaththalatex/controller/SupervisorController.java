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

import java.io.IOException;

public class SupervisorController {

    @FXML
    private AnchorPane ancSuperpageloader;

    @FXML
    private Button btnDelivary;

    @FXML
    private Button btnEmployee;

    @FXML
    private Button btnFactory;

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
    void btnonActionMoveDelivary(ActionEvent event) {
        navigate("/view/Order.fxml");
    }

    @FXML
    void btnonActionMoveEmployee(ActionEvent event) {
        navigate("/view/Employee.fxml");
    }

    @FXML
    void btnonActionMoveFactoryPage(ActionEvent event) {
        navigate("/view/FactoryView.fxml");
    }

    @FXML
    void btnonActionMoveInventory(ActionEvent event) {
        navigate("/view/Inventory.fxml");
    }

    @FXML
    void btnonActionMoveSupplierPage(ActionEvent event) {
        navigate("/view/Suplier.fxml");
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
    @FXML
    void btnonActionVehicle(ActionEvent event) {
            navigate("/view/Vehicle.fxml");
    }

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
    void onMouseEnteredFactory(MouseEvent event) {
        btnFactory.setStyle("-fx-background-color: #f39c12");
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
        btnLogout.setStyle("-fx-background-color: #f39c12");
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
    void onMouseEnteredVehical(MouseEvent event) {
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
    void onMouseExiteEmployee(MouseEvent event) {
        btnEmployee.setStyle("-fx-background-color: #1B3553");
    }

    @FXML
    void onMouseExitedFactory(MouseEvent event) {
        btnFactory.setStyle("-fx-background-color: #1B3553");
    }

    @FXML
    void onMouseExitedLogout(MouseEvent event) {
        btnLogout.setStyle("-fx-background-color: #1B3553");
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
        try {
            ancSuperpageloader.getChildren().clear();
            Parent parent = FXMLLoader.load(getClass().getResource(path));
            ancSuperpageloader.getChildren().add(parent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cmbonActionSelectSuplierID(ActionEvent actionEvent) {
    }

    public void btnonActionAddEmploye(ActionEvent actionEvent) {
    }

    public void btnonActionUpdateEmploye(ActionEvent actionEvent) {
    }

    public void btnonActionDeleteEmploye(ActionEvent actionEvent) {
    }

    public void btnonActionResetEmploye(ActionEvent actionEvent) {
    }

    public void cmbonActionSelectAttendance(ActionEvent actionEvent) {
    }

    public void btnonActionsalary(ActionEvent actionEvent) {
    }
}
