package com.SevenNine.essentials.Bean;

public class Sellbean {

    String name,id,weight,price,actual_price,uom,prod_descr,upid,prodId,selling_cat_name,brand,offerPrice;
    String image;


    public Sellbean(String name, String id,String image,String weight,String price,String actual_price,String uom,String prod_descr,String upid,String prodId,String selling_cat_name,String brand,String offerPrice) {
        this.name = name;
        this.id = id;
        this.weight = weight;
        this.price = price;
        this.actual_price = actual_price;
        this.image=image;
        this.uom=uom;
        this.prod_descr=prod_descr;
        this.prodId=prodId;
        this.upid=upid;
        this.selling_cat_name=selling_cat_name;
        this.brand=brand;
        this.offerPrice=offerPrice;

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

    public String getImage() {
        return image;
    }

    public String getUom() {
        return uom;
    }

    public String getProd_descr() {
        return prod_descr;
    }

    public String getUpid() {
        return upid;
    }

    public String getProdId() {
        return prodId;
    }

    public String getSelling_cat_name() {
        return selling_cat_name;
    }

    public String getBrand() {
        return brand;
    }

    public String getOfferPrice() {
        return offerPrice;
    }
}
