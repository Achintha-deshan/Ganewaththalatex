package edu.lk.ijse.ganewaththalatex.ganewaththalatex.model;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    public String login(String username, String pass) throws SQLException, ClassNotFoundException {
        String role = null;
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select * from user where user_id = ? and user_password = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, pass);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            role = resultSet.getString("user_role");
        }
        return role;
    }
}
