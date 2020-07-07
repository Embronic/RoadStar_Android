package com.road.star.activities;

import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.road.star.R;
import com.road.star.base.BaseActivity;
import com.road.star.databinding.ActivitySettingsBinding;

public class SettingsActivity extends BaseActivity {

    ActivitySettingsBinding mBinding;

    /*Start home activity intent*/
    public static void startActivity(Context context) {
        Intent homeIntent = new Intent(context, SettingsActivity.class);
        context.startActivity(homeIntent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_settings);
        initToolbar();
        initView();
        initClickListeners();
    }

    private void initToolbar() {
        setHeaderTitle(getString(R.string.settings_lbl));
        setBackEnabled(true);

    }

    private void initView() {


    }

    private void initClickListeners() {
        mBinding.changePasswordRel.setOnClickListener(this);
    }

    //perform onClick
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.change_password_rel:
                ChangePasswordActivity.startActivity(mThis);
                break;
        }
    }
}
