package com.SevenNine.essentials.Bean;

public class Cart_Bean
{

    String prod_name, cost,mrp, delivery_charges, save_amount,quantity;

    public String getProd_name() {
        return prod_name;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getDelivery_charges() {
        return delivery_charges;
    }

    public void setDelivery_charges(String delivery_charges) {
        this.delivery_charges = delivery_charges;
    }

    public String getSave_amount() {
        return save_amount;
    }

    public void setSave_amount(String save_amount) {
        this.save_amount = save_amount;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getProduct_image() {
        return product_image;
    }

    public void setProduct_image(int product_image) {
        this.product_image = product_image;
    }

    public Cart_Bean(String prod_name, String cost, String mrp, String delivery_charges, String save_amount, String quantity, int product_image) {
        this.prod_name = prod_name;
        this.cost = cost;
        this.mrp = mrp;
        this.delivery_charges = delivery_charges;
        this.save_amount = save_amount;
        this.quantity = quantity;
        this.product_image = product_image;
    }

    int product_image;
}
