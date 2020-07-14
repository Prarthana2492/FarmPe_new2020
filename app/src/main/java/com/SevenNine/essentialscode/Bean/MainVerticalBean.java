package com.SevenNine.essentialscode.Bean;

public class MainVerticalBean {

    String name,id,image;




    public MainVerticalBean(String name, String id, String image) {
        this.name = name;
        this.id = id;
        this.image=image;

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
