package edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom.impl.AttendanceBOImpl;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom.impl.EmployeeBOImpl;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom.impl.FactoryBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BOFactory();
            return boFactory;
        }
        return boFactory;
    }


     public <T extends SuperBO> T getBO(BOTypes type) {
        return switch (type){
            case ATTENDANCE -> (T) new AttendanceBOImpl();
            case FACTORY -> (T) new FactoryBOImpl();
            case EMPLOYEE -> (T) new EmployeeBOImpl();

        };
     }
}
