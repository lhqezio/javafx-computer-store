package com.gitlab.lhqezio.items;

public class Computer extends Product {
    private String processor;
    private String ram;
    private String hardDrive;
    private String graphicsCard;
    private String operatingSystem;
    private int capacity;

    public Computer(String name, String manufacturer, double price, int quantity, String description, String id, String processor, String ram, String hardDrive, String graphicsCard, String operatingSystem, int capacity) {
        super(name, manufacturer, price, quantity, description, id);
        this.processor = processor;
        this.ram = ram;
        this.hardDrive = hardDrive;
        this.graphicsCard = graphicsCard;
        this.operatingSystem = operatingSystem;
        this.capacity = capacity;
    }

    public String getCategory() {
        return "Computer";
    };
}
