package com.SevenNine.essentials.Bean;

public class IrrigationTypeBean {

    String name,id,image;

    public boolean isselected() {
        return isselected;
    }

    public void setIsselected(boolean isselected) {
        this.isselected = isselected;
    }

    private boolean isselected;

    public IrrigationTypeBean(String name, String id, String image, boolean isselected) {
        this.name = name;
        this.id = id;
        this.image=image;
        this.isselected=isselected;
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
