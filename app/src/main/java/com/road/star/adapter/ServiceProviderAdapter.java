package com.road.star.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.road.star.R;
import com.road.star.callback.RecyclerItemClickListener;
import com.road.star.databinding.BookingsChilviewBinding;
import com.road.star.databinding.ServiceProviderChilviewBinding;
import com.road.star.models.BookingModel;
import com.road.star.models.ServiceProviderModel;

import java.util.List;

public class ServiceProviderAdapter extends RecyclerView.Adapter<ServiceProviderAdapter.ServiceHolder> {

    private List<ServiceProviderModel> mTripJourney_itemModelList;
    private final Context mContext;
    private RecyclerItemClickListener<ServiceProviderModel> mCallback;

    public ServiceProviderAdapter(Context context, List<ServiceProviderModel> data, RecyclerItemClickListener<ServiceProviderModel> callback) {
        mTripJourney_itemModelList = data;
        this.mContext = context;
        this.mCallback = callback;
    }


    //get list
    public void setmBooking_itemModelList(List<ServiceProviderModel> mTripJourney_itemModelList) {
        this.mTripJourney_itemModelList = mTripJourney_itemModelList;
    }


    //set list
    @Override
    public ServiceProviderAdapter.ServiceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ServiceProviderChilviewBinding serviceProviderChilviewBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.service_provider_chilview, parent, false);
        return new ServiceProviderAdapter.ServiceHolder(serviceProviderChilviewBinding);
    }

    /* binds view according to position in holder class*/
    @Override
    public void onBindViewHolder(ServiceProviderAdapter.ServiceHolder holder, int position) {
        holder.bindRow(mTripJourney_itemModelList.get(position), position);
    }

    /*get item count*/
    @Override
    public int getItemCount() {
        return mTripJourney_itemModelList.size();
    }

    /*set data in view*/
    public class ServiceHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ServiceProviderChilviewBinding itemViewBinding;

        public ServiceHolder(ServiceProviderChilviewBinding itemView) {
            super(itemView.getRoot());
            this.itemViewBinding = itemView;
        }

        public void bindRow(ServiceProviderModel bean, final int position) {

        }

        /*perform click*/
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.mainLin:
                    if (null != mCallback)
                        mCallback.onItemClick(getAdapterPosition(), mTripJourney_itemModelList.get(getAdapterPosition()), itemViewBinding.getRoot());
                    break;
            }
        }
    }
}
