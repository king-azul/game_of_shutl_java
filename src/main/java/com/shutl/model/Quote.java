package com.shutl.model;

public class Quote {

    String pickupPostcode;

    public Quote() {

    }

    public Quote(String postCode) {
        this.pickupPostcode = postCode;
    }

    public String getPickupPostcode() {
        return pickupPostcode;
    }

    public void setPickupPostcode(String pickupPostcode) {
        this.pickupPostcode = pickupPostcode;
    }

}
