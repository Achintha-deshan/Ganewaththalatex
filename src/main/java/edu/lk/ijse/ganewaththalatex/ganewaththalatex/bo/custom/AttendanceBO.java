package edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.SuperBO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.AttendanceDto;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.tm.AttendanceTM;


import java.sql.SQLException;
import java.util.List;

public interface AttendanceBO extends SuperBO {
    boolean markAttendance(AttendanceDto attendanceDto) throws SQLException, ClassNotFoundException;
    boolean updateAttendance(AttendanceDto attendanceDto) throws SQLException, ClassNotFoundException;
    boolean deleteAttendance(String attendanceId) throws SQLException, ClassNotFoundException;
    List<AttendanceTM> getAllAttendance() throws SQLException, ClassNotFoundException;
    int[] getAttendanceCounts(String empId, String month) throws SQLException, ClassNotFoundException;


}
