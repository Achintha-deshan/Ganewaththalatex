package edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom.impl;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom.SupplierBO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.util.SupplierEntityDtoConverter;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.DAOFactory;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.DAOTypes;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.custom.SupplierDAO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.SupplierDto;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SupplierBOImpl implements SupplierBO {

    private final SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getInstance().getDAO(DAOTypes.SUPPLIER);
    private final SupplierEntityDtoConverter converter = new SupplierEntityDtoConverter();

    @Override
    public ArrayList<SupplierDto> getAllSuppliers() throws SQLException, ClassNotFoundException {
        List<Supplier> suppliers = supplierDAO.getAll();
        ArrayList<SupplierDto> dtos = new ArrayList<>();
        for (Supplier s : suppliers) {
            dtos.add(converter.getSupplierDto(s));
        }
        return dtos;
    }

    @Override
    public boolean saveSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException{
        return supplierDAO.save(converter.getSupplier(dto));
    }


    @Override
    public boolean updateSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(converter.getSupplier(dto));
    }

    @Override
    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);
    }

    @Override
    public SupplierDto findSupplierById(String id) throws SQLException, ClassNotFoundException {
        Optional<Supplier> supplier = supplierDAO.findById(id);
        return supplier.map(converter::getSupplierDto).orElse(null);
    }

    @Override
    public String getNextSupplierId() throws SQLException, ClassNotFoundException {
        return supplierDAO.getNextId();
    }

    @Override
    public ArrayList<String> getAllSupplierIds() throws SQLException, ClassNotFoundException {
        List<String> ids = supplierDAO.getAllSupplierIds();
        return new ArrayList<>(ids);
    }
}
