package com.SevenNine.Essentials.Bean;

public class TodayPaymentBean {

    String name,order_no,image,credited_to,date,mob_no;

    public TodayPaymentBean(String name, String order_no, String image,String credited_to,String date,String mob_no) {

        this.name = name;
        this.order_no = order_no;
        this.image = image;
        this.credited_to = credited_to;
        this.date = date;
        this.mob_no = mob_no;
    }

    public String getName() {
        return name;
    }

    public String getOrder_no() {
        return order_no;
    }

    public String getImage() {
        return image;
    }

    public String getCredited_to() {
        return credited_to;
    }

    public String getDate() {
        return date;
    }

    public String getMob_no() {
        return mob_no;
    }
}
