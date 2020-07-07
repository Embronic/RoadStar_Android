package com.road.star.fragments;

import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.road.star.R;
import com.road.star.adapter.BookingAdapter;
import com.road.star.base.BaseFragment;
import com.road.star.callback.RecyclerItemClickListener;
import com.road.star.databinding.FragmentBookingsBinding;
import com.road.star.models.BookingModel;
import java.util.ArrayList;
import java.util.List;


public class BookingsFragment extends BaseFragment implements RecyclerItemClickListener<BookingModel> {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentBookingsBinding mBinding;
    private String mHeaderTitle = "";
    private BookingAdapter mBookingAdapter;
    private List<BookingModel> mBookingItemModelList = new ArrayList<>();


    public BookingsFragment() {
        // Required empty public constructor
    }


    public static BookingsFragment  newInstance(String headerTitle, String param2) {
        BookingsFragment fragment = new BookingsFragment();
        Bundle args = new Bundle();
        args.putString("HeaderTitle", headerTitle);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_bookings, container, false);

        getBundle();
        initClicklistners();
        initView();
        initToolbar();

        return mBinding.getRoot();
    }

    private void getBundle() {
        if (getArguments() != null) {
            mHeaderTitle = getArguments().getString("HeaderTitle");
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private void initToolbar() {
        setBackEnabled(false, mBinding.getRoot());
        setHeaderTitle(mHeaderTitle, mBinding.getRoot());
    }


    private void initClicklistners() {
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
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity());
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        mBinding.bookingsRv.setLayoutManager(linearLayoutManager1);

    }

    private void bindStaticData() {
        mBookingItemModelList.add(new BookingModel());
        mBookingItemModelList.add(new BookingModel());
        mBookingItemModelList.add(new BookingModel());
        mBookingItemModelList.add(new BookingModel());
        mBookingItemModelList.add(new BookingModel());

    }

    //Set Trip Journey data adapter for RecyclerView
    private void setUpBookingAdapter() {
//        mBinding.swipeRefresh.setRefreshing(false);
        if (null == mBookingAdapter) {
            mBookingAdapter = new BookingAdapter(getActivity(), mBookingItemModelList, this);
            mBinding.bookingsRv.setAdapter(mBookingAdapter);
        } else {
            mBookingAdapter.setmBooking_itemModelList(mBookingItemModelList);
            mBookingAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(int position, BookingModel item, View v) {

    }

    //perform onClick
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.next_btn:
                break;

        }
    }
}
