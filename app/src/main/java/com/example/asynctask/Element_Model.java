package com.example.asynctask;

public class Element_Model {
    String name, description;
    Integer price;
    public Element_Model(String name, Integer price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
