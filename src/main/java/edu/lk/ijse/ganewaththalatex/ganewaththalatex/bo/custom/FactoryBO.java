package edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.SuperBO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.FactoryDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface FactoryBO extends SuperBO {
    List<FactoryDto> getAllFactories() throws SQLException, ClassNotFoundException;
    boolean saveFactory(FactoryDto dto) throws SQLException, ClassNotFoundException;
    boolean updateFactory(FactoryDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteFactory(String id) throws SQLException, ClassNotFoundException;
    FactoryDto findFactoryById(String id) throws SQLException, ClassNotFoundException;
    String getNextFactoryId() throws SQLException, ClassNotFoundException;
}
