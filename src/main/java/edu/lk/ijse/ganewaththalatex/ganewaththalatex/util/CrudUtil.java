package edu.lk.ijse.ganewaththalatex.ganewaththalatex.util;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUtil {
    // A generic method to execute SQL queries, with a flexible return type `T`.
    // It accepts an SQL statement and a variable number of parameters (obj) to insert into SQL query.
    public static <T> T execute(String sql, Object... obj) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement(sql);
        for (int i = 0; i < obj.length; i++) {
            pst.setObject(i + 1, obj[i]);
        }

        if (sql.startsWith("select") || sql.startsWith("SELECT")) {
            // If the SQL query is a SELECT statement, the prepared statement is executed as a query.

            // A ResultSet is returned, which contains the data retrieved from the database.
            ResultSet resultSet = pst.executeQuery();

            // The result set is returned, cast to the generic type `T`.
            return (T) resultSet;
        } else {
            // For non-SELECT queries (INSERT, UPDATE, DELETE), executeUpdate() is called.

            // This method returns the number of rows affected by the query.
            int i = pst.executeUpdate();

            // If one or more rows are affected, the operation is considered successful (`isSuccess = true`).
            boolean isSuccess = i > 0;

            // Returns the result of the operation (true if successful, false otherwise), cast to the generic type `T`.
            return (T) (Boolean) isSuccess;
        }
    }
}
