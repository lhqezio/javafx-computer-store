package com.gitlab.lhqezio.items;

public class Computer extends Product {
    private String processor;
    private String ram;
    private String hardDrive;
    private String graphicsCard;
    private String operatingSystem;
    private int capacity;

    public Computer(String name, String manufacturer, double price, double discount, int quantity, String description, String id, String processor, String ram, String hardDrive, String graphicsCard, String operatingSystem, int capacity) {
        super(name, manufacturer, price, discount, quantity, description, id);
        this.processor = processor;
        this.ram = ram;
        this.hardDrive = hardDrive;
        this.graphicsCard = graphicsCard;
        this.operatingSystem = operatingSystem;
        this.capacity = capacity;
    }

    public String getCategory() {
        return "Computer";
    }
    public String getGraphicsCard() {
        return graphicsCard;
    }
    public String getHardDrive() {
        return hardDrive;
    }
    public String getOperatingSystem() {
        return operatingSystem;
    }
    public String getProcessor() {
        return processor;
    }
    public String getRam() {
        return ram;
    }
    public int getCapacity() {
        return capacity;
    }
}
