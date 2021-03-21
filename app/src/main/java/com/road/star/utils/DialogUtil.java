package com.road.star.utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.snackbar.Snackbar;
import com.road.star.R;
import com.road.star.activities.ModifyRequestActivity;
import com.road.star.callback.DialogCallback;

public class DialogUtil {

    /**
     * get a blocking progress dialog.
     *
     * @param context context of current activity/fragment
     * @return create of {@link ProgressDialog}
     */
    public static ProgressDialog getProgressDialog(Context context) {
        ProgressDialog progressDialog = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            progressDialog = new ProgressDialog(context, R.style.ProgressTheme);
        else
            progressDialog = new ProgressDialog(context, R.style.AlertDialog_Holo);
        progressDialog.setMessage("Loading..");
        progressDialog.setCancelable(false);
        return progressDialog;
    }

    public static void showToast(@NonNull Context context, String msg) {
        Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(@NonNull Context context, int msg) {
        Toast.makeText(context.getApplicationContext(), context.getResources().getString(msg), Toast.LENGTH_SHORT).show();
    }

    public static void showCancelRequestAlertDialog(Context activity, FragmentManager manager, DialogCallback mCallback)//, final
    {
        final Dialog dialog = new Dialog(activity, R.style.DialogTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.cancel_request_dialog);

        RelativeLayout submit_btRel = dialog.findViewById(R.id.submit_btRel);
        RelativeLayout cancel_btRel = dialog.findViewById(R.id.cancel_btRel);

        submit_btRel.setOnClickListener(v -> {
           /* if (mCallback != null) {
                mCallback.onPersonalInfoUpdate();
            }*/
            dialog.dismiss();
        });

        cancel_btRel.setOnClickListener(v -> {
            /*if (mCallback != null) {
                mCallback.cancelPressed();
            }*/
            dialog.dismiss();
        });

        dialog.show();
    }

    public static void showChooseCameraAlertDialog(Context activity, FragmentManager manager, DialogCallback mCallback) {
        final Dialog dialog = new Dialog(activity, R.style.DialogTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.choose_picture_dialog);

        RelativeLayout submit_btRel = dialog.findViewById(R.id.submit_btRel);
        RelativeLayout cancel_btRel = dialog.findViewById(R.id.cancel_btRel);

        submit_btRel.setOnClickListener(v -> {
           /* if (mCallback != null) {
                mCallback.onPersonalInfoUpdate();
            }*/
            dialog.dismiss();
        });

        cancel_btRel.setOnClickListener(v -> {
            /*if (mCallback != null) {
                mCallback.cancelPressed();
            }*/
            dialog.dismiss();
        });

        dialog.show();
    }

    public static void showRequestAccceptedAlertDialog(Context activity, FragmentManager manager, DialogCallback mCallback)//, final
    {
        final Dialog dialog = new Dialog(activity, R.style.DialogTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.request_accepted_dialog);

        LinearLayout submit_btRel = dialog.findViewById(R.id.submit_btRel);

        submit_btRel.setOnClickListener(v -> {
           /* if (mCallback != null) {
                mCallback.onPersonalInfoUpdate();
            }*/
            dialog.dismiss();
        });


        dialog.show();
    }

    public static void showModifyRequestAlertDialog(Context activity, FragmentManager manager, DialogCallback mCallback)//, final
    {
        final Dialog dialog = new Dialog(activity, R.style.DialogTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.modify_request_dialog);

        RelativeLayout submit_btRel = dialog.findViewById(R.id.submit_btRel);
        RelativeLayout cancel_btRel = dialog.findViewById(R.id.cancel_btRel);

        submit_btRel.setOnClickListener(v -> {
           /* if (mCallback != null) {
                mCallback.onPersonalInfoUpdate();
            }*/

            ModifyRequestActivity.startActivity(activity);
            dialog.dismiss();
        });

        cancel_btRel.setOnClickListener(v -> {
            /*if (mCallback != null) {
                mCallback.cancelPressed();
            }*/
            dialog.dismiss();
        });

        dialog.show();
    }


    public static void showLogoutAlertDialog(Context activity, FragmentManager manager, DialogCallback mCallback)//, final
    {
        final Dialog dialog = new Dialog(activity, R.style.DialogTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.logout_dialog);

        RelativeLayout submit_btRel = dialog.findViewById(R.id.submit_btRel);
        RelativeLayout cancel_btRel = dialog.findViewById(R.id.cancel_btRel);

        submit_btRel.setOnClickListener(v -> {
            if (mCallback != null) {
                mCallback.okPressed();
            }
            dialog.dismiss();
        });

        cancel_btRel.setOnClickListener(v -> {
            if (mCallback != null) {
                mCallback.cancelPressed();
            }
            dialog.dismiss();
        });

        dialog.show();
    }

    public static void showNoNetworkToast(Context context) {
        showToast(context, context.getString(R.string.no_internet_error_msg));
    }

    public static Snackbar showNoNetworkSnackBar(@NonNull View anyView) {
        return showSnackBar(anyView, R.string.no_internet_error_msg);
    }

    public static Snackbar showSnackBar(View anyView, int msg) {
        Resources res = anyView.getContext().getResources();
        return showSnackBar(anyView, res.getString(msg));
    }

    public static Snackbar showSnackBar(View anyView, String msg) {
        final Snackbar snackBar = Snackbar.make(anyView, msg, Snackbar.LENGTH_LONG);
        snackBar.setActionTextColor(Color.WHITE);
        if (anyView instanceof CoordinatorLayout) {
            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams)
                    snackBar.getView().getLayoutParams();
            params.setMargins(0, 0, 0, 130);
            snackBar.getView().setLayoutParams(params);
        }
        View view = snackBar.getView();
        TextView tv = view.findViewById(R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        tv.setMaxLines(5);
        snackBar.show();
        return snackBar;
    }


}
