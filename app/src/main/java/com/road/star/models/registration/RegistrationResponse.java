package com.road.star.models.registration;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistrationResponse {

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("data")
    @Expose
    private RegistrationData data;

    @SerializedName("message")
    @Expose
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public RegistrationData getData() {
        return data;
    }

    public void setData(RegistrationData data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
