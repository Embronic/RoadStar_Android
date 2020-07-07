package com.road.star.activities;

import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.road.star.R;
import com.road.star.base.BaseActivity;
import com.road.star.databinding.ActivityChangePasswordBinding;

public class ChangePasswordActivity extends BaseActivity {


    ActivityChangePasswordBinding mBinding;


    /*Start home activity intent*/
    public static void startActivity(Context context) {
        Intent homeIntent = new Intent(context, ChangePasswordActivity.class);
        context.startActivity(homeIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_change_password);
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
        mBinding.saveBtn.actionBtnParent.setOnClickListener(this);
    }

    //perform onClick
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.save_btn:
                //RatingActivity.startActivity(mThis);
                break;
        }
    }
}
