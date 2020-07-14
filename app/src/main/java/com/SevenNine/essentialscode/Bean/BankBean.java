package com.SevenNine.essentialscode.Bean;

public class BankBean {

    String bankname,name,phonenumber,ifsccode,area,city,id,state;
    String isIFSCCodeExist,stateId,districtId;

    public BankBean(String bankname,String name, String phonenumber,String ifsccode, String area, String city,String state,String id,String isIFSCCodeExist,String stateId,String districtId) {

        this.bankname = bankname;
        this.name = name;
        this.phonenumber = phonenumber;
        this.ifsccode = ifsccode;
        this.area = area;
        this.city = city;
        this.state = state;
        this.id = id;
        this.isIFSCCodeExist = isIFSCCodeExist;
        this.stateId = stateId;
        this.districtId = districtId;
    }

    public String getBankname() {
        return bankname;
    }

    public String getName() {
        return name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getIfsccode() {
        return ifsccode;
    }

    public String getArea() {
        return area;
    }

    public String getCity() {
        return city;
    }

    public String getId() {
        return id;
    }

    public String isIFSCCodeExist() {
        return isIFSCCodeExist;
    }

    public String getState() {
        return state;
    }

    public String getStateId() {
        return stateId;
    }

    public String getDistrictId() {
        return districtId;
    }
}