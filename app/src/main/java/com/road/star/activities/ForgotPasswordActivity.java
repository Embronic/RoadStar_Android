package com.road.star.activities;

import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.road.star.R;
import com.road.star.api.ApiClient;
import com.road.star.api.ApiInterface;
import com.road.star.base.BaseActivity;
import com.road.star.databinding.ActivityForgotPasswordBinding;
import com.road.star.models.forgotPasswordOtp.ForgotPasswordOTPResponse;
import com.road.star.utils.AppUtils;
import com.road.star.utils.Lg;
import com.road.star.utils.NetworkHandler;
import com.road.star.utils.ValidUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends BaseActivity {

    private static final String TAG = "ForgotPasswordActivity";
    ActivityForgotPasswordBinding mBinding;

    /*Start SignIn activity intent*/
    public static void startActivity(Context context) {
        Intent homeIntent = new Intent(context, ForgotPasswordActivity.class);
        context.startActivity(homeIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password);
        initToolbar();
        initView();
        initClickListeners();
    }

    private void initToolbar() {
        setHeaderTitle(getString(R.string.forgot_password));
        setBackEnabled(true);

    }

    private void initView() {
        mBinding.screenBannerView.screenBannerImageview.setImageDrawable(getResources().getDrawable(R.drawable.forgot_password_icon));
    }

    private void initClickListeners() {
        mBinding.registerBtn.actionBtnParent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.register_btn:
                if (isAllFieldsValid()) {
                    onButtonClicked();
                }
                break;
        }
    }

    private boolean isAllFieldsValid() {
        if (ValidUtils.isBlank(mBinding.etEmail)) {
            AppUtils.showToast(R.string.error_blank_email);
            return false;
        }

        return true;
    }

    private void onButtonClicked() {
        if (NetworkHandler.isConnected(mThis)) {
            AppUtils.hideKeyboard(mThis);
            showHideProgressDialog(true);

            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<ForgotPasswordOTPResponse> call = apiService.getForgotPasswordOTP(mBinding.etEmail.getText().toString());

            call.enqueue(new Callback<ForgotPasswordOTPResponse>() {
                @Override
                public void onResponse(Call<ForgotPasswordOTPResponse> call, Response<ForgotPasswordOTPResponse> response) {
                    showHideProgressDialog(false);
                    if (response.isSuccessful()) {
                        if (response.body().getCode() == 200) {
                            ForgotPasswordOTPActivity.startActivity(mThis,
                                    response.body().getData().getMobile(),
                                    response.body().getData().getCountry_mobile_code(),
                                    mBinding.etEmail.getText().toString(),
                                    response.body().getData().getOtp());

                        } else {
                            AppUtils.showToast(mThis, response.body().getMessage());

                        }

                    } else {
                        AppUtils.showToast(R.string.server_error);
                    }
                }

                @Override
                public void onFailure(Call<ForgotPasswordOTPResponse> call, Throwable t) {
                    showHideProgressDialog(false);
                    Lg.e(TAG, t.getMessage());
                    AppUtils.showToast(R.string.server_error);
                }
            });
        }
    }
}
