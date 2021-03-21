package com.road.star.models.registration;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistrationData {

    @SerializedName("mobile")
    @Expose
    private String mobile;

    @SerializedName("provider_id")
    @Expose
    private String provider_id;

    @SerializedName("email")
    @Expose
    private String email;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(String provider_id) {
        this.provider_id = provider_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ClassPojo [mobile = " + mobile + ", provider_id = " + provider_id + ", email = " + email + "]";
    }

}
