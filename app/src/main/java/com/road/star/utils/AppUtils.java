package com.road.star.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.road.star.R;
import com.road.star.app.AppController;
import com.road.star.base.BaseActivity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import okhttp3.RequestBody;


/**
 * Provides convenience methods and abstractions to some tasks in Android
 */

public class AppUtils {
    public final static String PHONE_PATTERN = "^[0-9]$";
    public static final int REQUEST_TAKE_PHOTO = 101;
    private static final String TAG = AppUtils.class.getSimpleName();
    public static String mCurrentPhotoPath;
    static ProgressDialog mProgressDialog;
    private static String selectedDate = "";
    private static int mYear;
    private static int mMonth;
    private static int mDay;

    /**
     * Shows a long time duration toast message.
     *
     * @param msg Message to be show in the toast.
     * @return Toast object just shown
     **/
    public static Toast showToast(Context ctx, CharSequence msg) {
        return showToast(ctx, msg, Toast.LENGTH_LONG);
    }

    public static void showToast(int stringId) {
        Toast.makeText(AppController.getContext(), AppController.getContext().getString(stringId),
                Toast.LENGTH_SHORT).show();
    }

    /**
     * Shows the message passed in the parameter in the Toast.
     *
     * @param msg      Message to be show in the toast.
     * @param duration Duration in milliseconds for which the toast should be shown
     * @return Toast object just shown
     **/
    public static Toast showToast(Context ctx, CharSequence msg, int duration) {
        Toast toast = Toast.makeText(ctx, msg, Toast.LENGTH_SHORT);
        toast.setDuration(duration);
        toast.show();
        return toast;
    }



    /**
     * Converting dp to pixel
     */
    public static int dpToPx(Context context, int dp) {
        Resources r = context.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    public static String getString(int stringId) {
        String string = AppController.getContext().getString(stringId);
        return string;
    }


    @SuppressLint("NewApi")
    public static int getDeviceHeight(Activity activity) {
        int deviceHeight = 0;

        Point size = new Point();
        WindowManager windowManager = activity.getWindowManager();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            windowManager.getDefaultDisplay().getSize(size);
            deviceHeight = size.y;
        } else {
            Display display = windowManager.getDefaultDisplay();
            deviceHeight = display.getHeight();
        }
        return deviceHeight;
    }

    public static void preventTwoClick(final View view) {
        view.setEnabled(false);
        view.postDelayed(new Runnable() {
            public void run() {
                view.setEnabled(true);
            }
        }, 600);
    }

    /**
     * Converts the passed in drawable to Bitmap representation
     **/
    public static Bitmap drawableToBitmap(Drawable drawable) {

        if (drawable == null) {
            throw new NullPointerException("Drawable to convert should NOT be null");
        }

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        if (drawable.getIntrinsicWidth() <= 0 && drawable.getIntrinsicHeight() <= 0) {
            return null;
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }



    /**
     * Shows an alert dialog with the OK button. When the user presses OK button, the dialog
     * dismisses.
     **/
    public static void showAlertDialog(Context context, @StringRes int titleResId, @StringRes int bodyResId) {
        showAlertDialog(context, context.getString(titleResId),
                context.getString(bodyResId), null);
    }

    /**
     * Shows an alert dialog with the OK button. When the user presses OK button, the dialog
     * dismisses.
     **/
    public static void showAlertDialog(Context context, String title, String body) {
        showAlertDialog(context, title, body, null);
    }



    public static Bitmap getIcon(@NonNull Context context, @DrawableRes int id) {
        Drawable vectorDrawable = ResourcesCompat.getDrawable(context.getResources(), id, context.getTheme());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(), Config.ARGB_8888);
        return bitmap;
    }

    public static Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {
        Drawable drawable = AppCompatResources.getDrawable(context, drawableId);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    /**
     * Shows an alert dialog with OK button
     **/
    public static void showAlertDialog(Context context, String title, String body, DialogInterface.OnClickListener okListener) {

        if (okListener == null) {
            okListener = new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            };
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setMessage(body).setPositiveButton("OK", okListener);

        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }

        builder.show();
    }

    public static String getDeviceId(Context context) {
        return UUID.randomUUID().toString();
    }


