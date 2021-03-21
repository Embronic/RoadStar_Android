package com.road.star.models.otp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OTPData {

    @SerializedName("country_phone_code")
    @Expose
    private String country_phone_code;

    @SerializedName("mobile")
    @Expose
    private String mobile;

    @SerializedName("otp")
    @Expose
    private String otp;

    @SerializedName("country_id")
    @Expose
    private String country_id;


    public String getCountry_phone_code() {
        return country_phone_code;
    }

    public void setCountry_phone_code(String country_phone_code) {
        this.country_phone_code = country_phone_code;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    @Override
    public String toString() {
        return "ClassPojo [country_phone_code = " + country_phone_code + ", mobile = " + mobile + ", otp = " + otp + "]";
    }

}
