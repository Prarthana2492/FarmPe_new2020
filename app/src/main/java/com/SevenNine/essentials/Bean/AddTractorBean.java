package com.SevenNine.essentials.Bean;

public class AddTractorBean {

   private String image;
    private String prod_name,id;


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    private  boolean isSelected;


    public AddTractorBean(String image, String prod_name, String id) {

        this.image = image;
        this.prod_name = prod_name;
        this.id = id;


    }


    public String getProd_name() {
        return prod_name;
    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }


}
