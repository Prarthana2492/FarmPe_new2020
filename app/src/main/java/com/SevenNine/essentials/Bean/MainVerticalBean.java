package com.SevenNine.essentials.Bean;

public class MainVerticalBean {

    String name,id,image;


    public int getImage1() {
        return image1;
    }

    public void setImage1(int image1) {
        this.image1 = image1;
    }

    int image1;

    public MainVerticalBean(String name, String id, String image,int image1) {
        this.name = name;
        this.id = id;
        this.image=image;
        this.image1 = image1;

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
