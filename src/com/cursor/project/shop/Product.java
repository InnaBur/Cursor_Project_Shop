package com.cursor.project.shop;

public class Product {
    String name;
    String price;

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price + " UAN" +
                '}';
    }


}
