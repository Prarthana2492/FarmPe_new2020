package com.SevenNine.essentials.Bean;

public class InventoryBean {

    String prod_name,prod_desc,brand,quantity,amount,mrp,prod_icon,selling_icon,selling_cat_id,productlistId,selling_master_id,offer_price,delivery_charge,exp_date,IsOfferActive,SellingTypeId;




    public InventoryBean(String prod_name, String prod_desc, String brand, String quantity, String amount, String mrp, String prod_icon, String selling_icon, String selling_cat_id, String productlistId, String selling_master_id, String offer_price, String delivery_charge, String exp_date, String IsOfferActive, String SellingTypeId) {
        this.prod_name = prod_name;
        this.prod_desc = prod_desc;
        this.brand=brand;
        this.quantity=quantity;
        this.amount=amount;
        this.mrp=mrp;
        this.prod_icon=prod_icon;
        this.selling_icon=selling_icon;
        this.selling_cat_id=selling_cat_id;
        this.productlistId=productlistId;
        this.offer_price=offer_price;
        this.exp_date=exp_date;
        this.delivery_charge=delivery_charge;
        this.selling_master_id=selling_master_id;
        this.IsOfferActive=IsOfferActive;
        this.SellingTypeId=SellingTypeId;

    }

    public String getProd_name() {
        return prod_name;
    }

    public String getProd_desc() {
        return prod_desc;
    }

    public String getBrand() {
        return brand;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getAmount() {
        return amount;
    }

    public String getMrp() {
        return mrp;
    }

    public String getProd_icon() {
        return prod_icon;
    }

    public String getSelling_icon() {
        return selling_icon;
    }

    public String getSelling_cat_id() {
        return selling_cat_id;
    }

    public String getProductlistId() {
        return productlistId;
    }

    public String getSelling_master_id() {
        return selling_master_id;
    }

    public String getDelivery_charge() {
        return delivery_charge;
    }

    public String getOffer_price() {
        return offer_price;
    }

    public String getExp_date() {
        return exp_date;
    }

    public String getIsOfferActive() {
        return IsOfferActive;
    }

    public String getSellingTypeId() {
        return SellingTypeId;
    }
}
