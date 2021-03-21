package com.road.star.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.road.star.R;
import com.road.star.api.ApiClient;
import com.road.star.api.ApiInterface;
import com.road.star.base.BaseActivity;
import com.road.star.databinding.ActivityUpdatePasswordBinding;
import com.road.star.models.verfiyPasswordOTP.VerifyForgotPasswordOTPResponse;
import com.road.star.utils.AppUtils;
import com.road.star.utils.Lg;
import com.road.star.utils.NetworkHandler;
import com.road.star.utils.SessionManager;
import com.road.star.utils.ValidUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdatePasswordActivity extends BaseActivity {


    ActivityUpdatePasswordBinding mBinding;
    private String TAG = "UpdatePasswordActivity";

    /*Start home activity intent*/
    public static void startActivity(Context context) {
        Intent homeIntent = new Intent(context, UpdatePasswordActivity.class);
        context.startActivity(homeIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(mThis, R.layout.activity_update_password);
        initToolbar();
        initView();
        initClickListeners();
    }

    private void initToolbar() {
        setHeaderTitle(getString(R.string.change_password));
        setBackEnabled(true);

    }

    private void initView() {


    }

    private void initClickListeners() {
        mBinding.udpateBtn.actionBtnParent.setOnClickListener(this);
    }

    //perform onClick
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.udpate_btn:
                if (isAllFieldsValid()) {
                    onButtonClicked();
                }
                break;
        }
    }

    private boolean isAllFieldsValid() {
        if (ValidUtils.isBlank(mBinding.etNewPassword)) {
            AppUtils.showToast(R.string.error_blank_password);
            return false;
        }

        if (!ValidUtils.validatePassword(mBinding.etNewPassword, mBinding.etConfirmPassword)) {
            AppUtils.showToast(R.string.error_password_not_match);
            return false;
        }

        return true;
    }

    private void onButtonClicked() {
        if (NetworkHandler.isConnected(mThis)) {
            AppUtils.hideKeyboard(mThis);
            showHideProgressDialog(true);

            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<VerifyForgotPasswordOTPResponse> call = apiService.updatePassword(SessionManager.get().getNumber(),
                    SessionManager.get().getCountryMobileCode(),
                    mBinding.etNewPassword.getText().toString(),
                    mBinding.etConfirmPassword.getText().toString());

            call.enqueue(new Callback<VerifyForgotPasswordOTPResponse>() {
                @Override
                public void onResponse(Call<VerifyForgotPasswordOTPResponse> call, Response<VerifyForgotPasswordOTPResponse> response) {
                    showHideProgressDialog(false);
                    if (response.isSuccessful()) {
                        if (response.body().getCode() == 200) {
                            WelcomeActivity.startActivity(mThis);
                            finishAffinity();

                        } else {
                            AppUtils.showToast(mThis, response.body().getMessage());

                        }

                    } else {
                        AppUtils.showToast(R.string.server_error);
                    }
                }

                @Override
                public void onFailure(Call<VerifyForgotPasswordOTPResponse> call, Throwable t) {
                    showHideProgressDialog(false);
                    Lg.e(TAG, t.getMessage());
                    AppUtils.showToast(R.string.server_error);
                }
            });
        }
    }
}
