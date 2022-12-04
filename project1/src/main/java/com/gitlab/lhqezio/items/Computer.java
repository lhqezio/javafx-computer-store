package com.gitlab.lhqezio.items;

public class Computer extends Product {
    private String processor;
    private String ram;
    private String hardDrive;
    private int capacity;
    private String graphicsCard;
    private String operatingSystem;

    public Computer(int id_, String name_, String manufacturer_, String description_, double price_, double discount_, int quantity_, String processor_, String ram_, String hardDrive_, int capacity_, String graphicsCard_, String operatingSystem_) {
        super(id_, name_, manufacturer_, description_, price_, discount_, quantity_);
        this.processor = processor_;
        this.ram = ram_;
        this.hardDrive = hardDrive_;
        this.capacity = capacity_;
        this.graphicsCard = graphicsCard_;
        this.operatingSystem = operatingSystem_;
    }

    public String getProcessor() {
        return this.processor;
    }
    public String getRam() {
        return this.ram;
    }
    public String getHardDrive() {
        return this.hardDrive;
    }
    public int getCapacity() {
        return this.capacity;
    }
    public String getGraphicsCard() {
        return this.graphicsCard;
    }
    public String getOperatingSystem() {
        return this.operatingSystem;
    }

    public String getCategory() {
        return "Computer";
    }

    @Override
    public Computer createCopy() {
        return new Computer(this.getId(), this.getName(), this.getManufacturer(), this.getDescription(), this.getPrice(), this.getDiscount(), this.getQuantity(), this.processor, this.ram, this.hardDrive, this.capacity, this.graphicsCard, this.operatingSystem);
    }

    @Override
    public Computer productConstructor(int id_, String name_, String manufacturer_, String description_, double price_, double discount_, int quantity_) {
        return new Computer(id_, name_, manufacturer_, description_, price_, discount_, quantity_,this.processor, this.ram, this.hardDrive, this.capacity, this.graphicsCard, this.operatingSystem);
    }
}
