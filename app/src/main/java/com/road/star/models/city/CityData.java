package com.road.star.models.city;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityData {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("state_id")
    @Expose
    private String state_id;

    @SerializedName("city_id")
    @Expose
    private String city_id;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState_id() {
        return state_id;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }


    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }
}
