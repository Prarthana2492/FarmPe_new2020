package com.SevenNine.Essentials.Bean;

public class MainAdapterBean1 {

    String name,id;
    int image;



    public MainAdapterBean1(String name, String id, int image) {
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
