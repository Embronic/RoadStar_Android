package com.road.star.models.forgotPasswordOtp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgotPasswordOTPResponse {

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("data")
    @Expose
    private ForgotPasswordOTPData data;

    @SerializedName("message")
    @Expose
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ForgotPasswordOTPData getData() {
        return data;
    }

    public void setData(ForgotPasswordOTPData data) {
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
