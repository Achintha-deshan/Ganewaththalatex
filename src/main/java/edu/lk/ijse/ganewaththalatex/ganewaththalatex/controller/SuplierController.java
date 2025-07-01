package edu.lk.ijse.ganewaththalatex.ganewaththalatex.controller;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.SupplierDto;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.tm.SupplierTM;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.model.SupplierModel;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class SuplierController implements Initializable {

    private final SupplierModel supplierModel = new SupplierModel();

    @FXML
    private TextField txtSearchheare;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<SupplierTM, String> colAccountNumber;

    @FXML
    private TableColumn<SupplierTM, String> colBankName;

    @FXML
    private TableColumn<SupplierTM, String> colBranchName;

    @FXML
    private TableColumn<SupplierTM, String> colName;

    @FXML
    private TableColumn<SupplierTM, String> colPhoneNumber;

    @FXML
    private TableColumn<SupplierTM, String> colSupplierID;

    @FXML
    private Label lblSupplier;

    @FXML
    private TableView<SupplierTM> tblSupplier;

    @FXML
    private TextField txtAccNumber;

    @FXML
    private TextField txtBankName;

    @FXML
    private TextField txtBranchName;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    void btnonActionAddSupplier(ActionEvent event) {
        String SupplierId = lblSupplier.getText().trim();
        String name = txtName.getText();
        String phoneNumber = txtPhoneNumber.getText();
        String bankName = txtBankName.getText();
        String accountNumber = txtAccNumber.getText();
        String branchName = txtBranchName.getText();

        SupplierDto supplierDto = new SupplierDto(SupplierId, name, phoneNumber, bankName, accountNumber, branchName);

        try {
            boolean isSaved = supplierModel.addSupplier(supplierDto);
            if (isSaved) {
                loadData();
                clearFields();
                loadNextId();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Supplier Saved");
                alert.showAndWait();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to save Supplier");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnonActionDeleteSupplier(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete this supplier",
                ButtonType.YES, ButtonType.NO
        );
        Optional<ButtonType> response = alert.showAndWait();

        if (response.isPresent() && response.get() == ButtonType.YES) {
            try {
                String SupplierId = lblSupplier.getText();

                boolean isDeleted = supplierModel.deleteSupplier(SupplierId);
                if (isDeleted) {
                    loadData();
                    clearFields();
                    loadNextId();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Supplier Not Found").show();
                }
            } catch (SQLException e) {
                e.printStackTrace();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void btnonActionReset(ActionEvent event) {
 clearFields() ;
    }

    @FXML
    void btnonActionSaveSupplier(ActionEvent event) {

    }

    @FXML
    void btnonActionSearch(ActionEvent event) {
        String key = txtSearchheare.getText().trim();

        if (key.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter a Supplier ID or Name").show();
            return;
        }

        try {
            SupplierDto dto = SupplierModel.searchSupplier(key);

            if (dto != null) {
                lblSupplier.setText(dto.getSupplierID());
                txtName.setText(dto.getSupplierName());
                txtPhoneNumber.setText(dto.getPhoneNumber());
                txtBankName.setText(dto.getBankName());
                txtBranchName.setText(dto.getBranchName());
                txtAccNumber.setText(dto.getBankAccount());
            } else {
                clearFields();
                new Alert(Alert.AlertType.INFORMATION, "Supplier Not Found").show();
            }

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An error occurred while searching.").show();
        }
    }

    @FXML
    void btnonActionUpdateSupplier(ActionEvent event) {
        String SupplierId = lblSupplier.getText();
        String name = txtName.getText();
        String phoneNumber = txtPhoneNumber.getText();
        String bankName = txtBankName.getText();
        String accountNumber = txtAccNumber.getText();
        String branchName = txtBranchName.getText();

        SupplierDto supplierDto = new SupplierDto(SupplierId, name, phoneNumber, bankName, accountNumber, branchName);

        try {
            boolean isUpdate = supplierModel.updateSupplier(supplierDto);
            if (isUpdate) {
                loadData();
                clearFields();
                loadNextId();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Supplier Updated");
                alert.showAndWait();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to update Supplier");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colSupplierID.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        colBankName.setCellValueFactory(new PropertyValueFactory<>("bankName"));
        colBranchName.setCellValueFactory(new PropertyValueFactory<>("branchName"));
        colAccountNumber.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        loadData();
        loadNextId();
        setTabledataToFields();
    }

    private void loadNextId(){
        try {
            String id = supplierModel.getNextSupplierId();
            lblSupplier.setText(id);
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void clearFields() {
        txtAccNumber.clear();
        txtBankName.clear();
        txtBranchName.clear();
        txtName.clear();
        txtPhoneNumber.clear();
        txtSearchheare.clear();

    }
    private void loadData() {
        try {
            ArrayList<SupplierDto> supplierDtos = supplierModel.getSuppliers();
            ObservableList<SupplierTM> supplierTMObservableList = FXCollections.observableArrayList();

            for (SupplierDto dto : supplierDtos) {
                SupplierTM supplierTM = new SupplierTM(dto.getSupplierID(), dto.getSupplierName(), dto.getPhoneNumber(), dto.getBankName(), dto.getBankAccount(), dto.getBranchName());
                supplierTMObservableList.add(supplierTM);
            }
            System.out.println(supplierTMObservableList);
            tblSupplier.setItems(supplierTMObservableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setTabledataToFields() {
        tblSupplier.setOnMouseClicked(event -> {
            SupplierTM supplierTM = tblSupplier.getSelectionModel().getSelectedItem();
            if (supplierTM != null) {
                lblSupplier.setText(supplierTM.getSupplierId());
                txtName.setText(supplierTM.getSupplierName());
                txtPhoneNumber.setText(supplierTM.getPhoneNumber());
                txtBankName.setText(supplierTM.getBankName());
                txtBranchName.setText(supplierTM.getBranchName());
                txtAccNumber.setText(supplierTM.getAccountNumber());
            }
        });
    }
}
