package com.SevenNine.essentials.Bean;

public class FarmersNearMeCropBean {

    String veg_name,veriety,location,grade,quantity,uom,price,id;
    String add_name;
    String cat_name;
    int cat_icon;

    String addressId;
    String sub_cat_name;
    String sub_cat_img;
    String subcat_name;
    String subcat_icon;

    String add_houseno;
    String add_street;
    String add_landmark;



    String add_city;
    String add_pincode;
    String add_mobile;
    String add_pickfrom;


    String add_state;
    String add_district;
    String add_taluk;
    String add_hobli;
    String add_village;

    String crop_image;

    public String getFrm_date() {
        return frm_date;
    }

    public void setFrm_date(String frm_date) {
        this.frm_date = frm_date;
    }

    String frm_date;

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }

    String to_date;




    public String getAdd_city() {
        return add_city;
    }

    public void setAdd_city(String add_city) {
        this.add_city = add_city;
    }


    public String getAdd_village() {
        return add_village;
    }


    public void setAdd_village(String add_village) {
        this.add_village = add_village;
    }

    public String getAdd_houseno() {
        return add_houseno;
    }

    public void setAdd_houseno(String add_houseno) {
        this.add_houseno = add_houseno;
    }

    public String getAdd_pickfrom() {
        return add_pickfrom;
    }

    public void setAdd_pickfrom(String add_pickfrom) {
        this.add_pickfrom = add_pickfrom;
    }

    public String getAdd_state() {
        return add_state;
    }

    public void setAdd_state(String add_state) {
        this.add_state = add_state;
    }

    public String getAdd_district() {
        return add_district;
    }

    public void setAdd_district(String add_district) {
        this.add_district = add_district;
    }

    public String getAdd_taluk() {
        return add_taluk;
    }

    public void setAdd_taluk(String add_taluk) {
        this.add_taluk = add_taluk;
    }

    public String getAdd_hobli() {
        return add_hobli;
    }

    public void setAdd_hobli(String add_hobli) {
        this.add_hobli = add_hobli;
    }



    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }


    public String getSub_cat_name() {
        return sub_cat_name;
    }

    public void setSub_cat_name(String sub_cat_name) {
        this.sub_cat_name = sub_cat_name;
    }

    public String getSub_cat_img() {
        return sub_cat_img;
    }

    public void setSub_cat_img(String sub_cat_img) {
        this.sub_cat_img = sub_cat_img;
    }



    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }


    public String getSubcat_name() {
        return subcat_name;
    }

    public void setSubcat_name(String subcat_name) {
        this.subcat_name = subcat_name;
    }

    public String getSubcat_icon() {
        return subcat_icon;
    }

    public void setSubcat_icon(String subcat_icon) {
        this.subcat_icon = subcat_icon;
    }



    public String getAdd_name() {
        return add_name;
    }

    public void setAdd_name(String add_name) {
        this.add_name = add_name;
    }

    public String getAdd_street() {
        return add_street;
    }

    public void setAdd_street(String add_street) {
        this.add_street = add_street;
    }

    public String getAdd_landmark() {
        return add_landmark;
    }

    public void setAdd_landmark(String add_landmark) {
        this.add_landmark = add_landmark;
    }

    public String getAdd_pincode() {
        return add_pincode;
    }

    public void setAdd_pincode(String add_pincode) {
        this.add_pincode = add_pincode;
    }

    public String getAdd_mobile() {
        return add_mobile;
    }

    public void setAdd_mobile(String add_mobile) {
        this.add_mobile = add_mobile;
    }

    public int getCat_icon() {
        return cat_icon;
    }

    public void setCat_icon(int cat_icon) {
        this.cat_icon = cat_icon;
    }

    public String getVeg_name() {
        return veg_name;
    }

    public String getVeriety() {
        return veriety;
    }

    public String getLocation() {
        return location;
    }

    public String getGrade() {
        return grade;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getUom() {
        return uom;
    }

    public String getPrice() {
        return price;
    }

    public String getId() {
        return id;
    }

    public FarmersNearMeCropBean(String veg_name, String veriety, String location, String grade, String quantity, String uom, String price, String id
            , String cat_name, int cat_icon
            , String sub_cat_name, String sub_cat_img, String addressId , String add_name,  String add_street, String add_landmark, String add_city, String add_pincode, String add_mobile, String add_pickfrom,
                                 String add_state, String add_district, String add_taluk, String add_hobli, String add_village, String frm_date, String to_date

    ) {

        this.veg_name = veg_name;
        this.veriety = veriety;
        this.location = location;
        this.grade = grade;
        this.quantity = quantity;
        this.uom = uom;
        this.price = price;
        this.id = id;
        this.cat_name = cat_name;
        this.cat_icon = cat_icon;
        this.sub_cat_name = sub_cat_name;
        this.sub_cat_img = sub_cat_img;
        this.addressId = addressId;
        this.add_name = add_name;

        this.add_street = add_street;
        this.add_landmark = add_landmark;
        this.add_city = add_city;
        this.add_pincode = add_pincode;
        this.add_mobile = add_mobile;
        this.add_pickfrom = add_pickfrom;
        this.add_state= add_state;
        this.add_district = add_district;
        this.add_taluk = add_taluk;
        this.add_hobli = add_hobli;
        this.add_village = add_village;
        this.frm_date = frm_date;
        this.to_date = to_date;





    }
}


