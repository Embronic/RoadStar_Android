package com.road.star.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.road.star.R;
import com.road.star.base.BaseActivity;
import com.road.star.callback.DialogCallback;
import com.road.star.databinding.ActivityForgotPasswordBinding;
import com.road.star.databinding.ActivityPaymentMethodBinding;
import com.road.star.utils.DialogUtil;

public class PaymentMethodActivity extends BaseActivity implements DialogCallback {

    ActivityPaymentMethodBinding mBinding;

    /*Start welcome activity intent*/
    public static void startActivity(Context context) {
        Intent homeIntent = new Intent(context, PaymentMethodActivity.class);
        context.startActivity(homeIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_payment_method);

        initClicklistner();
        initToolbar();
    }

    private void initClicklistner() {
        mBinding.doneBtn.actionBtnParent.setOnClickListener(this);
        mBinding.cancelBtn.actionBtnParent.setOnClickListener(this);
        mBinding.modifyRequestBtn.actionBtnParent.setOnClickListener(this);
    }

    private void initToolbar() {
        setInnerviewBackEnabled(true);
    }

    //perform onClick
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.modify_request_btn:
                DialogUtil.showModifyRequestAlertDialog(mThis, getSupportFragmentManager(), this);
                break;
            case R.id.done_btn:
                DialogUtil.showRequestAccceptedAlertDialog(mThis, getSupportFragmentManager(), this);
                break;
            case R.id.cancel_btn:
                DialogUtil.showCancelRequestAlertDialog(mThis, getSupportFragmentManager(), this);
                break;
        }
    }

    @Override
    public void okPressed() {

    }

    @Override
    public void cancelPressed() {

    }
}
