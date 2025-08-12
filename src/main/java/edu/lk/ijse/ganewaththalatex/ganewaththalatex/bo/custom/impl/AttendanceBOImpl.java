package edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom.impl;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom.AttendanceBO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.util.EntityDTOConverter;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.DAOFactory;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.DAOTypes;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.custom.AttendanceDAO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.AttendanceDto;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.tm.AttendanceTM;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.entity.Attendance;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttendanceBOImpl implements AttendanceBO {
    private final AttendanceDAO attendanceDAO =
            (AttendanceDAO) DAOFactory.getInstance().getDAO(DAOTypes.ATTENDANCE);

    private final EntityDTOConverter converter = new EntityDTOConverter();

    @Override
    public boolean markAttendance(AttendanceDto attendanceDto) throws SQLException, ClassNotFoundException {
        Attendance attendance = converter.getAttendance(attendanceDto);
        return attendanceDAO.save(attendance);
    }

    @Override
    public boolean updateAttendance(AttendanceDto attendanceDto) throws SQLException, ClassNotFoundException {
        Attendance attendance = converter.getAttendance(attendanceDto);
        return attendanceDAO.update(attendance);
    }

    @Override
    public boolean deleteAttendance(String attendanceId) throws SQLException, ClassNotFoundException {
        return attendanceDAO.delete(attendanceId);
    }

    @Override
    public List<AttendanceTM> getAllAttendance() throws SQLException, ClassNotFoundException {
        List<Attendance> attendanceList = attendanceDAO.getAllWithEmployeeName(); // new DAO method
        List<AttendanceTM> tmList = new ArrayList<>();

        for (Attendance a : attendanceList) {
            tmList.add(new AttendanceTM(
                    a.getAttendanceId(),
                    a.getEmployeeId(),
                    a.getEmployeeName(),  // assuming DAO sets employeeName in Attendance entity
                    a.getStatus(),
                    a.getDate()
            ));
        }
        return tmList;
    }

    @Override
    public int[] getAttendanceCounts(String empId, String month) throws SQLException, ClassNotFoundException {
        return attendanceDAO.getAttendanceCounts(empId, month);
    }


}
