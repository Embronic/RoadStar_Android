package com.road.star.activities;

import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.road.star.R;
import com.road.star.base.BaseActivity;
import com.road.star.databinding.ActivityRegisterOneBinding;
import com.road.star.fragments.DashboardFragment;

public class RegisterOneActivity extends BaseActivity {

    ActivityRegisterOneBinding mBinding;

    /*Start RegisterOne activity intent*/
    public static void startActivity(Context context) {
        Intent homeIntent = new Intent(context, RegisterOneActivity.class);
        context.startActivity(homeIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_register_one);
        initToolbar();
        initView();
        initClickListeners();
    }

    private void initView() {
        mBinding.screenBannerView.screenBannerImageview.setImageDrawable(getResources().getDrawable(R.drawable.register_bg));
    }

    private void initToolbar() {
        setHeaderTitle(getString(R.string.register));
        setBackEnabled(true);
    }

    private void initClickListeners() {
        mBinding.viewSignIn.setOnClickListener(this);
        mBinding.registerBtn.actionBtnParent.setOnClickListener(this);
    }

    //perform onClick
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.viewSignIn:
                SignInActivity.startActivity(mThis);
                break;
            case R.id.register_btn:
                MainActivity.startActivity(mThis);
                break;
        }
    }
}
