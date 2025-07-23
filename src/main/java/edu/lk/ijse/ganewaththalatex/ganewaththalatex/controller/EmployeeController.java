package edu.lk.ijse.ganewaththalatex.ganewaththalatex.controller;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.EmployeeDto;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.tm.EmployeTM;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.model.EmployeeModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    @FXML
    private AnchorPane ancEmpPageLorder;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<EmployeTM, String> colEmpID;

    @FXML
    private TableColumn<EmployeTM, String> colName;

    @FXML
    private TableColumn<EmployeTM, String> colPhoneNumber;

    @FXML
    private Label lblEmpID;

    @FXML
    private TableView<EmployeTM> tblEmploye;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtSearchheare;

    @FXML
    void btnonActionAddEmploye(ActionEvent event) {

        String employerID = lblEmpID.getText().trim();
        String employerName = txtName.getText();
        String phonenumber = txtPhoneNumber.getText();

        EmployeeDto employeeDto = new EmployeeDto(employerID, employerName,phonenumber);

        try {
            boolean isSaved = EmployeeModel.addEmployee(employeeDto);
            if (isSaved) {
                loadData();
                loadNextId();
                clearFields();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Employee added successfully");
                alert.showAndWait();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to add employee");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnonActionDeleteEmploye(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete this Employee?",
                ButtonType.YES, ButtonType.NO
        );
        Optional<ButtonType> response = alert.showAndWait();

        if (response.isPresent() && response.get() == ButtonType.YES) {
            try {
                String EmployeeID = lblEmpID.getText();

                boolean isDeleted = EmployeeModel.deleteEmployee(EmployeeID);
                if (isDeleted) {
                    loadData();
                    clearFields();
                    loadNextId();

                }else {
                    new Alert(Alert.AlertType.ERROR,"Employee Not Found").show();
                }
            } catch (SQLException e) {
                e.printStackTrace();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    void btnonActionResetEmploye(ActionEvent event) {
        clearFields();

    }

    @FXML
    void btnonActionSearchEmploye(ActionEvent event) {
        String input = txtSearchheare.getText();

        try {
            EmployeeDto dto = EmployeeModel.searchEmployee(input);

            if (dto != null) {
                lblEmpID.setText(dto.getEmployerID());
                txtName.setText(dto.getEmployerName());
                txtPhoneNumber.setText(dto.getPhonenumber());

            } else {
                clearFields();
                new Alert(Alert.AlertType.INFORMATION, "Employee Not Found").showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong").showAndWait();
        }
    }



    @FXML
    void btnonActionUpdateEmploye(ActionEvent event) {


        String employerID = lblEmpID.getText().trim();
        String employerName = txtName.getText();
        String phonenumber = txtPhoneNumber.getText();

        EmployeeDto employeeDto = new EmployeeDto(employerID, employerName,phonenumber);
        try {
            boolean isUpdate = EmployeeModel.updateEmployee(employeeDto);
            if (isUpdate) {
                loadData();
                loadNextId();
                clearFields();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Employee updated successfully");
                alert.showAndWait();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to update employee");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colEmpID.setCellValueFactory(new PropertyValueFactory<>("employerID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("employerName"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));


        loadData();
        loadNextId();
        settableDatetoFields();

    }
    private void loadData(){
        try {
            ArrayList<EmployeeDto> employeeDtos = EmployeeModel.getEmployees();
            ObservableList<EmployeTM> employeesTMObservableList = FXCollections.observableArrayList();

            for (EmployeeDto employeeDto : employeeDtos) {
                EmployeTM employeTM = new EmployeTM(employeeDto.getEmployerID(),employeeDto.getEmployerName(),employeeDto.getPhonenumber());
                employeesTMObservableList.add(employeTM);
            }
            tblEmploye.setItems(employeesTMObservableList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadNextId(){
        try {
            String id = EmployeeModel.getEmployeeID();
            lblEmpID.setText(id);
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void settableDatetoFields(){
        tblEmploye.setOnMouseClicked(event -> {
            EmployeTM employeTM = tblEmploye.getSelectionModel().getSelectedItem();
            if (employeTM != null) {
                lblEmpID.setText(employeTM.getEmployerID());
                txtName.setText(employeTM.getEmployerName());
                txtPhoneNumber.setText(employeTM.getPhonenumber());
            }
        });
    }

    public void clearFields(){
        txtName.clear();
        txtPhoneNumber.clear();
        txtSearchheare.clear();
    }


    public void btnonActionAttandanceandsalary(ActionEvent actionEvent) {
        navigate("/view/Attendance.fxml");
    }

    public void navigate(String path) {
        try{
            ancEmpPageLorder.getChildren().clear();
            Parent parent = FXMLLoader.load(getClass().getResource(path));
            ancEmpPageLorder.getChildren().add(parent);
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnonActionsalary(ActionEvent actionEvent) {
        navigate("/view/Salary.fxml");
    }
}
