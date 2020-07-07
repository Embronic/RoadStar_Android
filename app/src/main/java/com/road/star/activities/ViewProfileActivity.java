package com.road.star.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.road.star.R;
import com.road.star.base.BaseActivity;
import com.road.star.databinding.ActivityViewProfileBinding;

public class ViewProfileActivity extends BaseActivity {

    ActivityViewProfileBinding mBinding;

    /*Start ViewProfile Activity intent*/
    public static void startActivity(Context context) {
        Intent homeIntent = new Intent(context, ViewProfileActivity.class);
        context.startActivity(homeIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_view_profile);
        initToolbar();
        initUI();
        initClickListeners();
    }

    private void initClickListeners() {
        mBinding.registerBtn.actionBtnParent.setOnClickListener(this);
    }

    private void initToolbar() {
        setHeaderTitle(getString(R.string.my_profile));
        setBackEnabled(true);
        mBinding.screenToolbar.toolbarTitleTv.setTextColor(getResources().getColor(R.color.colorwhite));
        mBinding.screenToolbar.ivBack.setColorFilter(getResources().getColor(R.color.colorwhite));
    }


    private void initUI() {
        //Name
        mBinding.nameLayout.ivMenu.setBackground(getResources().getDrawable(R.drawable.ic_launcher_background));
        mBinding.nameLayout.tvMenu.setText("Roma");

        //Email
        mBinding.emailLayout.ivMenu.setBackground(getResources().getDrawable(R.drawable.ic_launcher_background));
        mBinding.emailLayout.tvMenu.setText("roma19956@gmail.com");

        //Address
        mBinding.addressLayout.ivMenu.setBackground(getResources().getDrawable(R.drawable.ic_launcher_background));
        mBinding.addressLayout.tvMenu.setText("4299 express Lane");

        //Payment
        mBinding.paymentLayout.ivMenu.setBackground(getResources().getDrawable(R.drawable.ic_launcher_background));
        mBinding.paymentLayout.tvMenu.setText("Payment Method");
        mBinding.paymentLayout.ivRightIcon.setVisibility(View.VISIBLE);
        mBinding.paymentLayout.ivRightIcon.setBackground(getResources().getDrawable(R.drawable.ic_arrow_right));

        //Setting
        mBinding.settingLayout.ivMenu.setBackground(getResources().getDrawable(R.drawable.ic_launcher_background));
        mBinding.settingLayout.tvMenu.setText("Settings");
        mBinding.settingLayout.ivRightIcon.setVisibility(View.VISIBLE);
        mBinding.settingLayout.ivRightIcon.setBackground(getResources().getDrawable(R.drawable.ic_arrow_right));
    }


    //perform onClick
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.register_btn:
                EditProfileActivity.startActivity(mThis);
                break;
        }
    }


}
