package com.SevenNine.Essentials.Bean;


public class Sell_selected_bean {

    String name,id,image;
     boolean isSelected;


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }



    public Sell_selected_bean(String name, String id,String image,boolean isSelected) {
        this.name = name;
        this.id = id;
        this.image=image;
        this.isSelected=isSelected;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
