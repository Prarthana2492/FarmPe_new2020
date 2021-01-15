package com.SevenNine.essentials.Bean;

public class Inventorydetailsbean {
    String id;
    String sell_name;
    String uom;
    String price;
    String quantity;
    String image;
    String variety;
    String catgry_name;


    String quality;
    String min_price;
    String max_price;

    public Inventorydetailsbean(String id, String sell_name,String uom, String price, String quantity, String image, String variety, String catgry_name,String quality,String min_price,String max_price) {

        this.id = id;
        this.sell_name = sell_name;
        this.uom = uom;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.variety = variety;
        this.catgry_name = catgry_name;
        this.quality = quality;
        this.min_price = min_price;
        this.max_price = max_price;
    }
    public String getId() {
        return id;
    }

    public String getSell_name() {
        return sell_name;
    }

    public void setSell_name(String sell_name) {
        this.sell_name = sell_name;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }


    public String getVariety() { return variety; }

    public void setVariety(String variety) { this.variety = variety; }
    public String getCatgry_name() {
        return catgry_name;
    }

    public void setCatgry_name(String catgry_name) {
        this.catgry_name = catgry_name;
    }
    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getMin_price() {
        return min_price;
    }

    public void setMin_price(String min_price) {
        this.min_price = min_price;
    }

    public String getMax_price() {
        return max_price;
    }

    public void setMax_price(String max_price) {
        this.max_price = max_price;
    }



}
