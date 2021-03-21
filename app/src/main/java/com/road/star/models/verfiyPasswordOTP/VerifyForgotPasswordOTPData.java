package com.road.star.models.verfiyPasswordOTP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyForgotPasswordOTPData {

    @SerializedName("mobile")
    @Expose
    private String mobile;

    @SerializedName("country_mobile_code")
    @Expose
    private String country_mobile_code;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCountry_mobile_code() {
        return country_mobile_code;
    }

    public void setCountry_mobile_code(String country_mobile_code) {
        this.country_mobile_code = country_mobile_code;
    }

    @Override
    public String toString() {
        return "ClassPojo [mobile = " + mobile + ", country_mobile_code = " + country_mobile_code + "]";
    }

}
