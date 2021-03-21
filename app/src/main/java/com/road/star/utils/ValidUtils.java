package com.road.star.utils;

import android.text.Editable;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

public class ValidUtils {

    public static boolean isBlank(TextView tv, String msg) {
        if (TextUtils.isEmpty(tv.getText().toString().trim())) {
            tv.setError(msg);
            return true;
        }
        return false;
    }

    public static boolean isBlank(TextView tv) {
        if (TextUtils.isEmpty(tv.getText().toString().trim())) {
            return true;
        }
        return false;
    }

    public static boolean isBlank(TextView tv, ScrollView scrollView, String msg) {
        if (TextUtils.isEmpty(tv.getText().toString().trim())) {
            tv.setError(msg);
            scrollView.post(() -> scrollView.smoothScrollTo(0, tv.getBottom()));
            return true;
        }
        return false;
    }

    public static boolean validatePhoneLength(EditText mobileNoEdittext) {
        Editable phone = mobileNoEdittext.getText();
        int length = phone.length();
        return length != 10;
    }

    public static boolean validatePassword(EditText newPassword, EditText confirmPassword) {
        if (newPassword.getText().toString().equals(confirmPassword.getText().toString())) {
            return true;
        }
        return false;
    }


}
