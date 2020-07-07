package com.road.star.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.road.star.R;
import com.road.star.base.BaseActivity;
import com.road.star.databinding.ActivitySelectVehicleBinding;

import static com.road.star.utils.Constants.VEHICLE_TYPE_AIR;
import static com.road.star.utils.Constants.VEHICLE_TYPE_CAR;
import static com.road.star.utils.Constants.VEHICLE_TYPE_HAUL_TRUCK;
import static com.road.star.utils.Constants.VEHICLE_TYPE_PICKUP_TRUCK;
import static com.road.star.utils.Constants.VEHICLE_TYPE_SHIP;
import static com.road.star.utils.Constants.VEHICLE_TYPE_TRICYCLE;

public class SelectVehicleActivity extends BaseActivity {

    ActivitySelectVehicleBinding mBinding;
    private String mVehicleType = "";

    /*Start home activity intent*/
    public static void startActivity(Context context, String vehicleType) {
        Intent homeIntent = new Intent(context, SelectVehicleActivity.class);
        homeIntent.putExtra("vehicle_type", vehicleType);
        context.startActivity(homeIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_select_vehicle);

        initClicklistner();
        initToolbar();
        getBundle();
        initHeaderView();
        initView();
    }

    private void initView() {
    }

    private void initToolbar() {
        setInnerviewBackEnabled(true);
    }

    private void initHeaderView() {
        if (mVehicleType.equalsIgnoreCase(VEHICLE_TYPE_TRICYCLE)) {
            mBinding.vehicleToolbarCardView.setImageDrawable(getResources().getDrawable(R.drawable.ic_tricycle));
            mBinding.vehicleTypeTv.setText(getString(R.string.all_cycle));
        }else if (mVehicleType.equalsIgnoreCase(VEHICLE_TYPE_CAR)) {
            mBinding.vehicleToolbarCardView.setImageDrawable(getResources().getDrawable(R.drawable.ic_4_dor_car));
            mBinding.vehicleTypeTv.setText(getString(R.string.car));
        } else if (mVehicleType.equalsIgnoreCase(VEHICLE_TYPE_PICKUP_TRUCK)) {
            mBinding.vehicleToolbarCardView.setImageDrawable(getResources().getDrawable(R.drawable.ic_pick_up_truck));
            mBinding.vehicleTypeTv.setText(getString(R.string.pickup_truck_header));
        }else if (mVehicleType.equalsIgnoreCase(VEHICLE_TYPE_HAUL_TRUCK)) {
            mBinding.vehicleToolbarCardView.setImageDrawable(getResources().getDrawable(R.drawable.ic_long_haul_truck));
            mBinding.vehicleTypeTv.setText(getString(R.string.bulk_long_header));
        }else if(mVehicleType.equalsIgnoreCase(VEHICLE_TYPE_AIR)){
            mBinding.vehicleToolbarCardView.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
            mBinding.vehicleTypeTv.setText("Air");
        }
        else if(mVehicleType.equalsIgnoreCase(VEHICLE_TYPE_SHIP)){
            mBinding.vehicleToolbarCardView.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
            mBinding.vehicleTypeTv.setText("Ship");
        }
    }

    /*get data bundle*/
    private void getBundle() {
        if (getIntent().getExtras() != null) {
            mVehicleType = getIntent().getStringExtra("vehicle_type");
        }
    }

    private void initClicklistner() {
        mBinding.nextBtn.actionBtnParent.setOnClickListener(this);
    }

    //perform onClick
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.next_btn:
                ProductDetailActivity.startActivity(mThis);
                break;
        }
    }
}
