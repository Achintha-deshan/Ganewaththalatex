package edu.lk.ijse.ganewaththalatex.ganewaththalatex.controller;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom.FactoryBO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom.impl.FactoryBOImpl;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.FactoryDto;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.tm.FactoryTM;
//import edu.lk.ijse.ganewaththalatex.ganewaththalatex.model.FactoryModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class FactoryController implements Initializable {

//    private final FactoryModel factoryModel = new FactoryModel();
    private final FactoryBO factoryBO = new FactoryBOImpl();

    @FXML
    private TextField txtSearchheare;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSearch;

    @FXML
    private TableColumn<FactoryTM, String> colAddress;

    @FXML
    private TableColumn<FactoryTM, String> colFactoryID;

    @FXML
    private TableColumn<FactoryTM, String> colName;

    @FXML
    private TableColumn<FactoryTM, String> colPhoneNumber;

    @FXML
    private Label lblFactoryID;

    @FXML
    private TableView<FactoryTM> tblFactory;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    void btnonActionAddFactory(ActionEvent event) {
        String FactoryID = lblFactoryID.getText().trim();
        String factoryName = txtName.getText();
        String phoneNumber = txtPhoneNumber.getText();
        String address = txtAddress.getText();

        FactoryDto factoryDto = new FactoryDto(FactoryID,factoryName,phoneNumber,address);

        System.out.println(FactoryID);
        try {
            boolean isSaved = factoryBO.saveFactory(factoryDto);
            if (isSaved) {
                loadData();
                loadNextID();
                clearFields();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Factory successfully added");
                alert.showAndWait();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to add Factory");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    void btnonActionDeleteFactory(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete this factory",
                ButtonType.YES, ButtonType.NO
                );
             Optional<ButtonType> response = alert.showAndWait();

             if (response.isPresent() && response.get() == ButtonType.YES) {
                 try {
                     String FactoryID = lblFactoryID.getText();

                     boolean isDeleted = factoryBO.deleteFactory(FactoryID);
                     if (isDeleted) {
                         loadData();
                         loadNextID();
                         clearFields();
                     }else {
                         new Alert(Alert.AlertType.ERROR,"Factory Not Found").show();
                     }
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
             }
    }

    @FXML
    void btnonActionResetFactory(ActionEvent event) {
        clearFields();

    }

    @FXML
    void btnonActionSearchFactory(ActionEvent event) {

        String input = txtSearchheare.getText();

        try {
            FactoryDto dto = factoryBO.findFactoryById(input);

            if (dto != null) {
               lblFactoryID.setText(dto.getFactoryID());
                txtName.setText(dto.getFactoryName());
                txtAddress.setText(dto.getAddress());
                txtPhoneNumber.setText(dto.getPhonenumber());
            } else {
               clearFields();
                new Alert(Alert.AlertType.INFORMATION, "Factory Not Found").showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong").showAndWait();
        }
    }



    @FXML
    void btnonActionUpdateFactory(ActionEvent event) {
        String FactoryID = lblFactoryID.getText().trim();
        String factoryName = txtName.getText();
        String phoneNumber = txtPhoneNumber.getText();
        String address = txtAddress.getText();

        FactoryDto factoryDto = new FactoryDto(FactoryID,factoryName,phoneNumber,address);

        try {
            boolean isUpdate = factoryBO.updateFactory(factoryDto);
            if (isUpdate) {
                loadData();
                loadNextID();
                clearFields();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Factory successfully updated");
                alert.showAndWait();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to update Factory");
                alert.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("FactoryID: " + FactoryID);
        System.out.println("Name: " + factoryName);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("Address: " + address);

    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colFactoryID.setCellValueFactory(new PropertyValueFactory<>("factoryID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("factoryName"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        loadData();
        loadNextID();
        clearFields();
        setTabledatatoFields();
        System.out.println("ku");

    }
    private void loadData(){
        try {
            List<FactoryDto> factoryDtos = factoryBO.getAllFactories();
            ObservableList<FactoryTM> factoryTMObservableList = FXCollections.observableArrayList();

            for(FactoryDto factoryDto : factoryDtos){
                FactoryTM factoryTM = new FactoryTM(factoryDto.getFactoryID(),factoryDto.getFactoryName(),factoryDto.getPhonenumber(),factoryDto.getAddress());
                factoryTMObservableList.add(factoryTM);
            }
            tblFactory.setItems(factoryTMObservableList);
            System.out.println(factoryTMObservableList.size());
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadNextID(){
        try {
            String id = factoryBO.getNextFactoryId();
            lblFactoryID.setText(id);
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    private void setTabledatatoFields(){
        tblFactory.setOnMouseClicked(event -> {
            FactoryTM factoryTM = tblFactory.getSelectionModel().getSelectedItem();
            if (factoryTM != null) {
                lblFactoryID.setText(factoryTM.getFactoryID());
                txtName.setText(factoryTM.getFactoryName());
                txtPhoneNumber.setText(factoryTM.getPhonenumber());
                txtAddress.setText(factoryTM.getAddress());
            }
            System.out.println(tblFactory.getSelectionModel().getSelectedItem());
        });
    }
    public void clearFields() {
        txtName.clear();
        txtPhoneNumber.clear();
        txtAddress.clear();
        txtSearchheare.clear();
    }
}
