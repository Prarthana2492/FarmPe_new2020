package com.SevenNine.Essentials.Bean;

public class StoreListBean {

    private String area;
    private String gst_no,pincode,city;
    private String image;

    public StoreListBean(String area, String gst_no, String pincode, String city, String image)
                    {

        this.area = area;
        this.gst_no = gst_no;
        this.pincode = pincode;
        this.city = city;
        this.image = image;

    }

    public String getArea() {
        return area;
    }

    public String getGst_no() {
        return gst_no;
    }

    public String getPincode() {
        return pincode;
    }

    public String getCity() {
        return city;
    }

    public String getImage() {
        return image;
    }
}
