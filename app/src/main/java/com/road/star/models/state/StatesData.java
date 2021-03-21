package com.road.star.models.state;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatesData implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("state_id")
    @Expose
    private String state_id;

    @SerializedName("country_id")
    @Expose
    private String country_id;

    protected StatesData(Parcel in) {
        name = in.readString();
        state_id = in.readString();
        country_id = in.readString();
    }

    public static final Creator<StatesData> CREATOR = new Creator<StatesData>() {
        @Override
        public StatesData createFromParcel(Parcel in) {
            return new StatesData(in);
        }

        @Override
        public StatesData[] newArray(int size) {
            return new StatesData[size];
        }
    };

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

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    @Override
    public String toString() {
        return "ClassPojo [name = " + name + ", state_id = " + state_id + ", country_id = " + country_id + "]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(state_id);
        dest.writeString(country_id);
    }
}
