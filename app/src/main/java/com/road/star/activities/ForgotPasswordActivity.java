package com.road.star.activities;

import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.road.star.R;
import com.road.star.base.BaseActivity;
import com.road.star.databinding.ActivityForgotPasswordBinding;

public class ForgotPasswordActivity extends BaseActivity {

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
}
