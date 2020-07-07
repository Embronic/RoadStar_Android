package com.road.star.activities;

import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.road.star.R;
import com.road.star.base.BaseActivity;
import com.road.star.databinding.ActivityStartBookingBinding;

import static com.road.star.utils.Constants.VEHICLE_TYPE_AIR;
import static com.road.star.utils.Constants.VEHICLE_TYPE_CAR;
import static com.road.star.utils.Constants.VEHICLE_TYPE_HAUL_TRUCK;
import static com.road.star.utils.Constants.VEHICLE_TYPE_PICKUP_TRUCK;
import static com.road.star.utils.Constants.VEHICLE_TYPE_SHIP;
import static com.road.star.utils.Constants.VEHICLE_TYPE_TRICYCLE;

public class StartBookingActivity extends BaseActivity {

    ActivityStartBookingBinding mBinding;
    private String mVehicleType = "";

    /*Start home activity intent*/
    public static void startActivity(Context context, String deliveryType) {
        Intent homeIntent = new Intent(context, StartBookingActivity.class);
        homeIntent.putExtra("type", deliveryType);
        context.startActivity(homeIntent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_start_booking);
        getBundle();
        initClicklistners();
        initToolbar();
        initView();

    }

    /*get data bundle*/
    private void getBundle() {
        if (getIntent().getExtras() != null) {
            mVehicleType = getIntent().getStringExtra("type");
        }
    }

    private void initClicklistners() {
        mBinding.cycleDeliveryView.setOnClickListener(this);
        mBinding.carDeliveryView.setOnClickListener(this);
        mBinding.truckDeliveryView.setOnClickListener(this);
        mBinding.bulkLongDeliveryView.setOnClickListener(this);
    }

    private void initView() {
        if (!mVehicleType.equalsIgnoreCase("locale_delivery_view")) {
            mBinding.pickupTruckLin.setVisibility(View.INVISIBLE);
            mBinding.haulTruckLin.setVisibility(View.INVISIBLE);
            mBinding.cycleDeliveryView.setBackground(getResources().getDrawable(R.mipmap.ic_launcher));
            mBinding.carDeliveryView.setBackground(getResources().getDrawable(R.mipmap.ic_launcher));
            mBinding.airLbl.setText("Air\n");
            mBinding.shipLbl.setText("Ship\n");
        } else {
            mBinding.pickupTruckLin.setVisibility(View.VISIBLE);
            mBinding.haulTruckLin.setVisibility(View.VISIBLE);
            mBinding.airLbl.setText("All Cycle\n");
            mBinding.airLbl.setText("Car\n");
            mBinding.cycleDeliveryView.setBackground(getResources().getDrawable(R.drawable.ic_tricycle));
            mBinding.carDeliveryView.setBackground(getResources().getDrawable(R.drawable.ic_4_dor_car));
            mBinding.truckDeliveryView.setBackground(getResources().getDrawable(R.drawable.ic_pick_up_truck));
            mBinding.bulkLongDeliveryView.setBackground(getResources().getDrawable(R.drawable.ic_long_haul_truck));
        }
        /*mBinding.cycleDeliveryView.mainImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_tricycle));
        mBinding.carDeliveryView.mainImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_4_dor_car));
        mBinding.truckDeliveryView.mainImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_pick_up_truck));
        mBinding.bulkLongDeliveryView.mainImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_long_haul_truck));*/
    }

    private void initToolbar() {
        setInnerviewBackEnabled(true);
    }


    //perform onClick
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.cycle_delivery_view:
                if (!mVehicleType.equalsIgnoreCase("locale_delivery_view")) {
                    SelectVehicleActivity.startActivity(mThis, VEHICLE_TYPE_AIR);
                } else
                    SelectVehicleActivity.startActivity(mThis, VEHICLE_TYPE_TRICYCLE);
                break;
            case R.id.car_delivery_view:
                if (!mVehicleType.equalsIgnoreCase("locale_delivery_view")) {
                    SelectVehicleActivity.startActivity(mThis, VEHICLE_TYPE_SHIP);
                } else
                    SelectVehicleActivity.startActivity(mThis, VEHICLE_TYPE_CAR);
                break;
            case R.id.truck_delivery_view:

                    SelectVehicleActivity.startActivity(mThis, VEHICLE_TYPE_PICKUP_TRUCK);
                break;
            case R.id.bulk_long_delivery_view:

                    SelectVehicleActivity.startActivity(mThis, VEHICLE_TYPE_HAUL_TRUCK);
                break;
        }
    }
}
