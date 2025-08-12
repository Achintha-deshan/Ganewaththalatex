package edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.custom.impl;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.SQLUtil;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.custom.FactoryDAO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.entity.Factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class FactoryDAOImpl implements FactoryDAO {
    @Override
    public ArrayList<Factory> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT * FROM Factory");
        ArrayList<Factory> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Factory(
                    rs.getString("Factory_ID"),
                    rs.getString("name"),
                    rs.getString("contact_number"),
                    rs.getString("address")
            ));
        }
        return list;
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT Factory_ID FROM Factory ORDER BY Factory_ID DESC LIMIT 1");
        if (rs.next()) {
            String lastID = rs.getString(1);
            int newIDNum = Integer.parseInt(lastID.substring(1)) + 1;
            return String.format("F%03d", newIDNum);
        }
        return "F001";
    }

    @Override
    public boolean save(Factory factory) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Factory VALUES (?, ?, ?, ?)",
                factory.getFactoryID(),
                factory.getFactoryName(),
                factory.getPhoneNumber(),
                factory.getAddress());
    }

    @Override
    public boolean update(Factory factory) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Factory SET name=?, contact_number=?, address=? WHERE Factory_ID=?",
                factory.getFactoryName(),
                factory.getPhoneNumber(),
                factory.getAddress(),
                factory.getFactoryID());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Factory WHERE Factory_ID=?", id);
    }

    @Override
    public ArrayList<String> getAllids() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT Factory_ID FROM Factory");
        ArrayList<String> ids = new ArrayList<>();
        while (rs.next()) {
            ids.add(rs.getString("Factory_ID"));
        }
        return ids;
    }
    @Override
    public Optional<Factory> findbyId(String id) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT * FROM Factory WHERE Factory_ID = ?", id);
        if (rs.next()) {
            return Optional.of(new Factory(
                    rs.getString("Factory_ID"),
                    rs.getString("name"),
                    rs.getString("contact_number"),
                    rs.getString("address")
            ));
        }
        return Optional.empty();
    }
}
