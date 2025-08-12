package edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.SuperBO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO{

    ArrayList<SupplierDto> getAllSuppliers() throws SQLException, ClassNotFoundException;
    boolean saveSupplier(SupplierDto dto) throws Exception;
    boolean updateSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException;
    SupplierDto findSupplierById(String id) throws SQLException, ClassNotFoundException;
    String getNextSupplierId() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllSupplierIds() throws SQLException, ClassNotFoundException;
}
