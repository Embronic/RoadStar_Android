package com.road.star.models;

import com.google.gson.annotations.SerializedName;

public class CustomSpinnerModel {

    @SerializedName("Title")
    private String Title;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
