package edu.lk.ijse.ganewaththalatex.ganewaththalatex.controller;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.InventoryDto;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.SupplierDto;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.tm.InventoryTM;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.model.InventoryModel;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.model.SupplierModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class InventoryController implements Initializable {

    private final InventoryModel inventoryModel = new InventoryModel();

    @FXML
    private ComboBox<String> cobSupID;

    @FXML
    private TableColumn<InventoryTM, String> colDAteadd, colGana, colSupID, colinveID;

    @FXML
    private TableColumn<InventoryTM, Integer> colQTY;

    @FXML
    private TableColumn<InventoryTM, Double> colPrice;

    @FXML
    private Label lblInwentID, lblSupName;

    @FXML
    private TableView<InventoryTM> tblInventory;

    @FXML
    private TextField txtDateadd, txtGanathwya, txtQTY, txtrateforliter;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadSuplierID();
        loadNextID();
        loadInventoryTable();
        setTableDataToFields();
        txtDateadd.setText(String.valueOf(LocalDate.now()));
    }

    private void loadSuplierID() {
        try {
            List<SupplierDto> suppliers = new SupplierModel().getSuppliers();
            cobSupID.getItems().clear();
            for (SupplierDto supplier : suppliers) {
                cobSupID.getItems().add(supplier.getSupplierID());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadNextID() {
        try {
            lblInwentID.setText(InventoryModel.getInventoryID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadInventoryTable() {
        try {
            List<InventoryDto> inventoryList = InventoryModel.getAllInventory();
            ObservableList<InventoryTM> observableList = FXCollections.observableArrayList();

            for (InventoryDto dto : inventoryList) {
                observableList.add(new InventoryTM(
                        dto.getInventoryID(),
                        dto.getSupplierID(),
                        dto.getSupplierName(),
                        dto.getDateAdded(),
                        dto.getQuantity(),
                        dto.getRate(),
                        dto.getPrice()
                ));
            }

            colinveID.setCellValueFactory(new PropertyValueFactory<>("inventoryID"));
            colSupID.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
            colGana.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
            colDAteadd.setCellValueFactory(new PropertyValueFactory<>("dateAdded"));
            colQTY.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

            tblInventory.setItems(observableList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearInputs() {
        cobSupID.setValue(null);
        lblSupName.setText("");
        txtDateadd.setText(String.valueOf(LocalDate.now()));
        txtQTY.clear();
        txtrateforliter.clear();
        txtrateforliter.clear();
        loadNextID();  // Reset Inventory ID for next input
    }

    @FXML
    void cobsetSupplierID(ActionEvent event) {
        try {
            String selectedID = cobSupID.getValue();
            if (selectedID != null) {
                SupplierDto dto = SupplierModel.searchSupplier(selectedID);
                lblSupName.setText(dto != null ? dto.getSupplierName() : "Supplier Not Found");
            }
        } catch (Exception e) {
            lblSupName.setText("Error loading supplier");
            e.printStackTrace();
        }
    }

    public void btnonActionalldetailssave(ActionEvent actionEvent) {
        try {
            String inventoryID = lblInwentID.getText();
            String supplierID = cobSupID.getValue();
            String supplierName = lblSupName.getText();
            String dateAdded = txtDateadd.getText();
            String quantity = txtQTY.getText();
            String rate = txtrateforliter.getText();


            if (supplierID == null || supplierID.isEmpty() || quantity.isEmpty() || rate.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Please fill all required fields.");
                return;
            }


            int qty = Integer.parseInt(quantity);
            double ratePerLiter = Double.parseDouble(rate);
            int currentQty = InventoryModel.getTotalQuantityforInventory(inventoryID);
            int maxCapacity = 10000;

            if (currentQty + qty >= maxCapacity) {
                showAlert(Alert.AlertType.WARNING,
                        "Inventory full (" + currentQty + "L). Switching to next.");
                loadNextID();
                clearInputs();
                return;
            }

            double price = qty * ratePerLiter;

            InventoryDto dto = new InventoryDto(inventoryID, supplierID, supplierName, dateAdded, qty, ratePerLiter, price);

            if (InventoryModel.addInventory(dto)) {
                showAlert(Alert.AlertType.INFORMATION, "Inventory added successfully!");
                loadInventoryTable();
                loadNextID();
                clearInputs();
            } else {
                showAlert(Alert.AlertType.ERROR, "Failed to add inventory.");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Quantity and rate must be numbers.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void btnonActionUpdate(ActionEvent event) {
        try {
            int qty = Integer.parseInt(txtQTY.getText());
            double ratePerLiter = Double.parseDouble(txtrateforliter.getText());
            double price = qty * ratePerLiter;

            InventoryDto dto = new InventoryDto(
                    lblInwentID.getText(),
                    cobSupID.getValue(),
                    lblSupName.getText(),
                    txtDateadd.getText(),
                    qty,
                    ratePerLiter,
                    price
            );
            if (inventoryModel.updateInventory(dto)) {
                showAlert(Alert.AlertType.INFORMATION, "Inventory updated.");
                loadInventoryTable();
                clearInputs();
                loadNextID();
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Quantity and rate must be numbers.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Update failed.");
            e.printStackTrace();
        }
    }

    public void btnonActionalldetailsdelete(ActionEvent event) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete this Inventory?",
                ButtonType.YES, ButtonType.NO
        );
        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            try {
                String id = lblInwentID.getText();
                if (InventoryModel.deleteInventory(id)) {
                    showAlert(Alert.AlertType.INFORMATION, "Deleted successfully.");
                    loadInventoryTable();
                    clearInputs();
                    loadNextID();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Inventory not found.");
                }
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Error deleting inventory.");
                e.printStackTrace();
            }
        }
    }

    public void btnonactionReset(ActionEvent event) {
        clearInputs();
    }

    public void btnonActionSearch(ActionEvent event) {
        String id = lblInwentID.getText();
        try {
            InventoryDto dto = InventoryModel.searchInventory(id);
            if (dto != null) {
                cobSupID.setValue(dto.getSupplierID());
                lblSupName.setText(dto.getSupplierName());
                txtDateadd.setText(dto.getDateAdded());
                txtQTY.setText(String.valueOf(dto.getQuantity()));
                txtrateforliter.setText(String.valueOf(dto.getRate()));
            } else {
                showAlert(Alert.AlertType.INFORMATION, "No Inventory found.");
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error searching inventory.");
            e.printStackTrace();
        }
    }

    private void setTableDataToFields() {
        tblInventory.setOnMouseClicked(event -> {
            InventoryTM selected = tblInventory.getSelectionModel().getSelectedItem();
            if (selected != null) {
                lblInwentID.setText(selected.getInventoryID());
                cobSupID.setValue(selected.getSupplierID());
                lblSupName.setText(selected.getSupplierName());
                txtDateadd.setText(selected.getDateAdded());
                txtQTY.setText(String.valueOf(selected.getQuantity()));
                txtrateforliter.setText(String.valueOf(selected.getRate()));
            }
        });
    }

    private void showAlert(Alert.AlertType type, String msg) {
        new Alert(type, msg).show();
    }
}
