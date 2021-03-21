package com.road.star.models.forgotPasswordOtp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgotPasswordOTPData {

    @SerializedName("mobile")
    @Expose
    private String mobile;

    @SerializedName("country_phone_code")
    @Expose
    private String country_mobile_code;

    @SerializedName("otp")
    @Expose
    private String otp;

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

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    @Override
    public String toString() {
        return "ClassPojo [mobile = " + mobile + ", country_mobile_code = " + country_mobile_code + ", otp = " + otp + "]";
    }

}
