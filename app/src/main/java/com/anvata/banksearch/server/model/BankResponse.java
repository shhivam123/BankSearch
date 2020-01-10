package com.anvata.banksearch.server.model;

import com.google.gson.annotations.SerializedName;

public class BankResponse {

    @SerializedName("ifsc")
    private String ifsc;
    @SerializedName("bank_id")
    private int bankId;
    @SerializedName("branch")
    private String branch;
    @SerializedName("address")
    private String address;
    @SerializedName("city")
    private String city;
    @SerializedName("district")
    private String district;
    @SerializedName("state")
    private String state;
    @SerializedName("bank_name")
    private String bank_name;

    public String getIfsc() {
        return ifsc;
    }

    public int getBankId() {
        return bankId;
    }

    public String getBranch() {
        return branch;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public String getState() {
        return state;
    }

    public String getBank_name() {
        return bank_name;
    }
}
