package com.road.star.listners;

public interface BaseListener {
    /**
     * Method to show a progress dialog on some background task
     */
    void showHideProgressDialog(boolean iShow);

    void isTokenRefreshed(boolean iShow);

}


