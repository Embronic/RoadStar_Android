package com.road.star.models.verfiyPasswordOTP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyForgotPasswordOTPResponse {

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("data")
    @Expose
    private VerifyForgotPasswordOTPData data;

    @SerializedName("message")
    @Expose
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public VerifyForgotPasswordOTPData getData() {
        return data;
    }

    public void setData(VerifyForgotPasswordOTPData data) {
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
