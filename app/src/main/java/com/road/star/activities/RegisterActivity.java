package com.road.star.activities;

import androidx.databinding.DataBindingUtil;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.road.star.R;
import com.road.star.base.BaseActivity;
import com.road.star.databinding.ActivityRegisterBinding;

public class RegisterActivity extends BaseActivity {

    ActivityRegisterBinding mBinding;

    /*Start register activity intent*/
    public static void startActivity(Context context) {
        Intent homeIntent = new Intent(context, RegisterActivity.class);
        context.startActivity(homeIntent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        initToolbar();
        initView();
        initClicklistners();
    }

    private void initClicklistners() {
        mBinding.registerBtn.actionBtnParent.setOnClickListener(this);
    }

    private void initView() {
        mBinding.screenBannerView.screenBannerImageview.setImageDrawable(getResources().getDrawable(R.drawable.phonenumber_validate_bg));
    }


    private void initToolbar() {
        setHeaderTitle(getString(R.string.register));
        setBackEnabled(true);
    }

    //perform onClick
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.register_btn:
                OTPActivity.startActivity(mThis);
                break;
        }
    }
}
