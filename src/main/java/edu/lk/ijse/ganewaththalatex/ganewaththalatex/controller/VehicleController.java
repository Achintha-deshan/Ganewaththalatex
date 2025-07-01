package edu.lk.ijse.ganewaththalatex.ganewaththalatex.controller;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.VehicleDto;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.tm.VehicleTM;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.model.VehicleModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class VehicleController implements Initializable {

    @FXML
    private Button btnonActionAddVehical;

    @FXML
    private Button btnonActionDeleteVehical;

    @FXML
    private Button btnonActionResetVehical;

    @FXML
    private Button btnonActionSearchVehical;

    @FXML
    private Button btnonActionUpdateVehical;

    @FXML
    private TableColumn<VehicleTM, String> colVehicleID;

    @FXML
    private TableColumn<VehicleTM, String> colVehicleNumber;

    @FXML
    private Label lblVehicalId;

    @FXML
    private TableView<VehicleTM> tblVehical;

    @FXML
    private TextField txtVehicalNumber;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colVehicleID.setCellValueFactory(new PropertyValueFactory<>("vehicleID"));
        colVehicleNumber.setCellValueFactory(new PropertyValueFactory<>("vehicleName"));

        loadData();
        settablevalueforfields();
        loadNextID();


    }

    private void loadNextID(){
        try {
            String vehicleID = VehicleModel.getNextVehicleID();
            lblVehicalId.setText(vehicleID);
            loadData();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadData(){
        try {
            ArrayList<VehicleDto>vehicleDtos = VehicleModel.getVehicles();
            ObservableList<VehicleTM> vehicles = FXCollections.observableArrayList();

            for (VehicleDto vehicleDto : vehicleDtos) {
               VehicleTM vehicleTM = new VehicleTM(vehicleDto.getVehicleID(),vehicleDto.getVehicleName());
               vehicles.add(vehicleTM);
            }
            tblVehical.setItems(vehicles);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnonActionUpdateVehical(javafx.event.ActionEvent actionEvent) {

    }

    public void btnonActionAddVehical(javafx.event.ActionEvent actionEvent) {
        String vehicleID = lblVehicalId.getText().trim();
        String vehicleName = txtVehicalNumber.getText();

      VehicleDto vehicleDto = new VehicleDto(vehicleID,vehicleName);

        try {
            boolean isSaved =  VehicleModel.addVehicle(vehicleDto);
            if(isSaved){
                loadNextID();
                loadData();
                clearfields();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Vehicle added successfully");
                alert.showAndWait();

            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Vehicle Added Failed");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnonActionDeleteVehical(javafx.event.ActionEvent actionEvent) {
        VehicleTM selectedVehicle = tblVehical.getSelectionModel().getSelectedItem();
        if (selectedVehicle == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a vehicle to delete").show();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete this vehicle?",
                ButtonType.YES, ButtonType.NO
        );
        Optional<ButtonType> response = alert.showAndWait();

        if (response.isPresent() && response.get() == ButtonType.YES) {
            try {
                String vehicleID = selectedVehicle.getVehicleID();
                boolean isDeleted = VehicleModel.deleteVehicle(vehicleID);
                if (isDeleted) {
                    clearfields();
                    loadNextID();
                    loadData();

                    new Alert(Alert.AlertType.INFORMATION, "Vehicle deleted successfully").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Vehicle not found").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    public void btnonActionSearchVehical(javafx.event.ActionEvent actionEvent) {
    }

    public void btnonActionResetVehical(javafx.event.ActionEvent actionEvent) {
    }

    private void settablevalueforfields(){
        tblVehical.setOnMouseClicked(event -> {
            VehicleTM vehicleTM = tblVehical.getSelectionModel().getSelectedItem();
            if (vehicleTM != null){
                lblVehicalId.setText(vehicleTM.getVehicleID());
                txtVehicalNumber.setText(vehicleTM.getVehicleName());

            }

        });
    }
    private void clearfields() {
        txtVehicalNumber.clear();
        loadNextID();
    }
}
