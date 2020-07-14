package com.SevenNine.essentialscode.Bean;

public class AddTractorBean1 {

    private String image;
    private String farm_type;

    public String getFarm_name() {
        return farm_name;
    }

    public void setFarm_name(String farm_name) {
        this.farm_name = farm_name;
    }

    private String farm_name;
    private String id;





    public String getFarm_type() {
        return farm_type;
    }

    public void setFarm_type(String farm_type) {
        this.farm_type = farm_type;
    }


    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }




    public AddTractorBean1(String image,String farm_name,String farm_type,String id) {

        this.image = image;
        this.farm_name = farm_name;
        this.farm_type = farm_type;
        this.id = id;

    }






}
