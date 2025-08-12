package edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.util;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.SalaryDTO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.entity.Salary;

public class SalaryEntityDtoConverter {

    public Salary getSalaryEntity(SalaryDTO dto) {
        return new Salary(
                dto.getSalaryId(),
                dto.getEmployeeId(),
                dto.getMonth(),
                dto.getTotalPresentDays(),
                dto.getTotalHalfDays(),
                dto.getDailyRate(),
                dto.getTotalSalary()
        );
    }

    public SalaryDTO getSalaryDTO(Salary entity) {
        return new SalaryDTO(
                entity.getSalaryId(),
                entity.getEmployeeId(),
                entity.getMonth(),
                entity.getTotalPresentDays(),
                entity.getTotalHalfDays(),
                entity.getDailyRate(),
                entity.getTotalSalary()
        );
    }
}
