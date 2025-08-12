package edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.custom.impl;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.SQLUtil;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.custom.SupplierDAO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SupplierDAOImpl implements SupplierDAO {


    @Override
    public List<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT * FROM Supplier");
        List<Supplier> suppliers = new ArrayList<>();
        while(rs.next()){
            suppliers.add(new Supplier(
                    rs.getString("Supplier_ID"),
                    rs.getString("name"),
                    rs.getString("contact_number"),
                    rs.getString("bank_name"),
                    rs.getString("account_number"),
                    rs.getString("branch_name")
            ));
        }
        return suppliers;
    }

    @Override
    public List<String> getAllSupplierIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT Supplier_ID FROM Supplier");
        List<String> ids = new ArrayList<>();
        while(rs.next()){
            ids.add(rs.getString("Supplier_ID"));
        }
        return ids;
    }

    @Override
    public Optional<Supplier> findById(String id) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT * FROM Supplier WHERE Supplier_ID = ?", id);
        if(rs.next()){
            return Optional.of(new Supplier(
                    rs.getString("Supplier_ID"),
                    rs.getString("name"),
                    rs.getString("contact_number"),
                    rs.getString("bank_name"),
                    rs.getString("account_number"),
                    rs.getString("branch_name")
            ));
        }
        return Optional.empty();
    }

    @Override
    public boolean save(Supplier supplier) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Supplier VALUES (?,?,?,?,?,?)",
                supplier.getSupplierID(),
                supplier.getSupplierName(),
                supplier.getPhoneNumber(),
                supplier.getBankName(),
                supplier.getBankAccount(),
                supplier.getBranchName());
    }

    @Override
    public boolean update(Supplier supplier) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Supplier SET name=?, contact_number=?, bank_name=?, account_number=?, branch_name=? WHERE Supplier_ID=?",
                supplier.getSupplierName(),
                supplier.getPhoneNumber(),
                supplier.getBankName(),
                supplier.getBankAccount(),
                supplier.getBranchName(),
                supplier.getSupplierID());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Supplier WHERE Supplier_ID=?", id);
    }

    @Override
    public List<String> getAllids() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public Optional<Supplier> findbyId(String id) throws SQLException, ClassNotFoundException {
        return Optional.empty();
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT Supplier_ID FROM Supplier ORDER BY Supplier_ID DESC LIMIT 1");
        if(rs.next()){
            String lastId = rs.getString(1);
            int idNum = Integer.parseInt(lastId.substring(1)) + 1;
            return String.format("S%03d", idNum);
        }
        return "S001";
    }

}