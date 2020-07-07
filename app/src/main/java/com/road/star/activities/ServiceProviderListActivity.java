package com.road.star.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.road.star.R;
import com.road.star.adapter.BookingAdapter;
import com.road.star.adapter.ServiceProviderAdapter;
import com.road.star.base.BaseActivity;
import com.road.star.callback.RecyclerItemClickListener;
import com.road.star.databinding.ActivityServiceProviderListBinding;
import com.road.star.models.BookingModel;
import com.road.star.models.ServiceProviderModel;

import java.util.ArrayList;
import java.util.List;

public class ServiceProviderListActivity extends BaseActivity implements RecyclerItemClickListener<ServiceProviderModel> {

    ActivityServiceProviderListBinding mBinding;

    private ServiceProviderAdapter mServiceAdapter;
    private List<ServiceProviderModel> mServiceItemModelList = new ArrayList<>();

    /*Start ServiceProviderList activity intent*/
    public static void startActivity(Context context) {
        Intent homeIntent = new Intent(context, ServiceProviderListActivity.class);
        context.startActivity(homeIntent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_service_provider_list);

        initView();
        initToolbar();
    }

    private void initToolbar() {
        setHeaderTitle(getString(R.string.service_provider));
        setBackEnabled(true);
    }

    private void initView() {
        /*Set refresh listner on list*/
        mBinding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                mBinding.swipeRefresh.setRefreshing(false);
            }
        });


        setUpBookingRecycler();
        bindStaticData();
        setUpBookingAdapter();
    }


    //SetUp Trip Journey Data For RecyclerView
    private void setUpBookingRecycler() {
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(mThis);
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        mBinding.bookingsRv.setLayoutManager(linearLayoutManager1);

    }

    private void bindStaticData() {
        mServiceItemModelList.add(new ServiceProviderModel());
        mServiceItemModelList.add(new ServiceProviderModel());
        mServiceItemModelList.add(new ServiceProviderModel());
        mServiceItemModelList.add(new ServiceProviderModel());
        mServiceItemModelList.add(new ServiceProviderModel());

    }

    //Set Trip Journey data adapter for RecyclerView
    private void setUpBookingAdapter() {
//        mBinding.swipeRefresh.setRefreshing(false);
        if (null == mServiceAdapter) {
            mServiceAdapter = new ServiceProviderAdapter(mThis, mServiceItemModelList, this);
            mBinding.bookingsRv.setAdapter(mServiceAdapter);
        } else {
            mServiceAdapter.setmBooking_itemModelList(mServiceItemModelList);
            mServiceAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(int position, ServiceProviderModel item, View v) {

    }
}
