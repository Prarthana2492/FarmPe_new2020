package com.SevenNine.Essentials.Bean;

public class BankOfferBean {
    String id,bank_name,loan_type,loan_amount,interest_rate,tenuree_rang,image;

    public BankOfferBean(String id, String bank_name, String loan_type, String loan_amount, String interest_rate, String tenuree_rang, String image) {

        this.id = id;
        this.bank_name = bank_name;
        this.loan_type = loan_type;
        this.loan_amount = loan_amount;
        this.interest_rate = interest_rate;
        this.tenuree_rang = tenuree_rang;
        this.image = image;

    }

    public String getId() {
        return id;
    }

    public String getBank_name() {
        return bank_name;
    }

    public String getLoan_type() {
        return loan_type;
    }

    public String getLoan_amount() {
        return loan_amount;
    }

    public String getInterest_rate() {
        return interest_rate;
    }

    public String getTenuree_rang() {
        return tenuree_rang;
    }

    public String getImage() {
        return image;
    }
}
