package edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom.impl;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom.FactoryBO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.util.FactoryEntityDTOConverter;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.DAOFactory;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.DAOTypes;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.custom.FactoryDAO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.FactoryDto;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.entity.Factory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FactoryBOImpl implements FactoryBO {


    private final FactoryDAO factoryDAO = (FactoryDAO) DAOFactory.getInstance().getDAO(DAOTypes.FACTORY);
    private final FactoryEntityDTOConverter converter = new FactoryEntityDTOConverter();

    @Override
    public List<FactoryDto> getAllFactories() throws SQLException, ClassNotFoundException {
        List<Factory> factories = factoryDAO.getAll();
        ArrayList<FactoryDto> dtos = new ArrayList<>();
        for (Factory factory : factories) {
            dtos.add(converter.getFactoryDto(factory));
        }
        return dtos;
    }

    @Override
    public boolean saveFactory(FactoryDto dto) throws SQLException, ClassNotFoundException {
        Factory factoryEntity = converter.getFactoryEntity(dto);
        return factoryDAO.save(factoryEntity);
    }

    @Override
    public boolean updateFactory(FactoryDto dto) throws SQLException, ClassNotFoundException {
        Factory factoryEntity = converter.getFactoryEntity(dto);
        return factoryDAO.update(factoryEntity);
    }

    @Override
    public boolean deleteFactory(String id) throws SQLException, ClassNotFoundException {
        return factoryDAO.delete(id);
    }

    @Override
    public FactoryDto findFactoryById(String id) throws SQLException, ClassNotFoundException {
        Optional<Factory> factoryOptional = factoryDAO.findbyId(id);
        return factoryOptional.map(converter::getFactoryDto).orElse(null);
    }

    @Override
    public String getNextFactoryId() throws SQLException, ClassNotFoundException {
        return factoryDAO.getNextId();
    }

}
