package com.SevenNine.essentials.Bean;

public class Order_Bean {

    public Order_Bean(String name, String weight, String cost, String date, int prod_image) {
        this.name = name;
        this.weight = weight;
        this.cost = cost;
        this.date = date;
        this.prod_image = prod_image;
    }

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getProd_image() {
        return prod_image;
    }

    public void setProd_image(int prod_image) {
        this.prod_image = prod_image;
    }

    String weight;
    String cost;
    String date;
    int prod_image;
}
