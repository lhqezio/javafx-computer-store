package com.gitlab.lhqezio.items;

public class Computer extends Product {
    private String processor;
    private String ram;
    private String hardDrive;
    private String graphicsCard;
    private String operatingSystem;
    private int capacity;

    public Computer(String name_, String manufacturer_, double price_, double discount_, int quantity_, String description_, String id_, String processor_, String ram_, String hardDrive_, int capacity_, String graphicsCard_, String operatingSystem_) {
        super(name_, manufacturer_, price_, discount_, quantity_, description_, id_);
        this.processor = processor_;
        this.ram = ram_;
        this.hardDrive = hardDrive_;
        this.capacity = capacity_;
        this.graphicsCard = graphicsCard_;
        this.operatingSystem = operatingSystem_;
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
