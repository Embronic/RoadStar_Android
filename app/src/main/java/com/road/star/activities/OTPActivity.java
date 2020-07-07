package com.road.star.activities;

import androidx.databinding.DataBindingUtil;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.road.star.R;
import com.road.star.base.BaseActivity;
import com.road.star.databinding.ActivityOtpBinding;

public class OTPActivity extends BaseActivity {

    ActivityOtpBinding mBinding;

    /*Start OTP activity intent*/
    public static void startActivity(Context context) {
        Intent homeIntent = new Intent(context, OTPActivity.class);
        context.startActivity(homeIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_otp);
        initToolbar();

        initView();
        initClickListeners();
    }

    private void initToolbar() {
        setHeaderTitle(getString(R.string.verify_otp));
        setBackEnabled(true);
    }

    private void initClickListeners() {
        mBinding.registerBtn.actionBtnParent.setOnClickListener(this);
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
                RegisterOneActivity.startActivity(mThis);
                break;
        }
    }
}
