package com.road.star.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.road.star.R;
import com.road.star.api.ApiClient;
import com.road.star.api.ApiInterface;
import com.road.star.base.BaseActivity;
import com.road.star.databinding.ActivityForgotPasswordOTPBinding;
import com.road.star.models.forgotPasswordOtp.ForgotPasswordOTPResponse;
import com.road.star.models.verfiyPasswordOTP.VerifyForgotPasswordOTPResponse;
import com.road.star.utils.AppUtils;
import com.road.star.utils.Constants;
import com.road.star.utils.Lg;
import com.road.star.utils.NetworkHandler;
import com.road.star.utils.OTPHandler;
import com.road.star.utils.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordOTPActivity extends BaseActivity implements View.OnKeyListener, View.OnFocusChangeListener {

    ActivityForgotPasswordOTPBinding mBinding;
    private String TAG = "ForgotPasswordOTPActivity";

    /*Start SignIn activity intent*/
    public static void startActivity(Context context, String phoneNumber, String countryPhoneCode, String email, String otp) {
        Intent homeIntent = new Intent(context, ForgotPasswordOTPActivity.class);
        homeIntent.putExtra(Constants.NUMBER, phoneNumber);
        homeIntent.putExtra(Constants.COUNTRY_PHONE_CODE, countryPhoneCode);
        homeIntent.putExtra(Constants.EMAIL, email);
        homeIntent.putExtra(Constants.OTP, otp);
        context.startActivity(homeIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(mThis, R.layout.activity_forgot_password_o_t_p);
        initToolbar();
        initView();
        initClickListeners();
        setUpOtpView();
        iniUI(getIntent().getStringExtra(Constants.OTP));
    }

    private void iniUI(String otp) {
        AppUtils.showAlertDialog(mThis, "OTP", otp, null);
    }

    private void initToolbar() {
        setHeaderTitle(getString(R.string.verify_otp));
        setBackEnabled(true);
    }

    private void initClickListeners() {
        mBinding.registerBtn.actionBtnParent.setOnClickListener(this);
        mBinding.resendBtn.setOnClickListener(this);
    }

    private void initView() {
        mBinding.screenBannerView.screenBannerImageview.setImageDrawable(getResources().getDrawable(R.drawable.verify_otp_bg));
    }

    //perform onClick
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.register_btn:
                if (isAllFieldsValid()) {
                    onButtonClicked();
                }
                break;

            case R.id.resend_btn:
                onResendButtonClicked();
                break;
        }
    }

    private boolean isAllFieldsValid() {
        if (!isOTPValid()) {
            AppUtils.showToast(R.string.error_blank_OTP);
            return false;
        }
        return true;
    }

    private void onButtonClicked() {
        if (NetworkHandler.isConnected(mThis)) {
            showHideProgressDialog(true);
            AppUtils.hideKeyboard(mThis);

            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<VerifyForgotPasswordOTPResponse> call = apiService.verifyPasswordOTP(
                    getIntent().getStringExtra(Constants.NUMBER),
                    getIntent().getStringExtra(Constants.COUNTRY_PHONE_CODE),
                    getOtpString());

            call.enqueue(new Callback<VerifyForgotPasswordOTPResponse>() {
                @Override
                public void onResponse(Call<VerifyForgotPasswordOTPResponse> call, Response<VerifyForgotPasswordOTPResponse> response) {
                    showHideProgressDialog(false);
                    if (response.isSuccessful()) {
                        if (response.body().getCode() == 200) {
                            SessionManager.get().setNumber(getIntent().getStringExtra(Constants.NUMBER));
                            SessionManager.get().setCountryMobileCode(getIntent().getStringExtra(Constants.COUNTRY_PHONE_CODE));
                            UpdatePasswordActivity.startActivity(mThis);

                        } else {
                            AppUtils.showToast(mThis, response.body().getMessage());

                        }

                    } else {
                        showHideProgressDialog(false);
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

    private void onResendButtonClicked() {
        if (NetworkHandler.isConnected(mThis)) {
            AppUtils.hideKeyboard(mThis);
            showHideProgressDialog(true);

            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<ForgotPasswordOTPResponse> call = apiService.getForgotPasswordOTP(getIntent().getStringExtra(Constants.EMAIL));

            call.enqueue(new Callback<ForgotPasswordOTPResponse>() {
                @Override
                public void onResponse(Call<ForgotPasswordOTPResponse> call, Response<ForgotPasswordOTPResponse> response) {
                    showHideProgressDialog(false);
                    if (response.isSuccessful()) {
                        if (response.body().getCode() != 200) {
                            AppUtils.showToast(mThis, response.body().getMessage());
                        }else {
                            iniUI(response.body().getData().getOtp());
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

    private void setUpOtpView() {
        OTPHandler.setRefrence(mBinding.otpView);

        mBinding.otpView.etOtp1.addTextChangedListener(new OTPHandler.GenericTextWatcher(mBinding.otpView.etOtp1));
        mBinding.otpView.etOtp2.addTextChangedListener(new OTPHandler.GenericTextWatcher(mBinding.otpView.etOtp2));
        mBinding.otpView.etOtp3.addTextChangedListener(new OTPHandler.GenericTextWatcher(mBinding.otpView.etOtp3));
        mBinding.otpView.etOtp4.addTextChangedListener(new OTPHandler.GenericTextWatcher(mBinding.otpView.etOtp4));

        mBinding.otpView.etOtp1.setOnKeyListener(this);
        mBinding.otpView.etOtp2.setOnKeyListener(this);
        mBinding.otpView.etOtp3.setOnKeyListener(this);
        mBinding.otpView.etOtp4.setOnKeyListener(this);


        mBinding.otpView.etOtp1.setOnFocusChangeListener(this);
        mBinding.otpView.etOtp2.setOnFocusChangeListener(this);
        mBinding.otpView.etOtp3.setOnFocusChangeListener(this);
        mBinding.otpView.etOtp4.setOnFocusChangeListener(this);


        mBinding.otpView.etOtp1.requestFocus();
    }

    private boolean isOTPValid() {
        return !TextUtils.isEmpty(mBinding.otpView.etOtp1.getText()) && !TextUtils.isEmpty(mBinding.otpView.etOtp2.getText()) &&
                !TextUtils.isEmpty(mBinding.otpView.etOtp3.getText()) && !TextUtils.isEmpty(mBinding.otpView.etOtp4.getText());
    }

    //method to get the otp string
    public String getOtpString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(mBinding.otpView.etOtp1.getText().toString());
        stringBuilder.append(mBinding.otpView.etOtp2.getText().toString());
        stringBuilder.append(mBinding.otpView.etOtp3.getText().toString());
        stringBuilder.append(mBinding.otpView.etOtp4.getText().toString());
        return stringBuilder.toString();
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            setFocusedBackground(v);
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        return false;
    }

    private void setFocusedBackground(View v) {
        switch (v.getId()) {
            case R.id.etOtp1: {
                mBinding.otpView.etOtp1.setBackgroundResource(R.drawable.shape_rounded_corner_edit_text_focused);
                mBinding.otpView.etOtp2.setBackgroundResource(R.drawable.shape_rounded_corner_edit_text_default);
                mBinding.otpView.etOtp3.setBackgroundResource(R.drawable.shape_rounded_corner_edit_text_default);
                mBinding.otpView.etOtp4.setBackgroundResource(R.drawable.shape_rounded_corner_edit_text_default);
                break;
            }
            case R.id.etOtp2: {
                mBinding.otpView.etOtp1.setBackgroundResource(R.drawable.shape_rounded_corner_edit_text_default);
                mBinding.otpView.etOtp2.setBackgroundResource(R.drawable.shape_rounded_corner_edit_text_focused);
                mBinding.otpView.etOtp3.setBackgroundResource(R.drawable.shape_rounded_corner_edit_text_default);
                mBinding.otpView.etOtp4.setBackgroundResource(R.drawable.shape_rounded_corner_edit_text_default);
                break;
            }
            case R.id.etOtp3: {
                mBinding.otpView.etOtp1.setBackgroundResource(R.drawable.shape_rounded_corner_edit_text_default);
                mBinding.otpView.etOtp2.setBackgroundResource(R.drawable.shape_rounded_corner_edit_text_default);
                mBinding.otpView.etOtp3.setBackgroundResource(R.drawable.shape_rounded_corner_edit_text_focused);
                mBinding.otpView.etOtp4.setBackgroundResource(R.drawable.shape_rounded_corner_edit_text_default);
                break;
            }
            case R.id.etOtp4: {
                mBinding.otpView.etOtp1.setBackgroundResource(R.drawable.shape_rounded_corner_edit_text_default);
                mBinding.otpView.etOtp2.setBackgroundResource(R.drawable.shape_rounded_corner_edit_text_default);
                mBinding.otpView.etOtp3.setBackgroundResource(R.drawable.shape_rounded_corner_edit_text_default);
                mBinding.otpView.etOtp4.setBackgroundResource(R.drawable.shape_rounded_corner_edit_text_focused);
                break;
            }
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
