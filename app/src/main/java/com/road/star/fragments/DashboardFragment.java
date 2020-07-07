package com.road.star.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import com.road.star.R;
import com.road.star.activities.StartBookingActivity;
import com.road.star.base.BaseFragment;
import com.road.star.databinding.FragmentDashboardBinding;


public class DashboardFragment extends BaseFragment {
    FragmentDashboardBinding mBinding;


    public DashboardFragment() {
        // Required empty public constructor
    }

    public static DashboardFragment newInstance() {
        DashboardFragment fragment = new DashboardFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false);

        initClicklistners();
        initToolbar();

        return mBinding.getRoot();
    }

    private void initToolbar() {
        setInnerviewBackEnabled(true,mBinding.getRoot());
    }


    private void initClicklistners() {
        mBinding.localeDeliveryView.setOnClickListener(this);
        mBinding.airSeaDeliveryView.setOnClickListener(this);
    }



   /* private void initView() {
        mBinding.localeDeliveryView.imageView.setImageDrawable(getResources().getDrawable(R.drawable.local_delivery));
        mBinding.airSeaDeliveryView.imageView.setImageDrawable(getResources().getDrawable(R.drawable.international));
    }*/


    //perform onClick
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.locale_delivery_view:
                StartBookingActivity.startActivity(getActivity(),"locale_delivery_view");
                break;
            case R.id.air_sea_delivery_view:
                StartBookingActivity.startActivity(getActivity(),"air_sea_delivery_view");
                break;
        }
    }
}
