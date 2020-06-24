package com.SevenNine.Essentials.Bean;

public class Orderbean {
    String id;
    String sell_listname;
    String sell_cropname;
    String uom;
    String price;
    String quantity;
    String image;
    String variety;
    String quality;



    String ord_msg;

    public Orderbean(String id, String sell_listname, String sell_cropname,String uom, String price, String quantity, String image, String variety, String ord_msg) {

        this.id = id;
        this.sell_listname = sell_listname;
        this.sell_cropname=sell_cropname;
        this.uom = uom;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.variety = variety;
        this.ord_msg = ord_msg;
    }
    public String getId() {
        return id;
    }

    public String getSell_listname() {
        return sell_listname;
    }

    public void setSell_listname(String sell_listname) {
        this.sell_listname = sell_listname;
    }

    public String getUom() {
        return uom;
    }
    public String getSell_cropname() {
        return sell_cropname;
    }

    public void setSell_cropname(String sell_cropname) {
        this.sell_cropname = sell_cropname;
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

    public String getOrd_msg() {
        return ord_msg;
    }

    public void setOrd_msg(String ord_msg) {
        this.ord_msg = ord_msg;
    }

}
