//package edu.lk.ijse.ganewaththalatex.ganewaththalatex.controller;
//
//import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.EmployeeDto;
//import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.SalaryDTO;
//import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.tm.SalaryTM;
//import edu.lk.ijse.ganewaththalatex.ganewaththalatex.model.EmployeeModel;
//import edu.lk.ijse.ganewaththalatex.ganewaththalatex.model.SalaryModel;
////import edu.lk.ijse.ganewaththalatex.ganewaththalatex.tm.SalaryTM;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//
//import java.sql.SQLException;
//import java.util.List;
//
//public class SalaryController {
//
//    @FXML private ComboBox<String> cmbEmployeeId;
//    @FXML private TextField txtEmployeeName;
//    @FXML private TextField txtMonth;
//    @FXML private TextField txtPresentDays;
//    @FXML private TextField txtHalfDays;
//    @FXML private TextField txtDailyRate;
//    @FXML private TextField txtTotalSalary;
//    @FXML private TableView<SalaryTM> tblSalary;
//    @FXML private TableColumn<SalaryTM, String> colEmployeeId;
//    @FXML private TableColumn<SalaryTM, String> colEmployeeName;
//    @FXML private TableColumn<SalaryTM, String> colMonth;
//    @FXML private TableColumn<SalaryTM, Integer> colPresentDays;
//    @FXML private TableColumn<SalaryTM, Integer> colHalfDays;
//    @FXML private TableColumn<SalaryTM, Double> colDailyRate;
//    @FXML private TableColumn<SalaryTM, Double> colTotalSalary;
//
//    @FXML private Button btnSave;
//    @FXML private Button btnUpdate;
//    @FXML private Button btnDelete;
//    @FXML private Button btnCalculate;
//    @FXML private Button btnClear;
//
//    private ObservableList<SalaryTM> salaryList = FXCollections.observableArrayList();
//    private String selectedSalaryId;
//    private Object EmployeeModel;
//
//    public void initialize() {
//        setCellValueFactory();
//        loadEmployeeIds();
//        loadAllSalaries();
//
//        cmbEmployeeId.setOnAction(e -> loadEmployeeName());
//
//        tblSalary.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
//            if (newVal != null) {
//                selectedSalaryId = newVal.getSalaryId();
//                cmbEmployeeId.setValue(newVal.getEmployeeId());
//                txtEmployeeName.setText(newVal.getEmployeeName());
//                txtMonth.setText(newVal.getMonth());
//                txtPresentDays.setText(String.valueOf(newVal.getTotalPresentDays()));
//                txtHalfDays.setText(String.valueOf(newVal.getTotalHalfDays()));
//                txtDailyRate.setText(String.valueOf(newVal.getDailyRate()));
//                txtTotalSalary.setText(String.valueOf(newVal.getTotalSalary()));
//            }
//        });
//    }
//
//    private void setCellValueFactory() {
////        colEmployeeId.setCellValueFactory(data -> data.getValue().employeeIdProperty());
////        colEmployeeName.setCellValueFactory(data -> data.getValue().employeeNameProperty());
////        colMonth.setCellValueFactory(data -> data.getValue().monthProperty());
////        colPresentDays.setCellValueFactory(data -> data.getValue().totalPresentDaysProperty().asObject());
////        colHalfDays.setCellValueFactory(data -> data.getValue().totalHalfDaysProperty().asObject());
////        colDailyRate.setCellValueFactory(data -> data.getValue().dailyRateProperty().asObject());
////        colTotalSalary.setCellValueFactory(data -> data.getValue().totalSalaryProperty().asObject());
//    }
//
//    private void loadEmployeeIds() {
//        try {
//          //  List<String> ids = EmployeeModel.getEmployeeID();
//            cmbEmployeeId.setItems(FXCollections.observableArrayList(ids));
//        } catch (Exception e) {
//            showAlert("Error loading employee IDs: " + e.getMessage());
//        }
//    }
//
//    private void loadEmployeeName() {
//        String empId = cmbEmployeeId.getValue();
//        try {
//          //  EmployeeDTO emp = EmployeeModel.getEmployee(empId);
//          //  txtEmployeeName.setText(emp.getEmployeeName());
//        } catch (Exception e) {
//            showAlert("Error loading employee name");
//        }
//    }
//
//    private void loadAllSalaries() {
//        try {
//          //  List<SalaryTM> list = SalaryModel.getAllSalaries();
//            salaryList.setAll(list);
//            tblSalary.setItems(salaryList);
//        } catch (Exception e) {
//            showAlert("Error loading salary table");
//        }
//    }
//
//    @FXML
//    void btnonActionCalculate(ActionEvent event) {
//        try {
//            int present = Integer.parseInt(txtPresentDays.getText());
//            int half = Integer.parseInt(txtHalfDays.getText());
//            double rate = Double.parseDouble(txtDailyRate.getText());
//
//            double total = (present * rate) + (half * rate / 2.0);
//            txtTotalSalary.setText(String.format("%.2f", total));
//        } catch (Exception e) {
//            showAlert("Invalid data for calculation.");
//        }
//    }
//
//    @FXML
//    void btnonActionSave(ActionEvent event) {
//        try {
//            SalaryDTO dto = new SalaryDTO(
//                    null,
//                    cmbEmployeeId.getValue(),
//                    txtMonth.getText(),
//                    Integer.parseInt(txtPresentDays.getText()),
//                    Integer.parseInt(txtHalfDays.getText()),
//                   // Double.parseDouble(txtDailyRate.getText()),
//                    //Double.parseDouble(txtTotalSalary.getText())
//            );
//
//            boolean isSaved = SalaryModel.saveSalary(dto);
//            if (isSaved) {
//                showAlert("Salary saved.");
//                loadAllSalaries();
//                clearFields();
//            }
//        } catch (Exception e) {
//            showAlert("Save failed: " + e.getMessage());
//        }
//    }
//
//    @FXML
//    void btnonActionUpdateEmploye(ActionEvent event) {
//        if (selectedSalaryId == null) {
//            showAlert("Please select a row.");
//            return;
//        }
//
//        try {
//            SalaryDTO dto = new SalaryDTO(
//                    selectedSalaryId,
//                    cmbEmployeeId.getValue(),
//                    txtMonth.getText(),
//                    Integer.parseInt(txtPresentDays.getText()),
//                    Integer.parseInt(txtHalfDays.getText()),
//                    Double.parseDouble(txtDailyRate.getText()),
//                    Double.parseDouble(txtTotalSalary.getText())
//            );
//
//            boolean isUpdated = SalaryModel.updateSalary(dto);
//            if (isUpdated) {
//                showAlert("Salary updated.");
//                loadAllSalaries();
//                clearFields();
//            }
//        } catch (Exception e) {
//            showAlert("Update failed.");
//        }
//    }
//
//    @FXML
//    void btnonActionDeleteEmploye(ActionEvent event) {
//        if (selectedSalaryId == null) {
//            showAlert("Please select a row.");
//            return;
//        }
//
//        try {
//            boolean deleted = SalaryModel.deleteSalary(selectedSalaryId);
//            if (deleted) {
//                showAlert("Salary deleted.");
//                loadAllSalaries();
//                clearFields();
//            }
//        } catch (Exception e) {
//            showAlert("Delete failed.");
//        }
//    }
//
//    @FXML
//    void btnonActionClear(ActionEvent event) {
//        clearFields();
//    }
//
//    private void clearFields() {
//        selectedSalaryId = null;
//        cmbEmployeeId.setValue(null);
//        txtEmployeeName.clear();
//        txtMonth.clear();
//        txtPresentDays.clear();
//        txtHalfDays.clear();
//        txtDailyRate.clear();
//        txtTotalSalary.clear();
//    }
//
//    private void showAlert(String msg) {
//        new Alert(Alert.AlertType.INFORMATION, msg).show();
//    }
//}
