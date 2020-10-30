package com.company.Model;

public class VehicleInfo {
    public String carId, carManufactory, carModel;
    public Boolean isAvaialble;

    public VehicleInfo(String carId, String carManufactory, String carModel, Boolean isAvaialble) {
        this.carId = carId;
        this.carManufactory = carManufactory;
        this.carModel = carModel;
        this.isAvaialble = isAvaialble;
    }
}
