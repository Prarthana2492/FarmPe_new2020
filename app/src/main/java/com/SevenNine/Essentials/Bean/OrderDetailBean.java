package com.SevenNine.Essentials.Bean;

public class OrderDetailBean {

    String prod_name,quantity,amount,shipping_fee,shippng_iscount,prod_img,cart_prodlistid,prod_desc,ProductId,Brand,MRP;

    public OrderDetailBean(String prod_name, String quantity,String amount,String shipping_fee,String shippng_iscount, String prod_img,String cart_prodlistid,String prod_desc,String ProductId, String Brand,String MRP ) {

        this.prod_name = prod_name;
        this.quantity = quantity;
        this.prod_img = prod_img;
        this.shippng_iscount = shippng_iscount;
        this.shipping_fee = shipping_fee;
        this.amount = amount;
        this.prod_img = prod_img;
        this.prod_desc = prod_desc;
        this.cart_prodlistid = cart_prodlistid;
        this.ProductId = ProductId;
        this.Brand = Brand;
        this.MRP = MRP;
    }

    public String getProd_name() {
        return prod_name;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getAmount() {
        return amount;
    }

    public String getShipping_fee() {
        return shipping_fee;
    }

    public String getShippng_iscount() {
        return shippng_iscount;
    }

    public String getProd_img() {
        return prod_img;
    }

    public String getCart_prodlistid() {
        return cart_prodlistid;
    }

    public String getProd_desc() {
        return prod_desc;
    }

    public String getProductId() {
        return ProductId;
    }

    public String getBrand() {
        return Brand;
    }

    public String getMRP() {
        return MRP;
    }
}
