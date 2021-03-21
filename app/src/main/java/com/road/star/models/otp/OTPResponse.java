package com.road.star.models.otp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OTPResponse {

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("data")
    @Expose
    private OTPData data;

    @SerializedName("message")
    @Expose
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public OTPData getData() {
        return data;
    }

    public void setData(OTPData data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ClassPojo [code = " + code + ", data = " + data + ", message = " + message + "]";
    }

}
