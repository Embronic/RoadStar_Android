package com.road.star.base;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.road.star.R;
import com.road.star.activities.MainActivity;
import com.road.star.listners.BaseListener;
import com.road.star.utils.AppUtils;
import com.road.star.utils.DialogUtil;

public class BaseActivity extends AppCompatActivity implements BaseListener, View.OnClickListener {

    protected BaseActivity mThis;
    private ProgressDialog progressDialog;
    private int backpress;


    public static void dismissDialog(Dialog dialog) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dialog != null)
                    dialog.dismiss();
            }
        }, 5000);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mThis = this;
    }

    public void setBackEnabled(boolean isBackEnabled) {
        ImageView backArrow = findViewById(R.id.iv_back);
        if (backArrow != null) {
            if (isBackEnabled) {
                backArrow.setVisibility(View.VISIBLE);
                backArrow.setOnClickListener(this);
            } else {
                backArrow.setVisibility(View.GONE);
            }
        }
    }

    public void setHeaderTitle(String title) {
        TextView titleTv = findViewById(R.id.toolbar_title_tv);
        if (titleTv != null) {
            titleTv.setVisibility(View.VISIBLE);
            titleTv.setText(title);
        }
    }

    public void setInnerviewBackEnabled(boolean isBackEnabled) {
        ImageView backArrow = findViewById(R.id.innerView_back_iv);
        if (backArrow != null) {
            if (isBackEnabled) {
                backArrow.setVisibility(View.VISIBLE);
                backArrow.setOnClickListener(this);
            } else {
                backArrow.setVisibility(View.GONE);
            }
        }
    }


    @Override
    public void showHideProgressDialog(boolean isShow) {
        if (progressDialog != null) {
            if (isShow)
                progressDialog.show();
            else {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }
            }
        } else {
            progressDialog = DialogUtil.getProgressDialog(this);
            showHideProgressDialog(isShow);

        }
    }

    @Override
    public void isTokenRefreshed(boolean iShow) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (progressDialog != null)
            progressDialog.dismiss();
    }


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            onHeaderBackPress();
            return;
        }
        if (this instanceof MainActivity) {
            if (backpress == 1) {
                super.onBackPressed();
            } else {
                DialogUtil.showToast(this, getString(R.string.press_back_button_to_exit));
                backpress = 1;
                new Handler().postDelayed(() -> backpress = 0, 1500);
            }
        } else {
            onHeaderBackPress();
        }

    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    /**
     * This method invokes when user press back button of header or device as well.
     */
    public void onHeaderBackPress() {
        AppUtils.hideKeyboard(this);

        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        AppUtils.hideKeyboard(this);
        AppUtils.preventMultiTab(this, view);
        switch (view.getId()) {
            case R.id.iv_back:
                onHeaderBackPress();
                break;
            case R.id.innerView_back_iv:
                onHeaderBackPress();
                break;
        }
    }

    protected void addFragment(@IdRes int containerViewId,
                               @NonNull Fragment fragment,
                               @NonNull String fragmentTag,
                               @Nullable String backStackStateName) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(containerViewId, fragment, fragmentTag)
                .disallowAddToBackStack()
                .commit();
    }

    protected void replaceFragment(@IdRes int containerViewId,
                                   @NonNull Fragment fragment,
                                   @NonNull String fragmentTag,
                                   @Nullable String backStackStateName) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, fragment, fragmentTag)
                .addToBackStack(backStackStateName)
                .commit();
    }



    /*public void setErrorObserver(BaseViewModel baseViewMOdel) {
        GlobalErrorMessage globalErrorMessage = new GlobalErrorMessage(baseViewMOdel, this, this);
        globalErrorMessage.setErrorObserver();
    }
*/
}