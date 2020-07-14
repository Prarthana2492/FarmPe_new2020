package com.SevenNine.essentialscode.Bean;

public class NewOrderBean {

    String prod_name,CreatedOn,prod_img,TxnId,Amount,Quantity,Uom,productInfo,mode,firstname;

    public NewOrderBean(String prod_name, String CreatedOn, String prod_img, String TxnId, String Amount, String Quantity,String Uom,String productInfo,String mode,String firstname) {

        this.prod_name = prod_name;
        this.CreatedOn = CreatedOn;
        this.prod_img = prod_img;
        this.TxnId = TxnId;
        this.Amount = Amount;
        this.Quantity = Quantity;
        this.Uom = Uom;
        this.productInfo = productInfo;
        this.mode = mode;
        this.firstname = firstname;
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

    public String getUom() {
        return Uom;
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
}
