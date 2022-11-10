package com.gitlab.lhqezio.items;

public abstract class Product extends Item {
    private double price;
    private int quantity;
    private String description;
    private double discount;

    public Product(String name, String manufacturer, double price, double discount, int quantity, String description, String id) {
        super(name, manufacturer, description, id);
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.discount = discount;
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
    public double getDiscount() {
        return discount;
    };

    @Override
    public String toString() {
        return getName() + "\n" + getPrice() + "\n" + getDescription() + "\n";
    }

    public abstract String getCategory();

    public double getDiscountPercentage() {
        return (getDiscount() / getPrice()) * 100;
    }
    public int discountCompareTo(Product other) {
        return this.getDiscountPercentage() > other.getDiscountPercentage() ? 1 : -1;
    }
}
