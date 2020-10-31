package com.company.Model;

public class VehicleInfo {
    public String carManufactory, carModel;
    public Integer carId, carPrice;
    public Boolean isAvaialble;

    public VehicleInfo(Integer carId, String carManufactory, String carModel, Integer carPrice, Boolean isAvaialble) {
        this.carId = carId;
        this.carManufactory = carManufactory;
        this.carModel = carModel;
        this.carPrice = carPrice;
        this.isAvaialble = isAvaialble;
    }

    public VehicleInfo(String carManufactory, String carModel, Integer carPrice, Boolean isAvaialble) {
        this.carManufactory = carManufactory;
        this.carModel = carModel;
        this.carPrice = carPrice;
        this.isAvaialble = isAvaialble;
    }

    public Integer getCarId() { return carId; }
    public String getCarManufactory() { return carManufactory; }
    public String getCarModel() { return carModel; }
    public Integer getCarPrice() { return carPrice; }
    public Boolean getIsAvaialble() { return isAvaialble; }
    public void setCarId(Integer carId) { this.carId = carId; }
    public void setCarManufactory(String carManufactory) { this.carManufactory = carManufactory; }
    public void setCarModel(String carModel) { this.carModel = carModel; }
    public void setCarPrice(Integer carPrice) { this.carPrice = carPrice; }
    public void setIsAvaialble(Boolean isAvaialble) { this.isAvaialble = isAvaialble; }
}
