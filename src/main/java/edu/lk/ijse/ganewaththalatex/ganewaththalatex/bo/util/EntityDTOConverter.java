package edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.util;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.entity.Attendance;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.AttendanceDto;

public class EntityDTOConverter {
//    dto ekak entity ekakat
    public Attendance getAttendance(AttendanceDto attendanceDto) {
        return new Attendance(
                attendanceDto.getAttendanceId(),
                attendanceDto.getEmployeeId(),
                attendanceDto.getStatus(),
                attendanceDto.getDate(),
                null
        );
    }

//    entity ekak dto ekakay
    public AttendanceDto getAttendanceDto(Attendance attendance) {
        return new AttendanceDto(
                attendance.getAttendanceId(),
                attendance.getEmployeeId(),
                attendance.getStatus(),
                attendance.getDate()
        );
    }
}
