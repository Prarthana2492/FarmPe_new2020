package com.SevenNine.essentials.Bean;

public class ListBean1 {

    String name;

    int id,image,loanid;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getImage() {
        return image;
    }

    public int getLoanid() {
        return loanid;
    }



    public ListBean1(String name, int id, int image, int loanid) {
        this.name=name;
        this.id = id;
        this.loanid = loanid;
        this.image=image;
    }
}


