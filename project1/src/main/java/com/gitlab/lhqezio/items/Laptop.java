package com.gitlab.lhqezio.items;

public class Laptop extends Computer {
    private int batteryLife;

    public Laptop(String name, String manufacturer, double price, int quantity, String description, String id, String processor, String ram, String hardDrive, String graphicsCard, String operatingSystem, int capacity, int batteryLife) {
        super(name, manufacturer, price, quantity, description, id, processor, ram, hardDrive, graphicsCard, operatingSystem, capacity);
        this.batteryLife = batteryLife;
    }

    public int getBatteryLife() {
        return batteryLife;
    };

    @Override
    public String getCategory() {
        return "Laptop";
    };
}
