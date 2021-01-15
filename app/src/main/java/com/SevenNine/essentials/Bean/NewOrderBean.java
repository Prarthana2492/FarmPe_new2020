package com.SevenNine.essentials.Bean;

public class NewOrderBean {

    String prod_name,CreatedOn,prod_img,TxnId,Amount,Quantity,mrp,productInfo,mode,firstname,offer_price,delivery_charges;

    public NewOrderBean(String prod_name, String CreatedOn, String prod_img, String TxnId, String Amount, String Quantity,String mrp,String productInfo,String mode,String firstname,String offer_price,String delivery_charges) {

        this.prod_name = prod_name;
        this.CreatedOn = CreatedOn;
        this.prod_img = prod_img;
        this.TxnId = TxnId;
        this.Amount = Amount;
        this.Quantity = Quantity;
        this.mrp = mrp;
        this.productInfo = productInfo;
        this.mode = mode;
        this.firstname = firstname;
        this.offer_price = offer_price;
        this.delivery_charges = delivery_charges;
    }

    public String getProd_name() {
        return prod_name;
    }

    public String getCreatedOn() {
        return CreatedOn;
    }

    public String getProd_img() {
        return prod_img;
    }

    public String getTxnId() {
        return TxnId;
    }

    public String getAmount() {
        return Amount;
    }

    public String getQuantity() {
        return Quantity;
    }

    public String getProductInfo() {
        return productInfo;
    }

    public String getMode() {
        return mode;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMrp() {
        return mrp;
    }

    public String getOffer_price() {
        return offer_price;
    }

    public String getDelivery_charges() {
        return delivery_charges;
    }
}
