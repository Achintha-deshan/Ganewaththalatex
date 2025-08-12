package edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.custom;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.CrudDAO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.entity.Attendance;

import java.sql.SQLException;
import java.util.List;

public interface AttendanceDAO extends CrudDAO<Attendance> {
    List<Attendance> getAllWithEmployeeName() throws SQLException, ClassNotFoundException;
    int[] getAttendanceCounts(String empId, String month) throws SQLException, ClassNotFoundException;


}
