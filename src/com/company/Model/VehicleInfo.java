package com.company.Model;

public class VehicleInfo {
    public String carId, carManufactory, carModel, carPrice;
    public Boolean isAvaialble;

    public VehicleInfo(String carId, String carManufactory, String carModel, String carPrice, Boolean isAvaialble) {
        this.carId = carId;
        this.carManufactory = carManufactory;
        this.carModel = carModel;
        this.carPrice = carPrice;
        this.isAvaialble = isAvaialble;
    }

    public String getCarId() { return carId; }
    public String getCarManufactory() { return carManufactory; }
    public String getCarModel() { return carModel; }
    public String getCarPrice() { return carPrice; }
    public Boolean getIsAvaialble() { return isAvaialble; }
    public void setCarId(String carId) { this.carId = carId; }
    public void setCarManufactory(String carManufactory) { this.carManufactory = carManufactory; }
    public void setCarModel(String carModel) { this.carModel = carModel; }
    public void setCarPrice(String carPrice) { this.carPrice = carPrice; }
    public void setIsAvaialble(Boolean isAvaialble) { this.isAvaialble = isAvaialble; }
}
