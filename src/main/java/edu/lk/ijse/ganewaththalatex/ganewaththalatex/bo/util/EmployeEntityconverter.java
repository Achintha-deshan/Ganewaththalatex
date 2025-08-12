package edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.util;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.EmployeeDto;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.entity.Employee;

public class EmployeEntityconverter {
    // DTO → Entity
    public Employee getEmployee(EmployeeDto dto) {
        return new Employee(
                dto.getEmployerID(),
                dto.getEmployerName(),
                dto.getPhonenumber()
        );
    }

    // Entity → DTO
    public EmployeeDto getEmployeeDto(Employee entity) {
        return new EmployeeDto(
                entity.getEmployerID(),
                entity.getEmployerName(),
                entity.getPhoneNumber()
        );
    }
}
