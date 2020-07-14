package com.SevenNine.essentialscode.Bean;

public class ListBean2 {

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



    public ListBean2(String name, int id, int image, int loanid) {
        this.name=name;
        this.id = id;
        this.image=image;
        this.loanid = loanid;

    }
}


