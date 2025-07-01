package edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.tm;

public class VehicleTM {
    private String VehicleID;
    private String VehicleName;

    public VehicleTM(String vehicleID, String vehicleName) {
        VehicleID = vehicleID;
        VehicleName = vehicleName;
    }

    public String getVehicleID() {
        return VehicleID;
    }

    public void setVehicleID(String vehicleID) {
        VehicleID = vehicleID;
    }

    public String getVehicleName() {
        return VehicleName;
    }

    public void setVehicleName(String vehicleName) {
        VehicleName = vehicleName;
    }
}
