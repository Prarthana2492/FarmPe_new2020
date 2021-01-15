package com.SevenNine.essentials.Bean;

public class Offers_static_bean {

    String name;
    String id;
    String weight;
    String rs;

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getRs() {
        return rs;
    }

    public void setRs(String rs) {
        this.rs = rs;
    }

    public String getMrp_discount() {
        return mrp_discount;
    }

    public void setMrp_discount(String mrp_discount) {
        this.mrp_discount = mrp_discount;
    }

    String mrp_discount;


    public int getImage1() {
        return image1;
    }

    public void setImage1(int image1) {
        this.image1 = image1;
    }

    int image1;

    public Offers_static_bean(String name, String id, String weight ,String rs,String mrp_discount,int image1) {
        this.name = name;
        this.id = id;
        this.weight = weight;
        this.rs= rs;
        this.mrp_discount = mrp_discount;
        this.image1 = image1;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
