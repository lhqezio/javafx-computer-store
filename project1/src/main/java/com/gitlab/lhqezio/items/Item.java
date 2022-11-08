package com.gitlab.lhqezio.items;

public abstract class Item {
    private String name;
    private String manufacturer;
    private String id;
    private String description;

    public Item(String name, String manufacturer, String description, String id) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.id = id;
        this.description = description;
    }

    public String getName() {
        return name;
    };

    public String getManufacturer() {
        return manufacturer;
    };

    public String getID() {
        return id;
    };

    public String getDescription() {
        return description;
    };
}
