package com.gitlab.lhqezio.items;

public abstract class Product extends Item {
    private double price;
    private int quantity;
    private double discount;

    public Product(int id_, String name_, String manufacturer_, String description_, double price_, double discount_, int quantity_) {
        super(id_, name_, manufacturer_, description_);
        this.price = price_;
        this.discount = discount_;
        this.quantity = quantity_;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscount() {
        return discount;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return getManufacturer() + " " + getName() + "\n" + Math.round(getPrice()) + "$  NOW ONLY: " + Math.round(getPrice() - getDiscount()) + "$\n" + getDiscountPercentage() + "% OFF" + "\n" + getDescription() + "\n";
    }

    public abstract String getCategory();

    public double getDiscountPercentage() {
        return Math.ceil((getDiscount() / getPrice()) * 100);
    }

    public int discountCompareTo(Product other) {
        return this.getDiscountPercentage() < other.getDiscountPercentage() ? 1 : -1;
    }

    public Product createCopy() {
        return null;
    }

    public Product productConstructor(int id_, String name_, String manufacturer_, String description_, double price_, double discount_, int quantity_) {
        return null;
    }
}
