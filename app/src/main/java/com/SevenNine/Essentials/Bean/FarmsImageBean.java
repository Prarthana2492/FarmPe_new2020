package com.SevenNine.Essentials.Bean;

public class FarmsImageBean {

  //  private String image;

    public String getFarmer_name() {
        return farmer_name;
    }

    public void setFarmer_name(String farmer_name) {
        this.farmer_name = farmer_name;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String farmer_name;

    private String id;
    private String state;
    private String district;
    private  String image;



    public FarmsImageBean (String farmer_name,String id,String state,String district,String image) {

        this.farmer_name = farmer_name;
        this.id = id;
        this.state = state;
        this.district = district;
        this.image = image;

    }


}
