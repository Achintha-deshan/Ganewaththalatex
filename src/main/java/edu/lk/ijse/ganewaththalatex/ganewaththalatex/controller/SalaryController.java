package edu.lk.ijse.ganewaththalatex.ganewaththalatex.controller;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom.AttendanceBO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom.EmployeBO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom.SalaryBO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom.impl.AttendanceBOImpl;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom.impl.EmployeeBOImpl;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom.impl.SalaryBOImpl;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.EmployeeDto;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.SalaryDTO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.tm.SalaryTM;
//import edu.lk.ijse.ganewaththalatex.ganewaththalatex.model.AttendanceModel;
//import edu.lk.ijse.ganewaththalatex.ganewaththalatex.model.EmployeeModel;
//import edu.lk.ijse.ganewaththalatex.ganewaththalatex.model.SalaryModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class SalaryController {

    private final EmployeBO employeBO = new EmployeeBOImpl();
    private final AttendanceBO attendanceBO = new AttendanceBOImpl();
    private final SalaryBO salaryBO = new SalaryBOImpl();
    @FXML
    private ComboBox<String> cmbEmployeeId;
    @FXML
    private TextField txtEmployeeName;
    @FXML
    private TextField txtMonth;
    @FXML
    private TextField txtPresentDays;
    @FXML
    private TextField txtHalfDays;
    @FXML
    private TextField txtDailyRate;
    @FXML
    private TextField txtTotalSalary;
    @FXML
    private TableView<SalaryTM> tblSalary;
    @FXML
    private TableColumn<SalaryTM, String> colEmployeeId;
    @FXML
    private TableColumn<SalaryTM, String> colEmployeeName;
    @FXML
    private TableColumn<SalaryTM, String> colMonth;
    @FXML
    private TableColumn<SalaryTM, Integer> colPresentDays;
    @FXML
    private TableColumn<SalaryTM, Integer> colHalfDays;
    @FXML
    private TableColumn<SalaryTM, Double> colDailyRate;
    @FXML
    private TableColumn<SalaryTM, Double> colTotalSalary;

    @FXML
    public void initialize() {
        loadEmployeeIds();
        setCellValueFactory();
        loadSalaryTable();


        cmbEmployeeId.setOnAction(event -> autoFillAttendanceData());

        txtDailyRate.textProperty().addListener((observable, oldValue, newValue) -> {
            calculateSalaryAuto();  // auto calculate when daily rate changes
        });
    }

    private void loadEmployeeIds() {
        try {
            List<EmployeeDto> employees = employeBO.getAllEmployees();
            for (EmployeeDto emp : employees) {
                cmbEmployeeId.getItems().add(emp.getEmployerID());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setCellValueFactory() {
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        colMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        colPresentDays.setCellValueFactory(new PropertyValueFactory<>("totalPresentDays"));
        colHalfDays.setCellValueFactory(new PropertyValueFactory<>("totalHalfDays"));
        colDailyRate.setCellValueFactory(new PropertyValueFactory<>("dailyRate"));
        colTotalSalary.setCellValueFactory(new PropertyValueFactory<>("totalSalary"));
    }

    private void loadSalaryTable() {
        try {
            List<SalaryDTO> list = salaryBO.getAllSalaries();
            ObservableList<SalaryTM> obList = FXCollections.observableArrayList();

            for (SalaryDTO dto : list) {
                EmployeeDto emp = employeBO.findEmployeeById(dto.getEmployeeId());
                obList.add(new SalaryTM(
                        dto.getSalaryId(),
                        dto.getEmployeeId(),
                        emp != null ? emp.getEmployerName() : "",
                        dto.getMonth(),
                        dto.getTotalPresentDays(),
                        dto.getTotalHalfDays(),
                        dto.getDailyRate().doubleValue(),
                        dto.getTotalSalary().doubleValue()
                ));
            }
            tblSalary.setItems(obList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnonActionClear(ActionEvent event) {
        cmbEmployeeId.getSelectionModel().clearSelection();
        txtEmployeeName.clear();
        txtMonth.clear();
        txtPresentDays.clear();
        txtHalfDays.clear();
        txtDailyRate.clear();
        txtTotalSalary.clear();
    }

    @FXML
    void btnonActionDeleteEmploye(ActionEvent event) {
        SalaryTM selected = tblSalary.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                boolean deleted = salaryBO.deleteSalary(selected.getSalaryId());
                if (deleted) {
                    new Alert(Alert.AlertType.INFORMATION, "Salary deleted").show();
                    loadSalaryTable();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void btnonActionUpdateEmploye(ActionEvent event) {
        SalaryTM selected = tblSalary.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                SalaryDTO dto = new SalaryDTO(
                        selected.getSalaryId(),
                        cmbEmployeeId.getValue(),
                        txtMonth.getText(),
                        Integer.parseInt(txtPresentDays.getText()),
                        Integer.parseInt(txtHalfDays.getText()),
                        new BigDecimal(txtDailyRate.getText()),
                        new BigDecimal(txtTotalSalary.getText())
                );

                boolean updated = salaryBO.updateSalary(dto);
                if (updated) {
                    new Alert(Alert.AlertType.INFORMATION, "Salary updated").show();
                    loadSalaryTable();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void autoFillAttendanceData() {
        String empId = cmbEmployeeId.getValue();
        String month = txtMonth.getText(); // format: "2025-07"

        if (empId != null && !month.isEmpty()) {
            try {
                EmployeeDto emp = employeBO.findEmployeeById(empId);
                if (emp != null) {
                    txtEmployeeName.setText(emp.getEmployerName());
                }

                int[] counts = attendanceBO.getAttendanceCounts(empId, month);
                txtPresentDays.setText(String.valueOf(counts[0]));
                txtHalfDays.setText(String.valueOf(counts[1]));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void calculateSalaryAuto() {
        try {
            String presentDaysText = txtPresentDays.getText().trim();
            String halfDaysText = txtHalfDays.getText().trim();
            String dailyRateText = txtDailyRate.getText().trim();

            if (!presentDaysText.isEmpty() && !halfDaysText.isEmpty() && !dailyRateText.isEmpty()) {
                int full = Integer.parseInt(presentDaysText);
                int half = Integer.parseInt(halfDaysText);
                BigDecimal rate = new BigDecimal(dailyRateText);

                BigDecimal fullTotal = rate.multiply(BigDecimal.valueOf(full));
                BigDecimal halfTotal = rate.multiply(BigDecimal.valueOf(half)).divide(BigDecimal.valueOf(2));

                BigDecimal total = fullTotal.add(halfTotal);
                txtTotalSalary.setText(total.toPlainString());
            }
        } catch (Exception e) {
            // Optional: clear total if invalid input
            txtTotalSalary.clear();
        }
    }

    public void btnonActionCalculatesalary(ActionEvent actionEvent) {

        try {
            String presentDaysText = txtPresentDays.getText().trim();
            String halfDaysText = txtHalfDays.getText().trim();
            String dailyRateText = txtDailyRate.getText().trim();

            if (presentDaysText.isEmpty() || halfDaysText.isEmpty() || dailyRateText.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please fill all fields").show();
                return;
            }

            int full = Integer.parseInt(presentDaysText);
            int half = Integer.parseInt(halfDaysText);
            BigDecimal rate = new BigDecimal(dailyRateText);

            BigDecimal fullTotal = rate.multiply(BigDecimal.valueOf(full));
            BigDecimal halfTotal = rate.multiply(BigDecimal.valueOf(half)).divide(BigDecimal.valueOf(2));

            BigDecimal total = fullTotal.add(halfTotal);

            txtTotalSalary.setText(total.toPlainString());  // use toPlainString() for cleaner output

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid number input").show();
            e.printStackTrace();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
            e.printStackTrace();
        }
    }

    public void btnonActionsavesalary(ActionEvent actionEvent) {
        try {
            String nextId = salaryBO.getNextSalaryId();

            SalaryDTO dto = new SalaryDTO(
                    nextId,
                    cmbEmployeeId.getValue(),
                    txtMonth.getText(),
                    Integer.parseInt(txtPresentDays.getText()),
                    Integer.parseInt(txtHalfDays.getText()),
                    new BigDecimal(txtDailyRate.getText()),
                    new BigDecimal(txtTotalSalary.getText())
            );

            boolean isSaved = salaryBO.saveSalary(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Salary Saved").show();
                loadSalaryTable();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to save salary").show();
        }
    }

}
