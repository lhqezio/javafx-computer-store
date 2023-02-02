package com.gitlab.lhqezio.items;
/**
 * Item abstract class.
 * @author Hoang
 */

public abstract class Item {
    private String name;
    private String manufacturer;
    private int id;
    private String description;

    public Item(int id_, String name_, String manufacturer_, String description_) {
        this.id = id_;
        this.name = name_;
        this.manufacturer = manufacturer_;
        this.description = description_;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getDescription() {
        return description;
    }

}
