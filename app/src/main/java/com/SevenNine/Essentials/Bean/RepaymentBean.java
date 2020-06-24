package com.SevenNine.Essentials.Bean;

public class RepaymentBean {

    String name,bank_name,acc_no,ifsc_code,emi,image;

    public RepaymentBean(String name, String bank_name, String acc_no, String ifsc_code, String emi, String image) {

        this.name = name;
        this.bank_name = bank_name;
        this.acc_no = acc_no;
        this.ifsc_code = ifsc_code;
        this.emi = emi;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getBank_name() {
        return bank_name;
    }

    public String getAcc_no() {
        return acc_no;
    }

    public String getIfsc_code() {
        return ifsc_code;
    }

    public String getEmi() {
        return emi;
    }

    public String getImage() {
        return image;
    }
}


