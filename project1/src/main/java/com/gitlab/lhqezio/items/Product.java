package com.gitlab.lhqezio.items;

public abstract class Product extends Item {
    private double price;
    private int quantity;
    private String description;

    public Product(String name, String manufacturer, double price, int quantity, String description, String id) {
        super(name, manufacturer, description, id);
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    public double getPrice() {
        return price;
    };

    public int getQuantity() {
        return quantity;
    };

    public String getDescription() {
        return description;
    };

    @Override
    public String toString() {
        return getName() + "\n" + getPrice() + "\n" + getDescription() + "\n";
    }

    public abstract String getCategory();
}
