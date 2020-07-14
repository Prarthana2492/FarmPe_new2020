package com.SevenNine.essentialscode.Bean;

public class Sellbean1 {

    String name,id,weight,price,actual_price,uom;
    int image;


    public Sellbean1(String name, String id, int image, String weight, String price, String actual_price,String uom) {
        this.name = name;
        this.id = id;
        this.weight = weight;
        this.price = price;
        this.actual_price = actual_price;
        this.image=image;
        this.uom=uom;

    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getWeight() {
        return weight;
    }

    public String getPrice() {
        return price;
    }

    public String getActual_price() {
        return actual_price;
    }

    public int getImage() {
        return image;
    }

    public String getUom() {
        return uom;
    }
}
