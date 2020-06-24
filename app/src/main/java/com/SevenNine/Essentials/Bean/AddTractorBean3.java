package com.SevenNine.Essentials.Bean;

public class AddTractorBean3 {

   private int image;
    private String prod_name,id;


    public AddTractorBean3(int image, String prod_name, String id) {

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

    public int getImage() {
        return image;
    }


}
