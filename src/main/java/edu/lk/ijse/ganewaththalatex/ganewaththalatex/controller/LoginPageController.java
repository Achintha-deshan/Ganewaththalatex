package edu.lk.ijse.ganewaththalatex.ganewaththalatex.controller;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.model.LoginModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import javax.sound.midi.MidiFileFormat;
import java.sql.SQLException;

public class LoginPageController {
    @FXML
    private AnchorPane ancLogInPage;


    @FXML
    private Button btnLogin;

    @FXML
    private Hyperlink hyperForgetpass;

    @FXML
    private PasswordField txtPass;

    @FXML
    private TextField txtUserName;
    LoginModel loginModel = new LoginModel();

    @FXML
    void onactionmovepage(ActionEvent event) throws SQLException, NullPointerException, ClassNotFoundException {
        String userName = txtUserName.getText();
        String pass = txtPass.getText();
        String role = loginModel.login(userName,pass);

        if (role==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password");
            alert.showAndWait();
            txtUserName.clear();
            txtPass.clear();
        } else if (role.equals("admin")) {
                 navigateTo("/view/AdminPage.fxml");
        }else {
            navigateTo("/view/SupervisorPage.fxml");
        }

    }

    public void navigateTo(String path){
        try {
            ancLogInPage.getChildren().clear();
            Parent parent = FXMLLoader.load(getClass().getResource(path));
            ancLogInPage.getChildren().add(parent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
