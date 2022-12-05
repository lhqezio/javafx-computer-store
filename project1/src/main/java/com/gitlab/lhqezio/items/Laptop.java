package com.gitlab.lhqezio.items;
/**
 * Laptop class.
 * @author Hoang
 */

public class Laptop extends Computer {
    private int batteryLife;

    public Laptop(int id_, String name_, String manufacturer_, String description_, double price_, double discount_, int quantity_, String processor_, String ram_, String hardDrive_, int capacity_, String graphicsCard_, String operatingSystem_, int batteryLife_) {
        super(id_, name_, manufacturer_, description_, price_, discount_, quantity_, processor_, ram_, hardDrive_, capacity_, graphicsCard_, operatingSystem_);
        this.batteryLife = batteryLife_;
    }

    public int getBatteryLife() {
        return batteryLife;
    }

    @Override
    public String getCategory() {
        return "Laptop";
    }

    @Override
    public Laptop createCopy() {
        return new Laptop(this.getId(), this.getName(), this.getManufacturer(), this.getDescription(), this.getPrice(), this.getDiscount(), this.getQuantity(), this.getProcessor(), this.getRam(), this.getHardDrive(), this.getCapacity(), this.getGraphicsCard(), this.getOperatingSystem(), this.batteryLife);
    }

    @Override
    public Laptop productConstructor(int id_, String name_, String manufacturer_, String description_, double price_, double discount_, int quantity_) {
        return new Laptop(id_, name_, manufacturer_, description_, price_, discount_, quantity_, this.getProcessor(), this.getRam(), this.getHardDrive(), this.getCapacity(), this.getGraphicsCard(), this.getOperatingSystem(), this.batteryLife);
    }
}
