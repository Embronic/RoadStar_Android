package com.road.star.utils;

import android.app.Application;

import com.road.star.app.AppController;

public class SessionManager {
    private static SessionManager sInstance;
    private PreferenceUtil pref;


    private SessionManager(Application application) {
        pref = new PreferenceUtil(application);
    }

    public static void init(Application application) {
        if (sInstance == null) {
            sInstance = new SessionManager(application);
        }
    }

    public static SessionManager get() {
        init(AppController.getInstance());
        return sInstance;
    }


    public String getNumber() {
        return pref.getStringData(PrefConstant.NUMBER);
    }

    public void setNumber(String number) {
        pref.setStringData(PrefConstant.NUMBER, number);
    }

    public String getCountryMobileCode() {
        return pref.getStringData(PrefConstant.COUNTRY_MOBILE_CODE);
    }

    public void setCountryMobileCode(String countryMobileCode) {
        pref.setStringData(PrefConstant.COUNTRY_MOBILE_CODE, countryMobileCode);
    }

    public String getCountryCode() {
        return pref.getStringData(PrefConstant.COUNTRY_CODE);
    }

    public void setCountryCode(String countryCode) {
        pref.setStringData(PrefConstant.COUNTRY_CODE, countryCode);
    }

    public boolean isLogin() {
        return pref.getBoolean(PrefConstant.isLogin);
    }

    public void setisLogin(boolean islogin) {
        pref.setBooleanData(PrefConstant.isLogin, islogin);
    }


    public String getFirstName() {
        return pref.getStringData(PrefConstant.first_name);
    }

    public void setFirstName(String firstName) {
        pref.setStringData(PrefConstant.first_name, firstName);
    }

    public String getLastName() {
        return pref.getStringData(PrefConstant.last_name);
    }

    public void setLastName(String lastName) {
        pref.setStringData(PrefConstant.last_name, lastName);
    }

    public String getCountryPhoneCode() {
        return pref.getStringData(PrefConstant.country_phone_code);
    }

    public void setCountryPhoneCode(String countryphonecode) {
        pref.setStringData(PrefConstant.country_phone_code, countryphonecode);
    }

    public String getMobile() {
        return pref.getStringData(PrefConstant.mobile);
    }

    public void setMobile(String mobile) {
        pref.setStringData(PrefConstant.mobile, mobile);
    }

    public String getEmail() {
        return pref.getStringData(PrefConstant.email);
    }

    public void setEmail(String email) {
        pref.setStringData(PrefConstant.email, email);
    }

    public String getCountry() {
        return pref.getStringData(PrefConstant.country);
    }

    public void setCountry(String country) {
        pref.setStringData(PrefConstant.country, country);
    }

    public String getCountryName() {
        return pref.getStringData(PrefConstant.country_name);
    }

    public void setCountryName(String countryName) {
        pref.setStringData(PrefConstant.country_name, countryName);
    }

    public String getState() {
        return pref.getStringData(PrefConstant.state);
    }

    public void setState(String state) {
        pref.setStringData(PrefConstant.state, state);
    }

    public String getStateName() {
        return pref.getStringData(PrefConstant.state_name);
    }

    public void setStateName(String stateName) {
        pref.setStringData(PrefConstant.state_name, stateName);
    }


    public String getCity() {
        return pref.getStringData(PrefConstant.city);
    }

    public void setCity(String city) {
        pref.setStringData(PrefConstant.city, city);
    }

    public String getCityName() {
        return pref.getStringData(PrefConstant.city_name);
    }

    public void setCityName(String cityName) {
        pref.setStringData(PrefConstant.city_name, cityName);
    }

    public void clear() {
        pref.clear();
    }
}

