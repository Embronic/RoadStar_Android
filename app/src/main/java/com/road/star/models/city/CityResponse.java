package com.road.star.models.city;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CityResponse {

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("data")
    @Expose
    private ArrayList<CityData> data;

    @SerializedName("message")
    @Expose
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ArrayList<CityData> getData() {
        return data;
    }

    public void setData(ArrayList<CityData> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
