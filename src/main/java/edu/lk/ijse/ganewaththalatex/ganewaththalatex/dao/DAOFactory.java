package edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.custom.impl.*;

public class DAOFactory {
    private  static DAOFactory daoFactory;

    private  DAOFactory() {}

    public static DAOFactory getInstance() {return daoFactory == null? (daoFactory = new DAOFactory()) : daoFactory;}

    @SuppressWarnings("unchecked")
    public <T extends SuperDAO> T getDAO(DAOTypes daoTypes)  {
        return switch (daoTypes){
            case ATTENDANCE -> (T) new AttendanceDAOimpl();
            case VEHICLE -> (T) new VehicleDAOimpl();
            case ORDER_DETAILS -> (T) new OrderDetailsDAOimpl();
            case EMPLOYEE -> (T) new EmployeeDAOImpl();
            case FACTORY -> (T) new FactoryDAOImpl();
            case INVENTORY -> (T) new InventoryDAOimpl();
            case ORDER -> (T) new OrderDAOimpl();
            case PAYMENT -> (T) new PaymentDAOimpl();
            case SUPPLIER -> (T) new SupplierDAOImpl();
            case SALARY -> (T) new SalaryDAOimpl();

        };
    }
}