    public static String getCommonSeperatedString(List<String> actionObjects) {
        StringBuffer sb = new StringBuffer();
        for (String actionObject : actionObjects) {
            sb.append(actionObject).append(",");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        return sb.toString();
    }


    @Nullable
    /**
     *
     * @deprecated Use {#setTextValues} or {#getNullEmptyCheckedValue}
     * Get the correctly appended name from the given name parameters
     *
     * @param firstName
     *            First name
     * @param lastName
     *            Last name
     *
     * @return Returns correctly formatted full name. Returns null if both the values are null.
     **/
    public static String getName(String firstName, String lastName) {
        if (!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName)) {
            return firstName + " " + lastName;
        } else if (!TextUtils.isEmpty(firstName)) {
            return firstName;
        } else if (!TextUtils.isEmpty(lastName)) {
            return lastName;
        } else {
            return null;
        }
    }

    /**
     * Hides the already popped up keyboard from the screen.
     *
     * @param context
     */
    public static void hideKeyboard(Context context) {
        try {
            InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            Log.e(TAG, "Sigh, cant even hide keyboard " + e.getMessage());
        }
    }

    public static void hideKeyboard_particular_edittext(Activity activity, EditText editText) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }


    /**
     * This method is used to hide keyboard on clicking anywhere on the screen.
     *
     * @param view    parent view
     * @param context Context of the current activity.
     */
    public static void hideKeyboardOnClickingScreen(View view, final Context context) {

        //Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideKeyboard(context);
                    return false;
                }

            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                hideKeyboardOnClickingScreen(innerView, context);
            }
        }
    }


    public static boolean getNetworkState(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }


    public static void setFocus(EditText editText) {
        editText.requestFocus();
        editText.setSelection(editText.getText().length());
    }

    public static DatePickerDialog createDialogWithoutDateField(Context context,
                                                                DatePickerDialog.OnDateSetListener listener, int year, int month, int date) {
        DatePickerDialog dpd = new DatePickerDialog(context, listener, year, month, date);
        try {
            java.lang.reflect.Field[] datePickerDialogFields = dpd.getClass().getDeclaredFields();
            for (java.lang.reflect.Field datePickerDialogField : datePickerDialogFields) {
                if (datePickerDialogField.getName().equals("mDatePicker")) {
                    datePickerDialogField.setAccessible(true);
                    DatePicker datePicker = (DatePicker) datePickerDialogField.get(dpd);
                    java.lang.reflect.Field[] datePickerFields = datePickerDialogField.getType().getDeclaredFields();
                    for (java.lang.reflect.Field datePickerField : datePickerFields) {
                        Log.i("test", datePickerField.getName());
                        if ("mDaySpinner".equals(datePickerField.getName())) {
                            datePickerField.setAccessible(true);
                            Object dayPicker = datePickerField.get(datePicker);
                            ((View) dayPicker).setVisibility(View.GONE);
                        }
                    }
                }
            }
        } catch (Exception ex) {
        }
        return dpd;
    }


    public static boolean checkPermissionCam(Activity context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && (context.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                    || context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    || context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
            ))
                return false;
            else
                return true;
        } else {
            return true;
        }
    }


    public static void checkForPermissionForCamera(Activity context) {
        ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 102);
    }


    public static boolean checkPermissionForGalary(Activity context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int READ_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(context, Manifest
                    .permission.READ_EXTERNAL_STORAGE);

            int WRITE_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(context, Manifest
                    .permission.WRITE_EXTERNAL_STORAGE);

            return READ_EXTERNAL_STORAGE == PackageManager.PERMISSION_GRANTED &&
                    WRITE_EXTERNAL_STORAGE == PackageManager.PERMISSION_GRANTED;
        } else {
            return true;
        }
    }

    public static void ReqForPermissionStorage(Activity context, int permissionRequestCode) {
        ActivityCompat.requestPermissions(context, new String[]{Manifest.permission
                .READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, permissionRequestCode);
    }


    public static String getParsedAmount(double amount) {
        return "€ " + String.format("%.2f", amount);
    }

    public static String getFormatedDuration(int duration) {
        if (duration <= 1)
            return duration + " min";
        else
            return duration + " mins";

    }

    public static Date convertStringIntoDate(String date, String sourceFormat) {
        Date convertedDate = null;

        SimpleDateFormat sdf = new SimpleDateFormat(sourceFormat, Locale.ENGLISH);
        try {
            convertedDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return convertedDate;
    }


    public static void openMapActivity(String lat, String lng, Context mContext) {
        String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=" + lat + "," + lng + "&" + "mode=l");
        Intent intent = new Intent(Intent.CATEGORY_APP_MAPS);
        intent.setDataAndType(Uri.parse(uri), "text/html");
        intent.setPackage("com.google.android.apps.maps");
        try {
            mContext.startActivity(Intent.createChooser(intent, "Select an application"));
        } catch (ActivityNotFoundException ex) {
            try {
                Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                mContext.startActivity(unrestrictedIntent);
            } catch (ActivityNotFoundException innerEx) {
                Toast.makeText(mContext, "Please install a maps application", Toast.LENGTH_LONG).show();
            }
        }
    }

    public static void openGoogleMap(double latitude, double longitude, double destinationLat, double destinationLng, Context context) {
        String url = "http://maps.google.com/maps?f=d&hl=en&saddr=" + latitude + "," + longitude + "&daddr=" + destinationLat + "," + destinationLng;
        ///  String url = "google.navigation:q=" + getLocationName(context, latitude, longitude);
        Uri gmmIntentUri = Uri.parse(url);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");


        try {
            context.startActivity(Intent.createChooser(mapIntent, "Select an application"));
        } catch (ActivityNotFoundException ex) {
            try {
                Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                context.startActivity(unrestrictedIntent);
            } catch (ActivityNotFoundException innerEx) {
                Toast.makeText(context, "Please install a maps application", Toast.LENGTH_LONG).show();
            }
        }
    }

    public static void preventMultiTab(Context context, View view) {
        view.setEnabled(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setEnabled(true);
            }
        }, 1000);
    }




    // Get the screen current brightness
    public static int getScreenBrightness(Context context) {
        int brightnessValue = Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS,
                0
        );
        return brightnessValue;
    }


    public static int getMaximumScreenBrightnessSetting() {
        final Resources res = Resources.getSystem();
        final int id = res.getIdentifier("config_screenBrightnessSettingMaximum", "integer", "android");  // API17+
        if (id != 0) {
            try {
                return res.getInteger(id);
            } catch (Resources.NotFoundException e) {
                // ignore
            }
        }
        return 255;
    }




    public static Map<String, RequestBody> getBeanToMaps(Object bean) {
        Map<String, RequestBody> properties = new HashMap<>();
        for (Method method : bean.getClass().getDeclaredMethods()) {
            if (Modifier.isPublic(method.getModifiers())
                    && method.getParameterTypes().length == 0
                    && method.getReturnType() != void.class
                    && method.getName().matches("^(get|is).+")
            ) {
                String name = method.getName().replaceAll("^(get|is)", "");
                name = Character.toLowerCase(name.charAt(0)) + (name.length() > 1 ? name.substring(1) : "");
                Object value = null;
                try {
                    value = method.invoke(bean);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                if (value != null)
                    properties.put(name, createPartFromString(value.toString()));
            }
        }
        return properties;
    }

    private static RequestBody createPartFromString(String data) {
        return RequestBody.create(
                okhttp3.MultipartBody.FORM, data);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static boolean hasWriteSettingsPermission(Context context) {
        return Settings.System.canWrite(context);
    }

    // Start can modify system settings panel to let user change the write settings permission.
    public static void changeWriteSettingsPermission(Context context) {
        Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
        context.startActivity(intent);
    }

    public static String[] getCategorySpinnerData(Context mThis) {
       return mThis.getResources().getStringArray(R.array.category_spinner_options);
    }

    public static String[] getProductTypeSpinnerData(Context mThis) {
        return mThis.getResources().getStringArray(R.array.product_spinner_options);
    }

    public static String[] getNameSpinnerData(Context mThis) {
        return mThis.getResources().getStringArray(R.array.name_spinner_options);
    }

}
