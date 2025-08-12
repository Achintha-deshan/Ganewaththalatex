package edu.lk.ijse.ganewaththalatex.ganewaththalatex.controller;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.BOFactory;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.BOTypes;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom.AttendanceBO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.custom.AttendanceDAO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.db.DBConnection;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.AttendanceDto;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.tm.AttendanceTM;
//import edu.lk.ijse.ganewaththalatex.ganewaththalatex.model.AttendanceModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class Attendance implements Initializable {

    private final AttendanceBO attendanceBO = BOFactory.getInstance().getBO(BOTypes.ATTENDANCE);

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnMArkAttendance;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cmbSupervisorID;

    @FXML
    private ComboBox<String> cmbattendance;

    @FXML
    private TableColumn<AttendanceTM, String> colattendance;

    @FXML
    private TableColumn<AttendanceTM, LocalDate> coldate;

    @FXML
    private TableColumn<AttendanceTM, String> colempid;

    @FXML
    private TableColumn<AttendanceTM, String> colname;

    @FXML
    private DatePicker datepicker;

    @FXML
    private TableView<AttendanceTM> tblAttendance;

    @FXML
    private TextField txtname;



    @FXML
    void btnonActionDeleteEmploye(ActionEvent event) {
        AttendanceTM selected = tblAttendance.getSelectionModel().getSelectedItem();
        if (selected == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a record to delete").show();
            return;
        }

        String attendanceId = selected.getAttendanceId();

        try {
            boolean deleted = attendanceBO.deleteAttendance(attendanceId);
            if (deleted) {
                new Alert(Alert.AlertType.INFORMATION, "Attendance deleted successfully").show();
                loadAttendanceTable();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to delete attendance").show();
        }

    }

    @FXML
    void btnonActionLoadREcords(ActionEvent event) {

    }

    @FXML
    void btnonActionMarkAttendance(ActionEvent event) {

        String attendanceId = "ATT" + System.currentTimeMillis(); // unique id
        String empId = cmbSupervisorID.getValue();
        String status = cmbattendance.getValue();
        LocalDate date = datepicker.getValue();

        if(empId == null || status == null || date == null){
            new Alert(Alert.AlertType.WARNING, "Please fill all fields").show();
            return;
        }

        AttendanceDto dto = new AttendanceDto(attendanceId, empId, status, date);
        try {
            boolean isSaved = attendanceBO.markAttendance(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION,  "Attendance marked successfully!").show();
                loadAttendanceTable();
                clearFields();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to save attendance!").show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void clearFields(){
        cmbSupervisorID.getSelectionModel().clearSelection();
        cmbattendance.getSelectionModel().clearSelection();
        datepicker.setValue(null);
        txtname.clear();
    }

    @FXML
    void btnonActionResetEmploye(ActionEvent event) {
        clearFields();

    }

    @FXML
    void btnonActionUpdateEmploye(ActionEvent event) {

       AttendanceTM selected = tblAttendance.getSelectionModel().getSelectedItem();
        if (selected == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a record to update").show();
            return;
        }

        String attendanceId = selected.getAttendanceId();
        String empId = cmbSupervisorID.getValue();
        String status = cmbattendance.getValue();
        LocalDate date = datepicker.getValue();

        if (empId == null || status == null || date == null) {
            new Alert(Alert.AlertType.WARNING, "Please fill all fields").show();
            return;
        }

        AttendanceDto dto = new AttendanceDto(attendanceId, empId, status, date);

        try {
            boolean updated = attendanceBO.updateAttendance(dto);
            if (updated) {
                new Alert(Alert.AlertType.INFORMATION, "Attendance updated successfully").show();
                loadAttendanceTable();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to update attendance").show();
        }

    }

    @FXML
    void cmbonActionSelectAttendance(ActionEvent event) {

    }

    @FXML
    void cmbonActionSelectSuplierID(ActionEvent event) {

        String empId = cmbSupervisorID.getValue();
        try {
            PreparedStatement ps = DBConnection.getInstance().getConnection()
                    .prepareStatement("SELECT name FROM Employee WHERE Employee_ID = ?");
            ps.setString(1, empId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                txtname.setText(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbattendance.getItems().addAll("Present", "Absent", "Half-Day");
        loadSupervisorIDs();

        colempid.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colname.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        colattendance.setCellValueFactory(new PropertyValueFactory<>("status"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));

        loadAttendanceTable();
        setTableSelectionListener();
    }

    private void loadSupervisorIDs() {
        cmbSupervisorID.getItems().clear();
        try {
            Connection con = DBConnection.getInstance().getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT Employee_ID FROM Employee");
            while (rs.next()) {
                cmbSupervisorID.getItems().add(rs.getString("Employee_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAttendanceTable() {
        try {
            List<AttendanceTM> attendanceList = attendanceBO.getAllAttendance();
            tblAttendance.getItems().setAll(attendanceList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setTableSelectionListener() {
        tblAttendance.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                cmbSupervisorID.setValue(newSelection.getEmployeeId());
               //
                cmbattendance.setValue(newSelection.getStatus());
                datepicker.setValue(newSelection.getDate());
            }
        });
    }

    public void btnonActionsalary(ActionEvent actionEvent) {
    }


    //   public void btnonActionsalary(ActionEvent actionEvent) {
   //     "/view/Salary.fxml"
    //}
}
