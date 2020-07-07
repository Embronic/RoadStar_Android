package com.road.star.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import com.road.star.R;
import com.road.star.adapter.CustomSpinnerAdapter;
import com.road.star.base.BaseActivity;
import com.road.star.databinding.ActivityReceiverDetailBinding;
import com.road.star.models.CustomSpinnerModel;
import com.road.star.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

public class ReceiverDetailActivity extends BaseActivity {

    ActivityReceiverDetailBinding mBinding;
    private String mReceiverSpinnerCategory="";

    /*Start welcome activity intent*/
    public static void startActivity(Context context) {
        Intent homeIntent = new Intent(context, ReceiverDetailActivity.class);
        context.startActivity(homeIntent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_receiver_detail);

        initClicklistner();
        initToolbar();
    }

    private void initClicklistner() {
        mBinding.nextBtn.actionBtnParent.setOnClickListener(this);
    }

    private void initToolbar() {
        setInnerviewBackEnabled(true);
    }

    //perform onClick
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.next_btn:
                PaymentMethodActivity.startActivity(mThis);
                break;
        }
    }
}
