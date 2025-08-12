package edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom.impl;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom.SalaryBO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.util.SalaryEntityDtoConverter;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.DAOFactory;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.DAOTypes;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.custom.SalaryDAO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.SalaryDTO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.entity.Salary;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SalaryBOImpl implements SalaryBO {


    private final SalaryDAO salaryDAO = (SalaryDAO) DAOFactory.getInstance().getDAO(DAOTypes.SALARY);
    private final SalaryEntityDtoConverter converter = new SalaryEntityDtoConverter();

    @Override
    public boolean saveSalary(SalaryDTO salary) throws SQLException, ClassNotFoundException {
        Salary entity = converter.getSalaryEntity(salary);
        return salaryDAO.save(entity);
    }

    @Override
    public boolean updateSalary(SalaryDTO salary) throws SQLException, ClassNotFoundException {
        Salary entity = converter.getSalaryEntity(salary);
        return salaryDAO.update(entity);
    }

    @Override
    public boolean deleteSalary(String salaryId) throws SQLException, ClassNotFoundException {
        return salaryDAO.delete(salaryId);
    }

    @Override
    public List<SalaryDTO> getAllSalaries() throws SQLException, ClassNotFoundException {
        List<Salary> allSalaries = salaryDAO.getAll();
        List<SalaryDTO> dtoList = new ArrayList<>();
        for (Salary s : allSalaries) {
            dtoList.add(converter.getSalaryDTO(s));
        }
        return dtoList;
    }

    @Override
    public SalaryDTO findSalaryById(String salaryId) throws SQLException, ClassNotFoundException {
        Optional<Salary> optional = salaryDAO.findbyId(salaryId);
        return optional.map(converter::getSalaryDTO).orElse(null);
    }
    @Override
    public String getNextSalaryId() throws SQLException, ClassNotFoundException {
        String lastId = salaryDAO.getLastSalaryId();
        if (lastId != null) {
            int num = Integer.parseInt(lastId.substring(1));
            num++;
            return String.format("S%03d", num);
        }
        return "S001";
    }

}
