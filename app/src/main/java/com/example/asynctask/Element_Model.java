package com.example.asynctask;

public class Element_Model {
    String name, description, price;
    public Element_Model(String name, String price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }
}
