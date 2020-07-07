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
import com.road.star.models.BookingModel;
import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.TripJourneyHolder> {

    private List<BookingModel> mTripJourney_itemModelList;
    private final Context mContext;
    private RecyclerItemClickListener<BookingModel> mCallback;
    private LinearLayoutManager linearLayoutManager = null;
    private String  mDelayDeparture = "", mDelayArrival = "";

    /*constructor  for setting travel option adapter
     * data : bind list of available transport medium
     * callback : item clicklistner callback
     * */
    public BookingAdapter(Context context, List<BookingModel> data, RecyclerItemClickListener<BookingModel> callback) {
        mTripJourney_itemModelList = data;
        this.mContext = context;
        this.mCallback = callback;
    }


    //get jorney plan list
    public void setmBooking_itemModelList(List<BookingModel> mTripJourney_itemModelList) {
        this.mTripJourney_itemModelList = mTripJourney_itemModelList;
    }


    //set jorney plan list
    @Override
    public BookingAdapter.TripJourneyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BookingsChilviewBinding tripJourneyChilviewBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.bookings_chilview, parent, false);
        return new BookingAdapter.TripJourneyHolder(tripJourneyChilviewBinding);
    }

    /* binds view according to position in holder class*/
    @Override
    public void onBindViewHolder(BookingAdapter.TripJourneyHolder holder, int position) {
        holder.bindRow(mTripJourney_itemModelList.get(position), position);
    }

    /*get item count*/
    @Override
    public int getItemCount() {
        return mTripJourney_itemModelList.size();
    }

    /*set data in view*/
    public class TripJourneyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final BookingsChilviewBinding itemViewBinding;

        public TripJourneyHolder(BookingsChilviewBinding itemView) {
            super(itemView.getRoot());
            this.itemViewBinding = itemView;
        }

        public void bindRow(BookingModel bean, final int position) {

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