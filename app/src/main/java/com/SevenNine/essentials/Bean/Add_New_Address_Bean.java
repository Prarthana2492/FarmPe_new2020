package com.SevenNine.essentials.Bean;

public class Add_New_Address_Bean {

    String add_id;
    String add_pickup_frm;
    String add_name;
    String add_street;
    String add_pincode;
    String add_landmark;
    String add_city;
    String add_mobile;
    String add_state;
    String add_district;
    String add_taluk;
    String add_hobli;
    String add_village,stateId,districtId,blockId,villageId;
    Boolean default_addr;


    public Add_New_Address_Bean(String add_name, String add_street, String add_landmark, String add_city, String add_pincode, String add_mobile, String add_pickup_frm, String add_state, String add_district, String add_taluk,
                                String add_hobli, String add_village, String add_id, boolean default_addr, String stateId, String districtId, String blockId, String villageId) {

        this.add_name = add_name;
        this.add_street = add_street;
        this.add_landmark = add_landmark;
        this.add_city = add_city;
        this.add_pincode = add_pincode;
        this.add_mobile = add_mobile;
        this.add_pickup_frm = add_pickup_frm;
        this.add_state = add_state;
        this.add_district = add_district;
        this.add_taluk = add_taluk;
        this.add_hobli = add_hobli;
        this.add_village = add_village;
        this.add_id = add_id;
        this.default_addr = default_addr;
        this.stateId = stateId;
        this.districtId = districtId;
        this.blockId = blockId;
        this.villageId = villageId;
    }

    public String getAdd_id() {
        return add_id;
    }

    public String getAdd_pickup_frm() {
        return add_pickup_frm;
    }

    public String getAdd_name() {
        return add_name;
    }

    public String getAdd_street() {
        return add_street;
    }

    public String getAdd_pincode() {
        return add_pincode;
    }

    public String getAdd_landmark() {
        return add_landmark;
    }

    public String getAdd_city() {
        return add_city;
    }

    public String getAdd_mobile() {
        return add_mobile;
    }

    public String getAdd_state() {
        return add_state;
    }

    public String getAdd_district() {
        return add_district;
    }

    public String getAdd_taluk() {
        return add_taluk;
    }

    public String getAdd_hobli() {
        return add_hobli;
    }

    public String getAdd_village() {
        return add_village;
    }

    public String getStateId() {
        return stateId;
    }

    public String getDistrictId() {
        return districtId;
    }

    public String getBlockId() {
        return blockId;
    }

    public String getVillageId() {
        return villageId;
    }

    public Boolean getDefault_addr() {
        return default_addr;
    }
}


