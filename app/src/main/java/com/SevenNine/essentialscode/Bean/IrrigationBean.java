package com.SevenNine.essentialscode.Bean;

public class IrrigationBean {

    public String getType_image() {
        return type_image;
    }

    public void setType_image(String type_image) {
        this.type_image = type_image;
    }

    public String getMethod_image() {
        return method_image;
    }

    public void setMethod_image(String method_image) {
        this.method_image = method_image;
    }

    public String getCoverage_image() {
        return coverage_image;
    }

    public void setCoverage_image(String coverage_image) {
        this.coverage_image = coverage_image;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getMethod_name() {
        return method_name;
    }

    public void setMethod_name(String method_name) {
        this.method_name = method_name;
    }

    public String getMethod_id() {
        return method_id;
    }

    public void setMethod_id(String method_id) {
        this.method_id = method_id;
    }

    public String getCoverage_name() {
        return coverage_name;
    }

    public void setCoverage_name(String coverage_name) {
        this.coverage_name = coverage_name;
    }

    public String getCoverage_id() {
        return coverage_id;
    }

    public void setCoverage_id(String coverage_id) {
        this.coverage_id = coverage_id;
    }

    private String type_image,method_image,coverage_image;
    private String type_name,type_id,method_name,method_id,coverage_name,coverage_id;


    public IrrigationBean(String type_image,String type_name,String type_id, String method_image, String method_name,String method_id, String coverage_image,String coverage_name, String coverage_id) {

        this.type_image = type_image;
        this.type_name = type_name;
        this.type_id = type_id;
        this.method_image = method_image;
        this.method_name = method_name;
        this.method_id= method_id;
        this.coverage_image = coverage_image;
        this.coverage_name = coverage_name;
        this.coverage_id = coverage_id;


    }


}
