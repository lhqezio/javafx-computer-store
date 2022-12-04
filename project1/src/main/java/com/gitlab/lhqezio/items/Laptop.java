package com.gitlab.lhqezio.items;

public class Laptop extends Computer {
    private int batteryLife;

    public Laptop(String name_, String manufacturer_, double price_, double discount_, int quantity_, String description_, String id_, String processor_, String ram_, String hardDrive_, int capacity_, String graphicsCard_, String operatingSystem_, int batteryLife_) {
        super(name_, manufacturer_, price_, discount_, quantity_, description_, id_, processor_, ram_, hardDrive_, capacity_, graphicsCard_, operatingSystem_);
        this.batteryLife = batteryLife_;
    }

    public int getBatteryLife() {
        return batteryLife;
    }

    ;

    @Override
    public String getCategory() {
        return "Laptop";
    }

    ;
}
