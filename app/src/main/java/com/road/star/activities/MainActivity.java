package com.road.star.activities;

import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.road.star.R;
import com.road.star.base.BaseActivity;
import com.road.star.callback.DialogCallback;
import com.road.star.databinding.ActivityMainBinding;
import com.road.star.fragments.BookingsFragment;
import com.road.star.fragments.ClaimInitiateFragment;
import com.road.star.fragments.DashboardFragment;
import com.road.star.fragments.SupportFragment;
import com.road.star.fragments.YourPackageFrag;
import com.road.star.utils.DialogUtil;
import com.road.star.utils.SessionManager;

import butterknife.ButterKnife;

import static com.road.star.utils.AppUtils.showToast;

public class MainActivity extends BaseActivity implements DialogCallback {

    private Context context;
    ActivityMainBinding mBinding;

    /*Start home activity intent*/
    public static void startActivity(Context context) {
        Intent homeIntent = new Intent(context, MainActivity.class);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(homeIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;

        initToolbar();
        initClicklistners();


        addFragment(mBinding.appBarMain.contentMain.framFrag.getId(),
                new DashboardFragment(),
                "DashBoardFrag",
                "");
    }

    private void initClicklistners() {
        mBinding.appBarMain.layoutAppBar.ivMenu.setOnClickListener(this);
        mBinding.navigationView.getHeaderView(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewProfileActivity.startActivity(mThis);
                closeDrawer();
            }
        });
    }

    private void initToolbar() {
        mBinding.appBarMain.layoutAppBar.ivBack.setVisibility(View.GONE);
        mBinding.appBarMain.layoutAppBar.toolbarTitleTv.setText(getString(R.string.app_name));
        mBinding.appBarMain.layoutAppBar.ivMenu.setVisibility(View.VISIBLE);
        mBinding.appBarMain.layoutAppBar.ivMenu.setOnClickListener(this);
        TextView tvName= mBinding.navigationView.getHeaderView(0).findViewById(R.id.tvName);
        TextView tvEmail=mBinding.navigationView.getHeaderView(0).findViewById(R.id.tvEmail);
        tvName.setText(SessionManager.get().getFirstName()+" "+ SessionManager.get().getLastName());
        tvEmail.setText(SessionManager.get().getEmail());
        setUpNavigationView();
    }


    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        mBinding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            // This method will trigger on item Click of navigation menu
            @SuppressLint("WrongConstant")
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    case R.id.navigation_claim:
                        claimNavigation();
                        closeDrawer();
                        break;
                    case R.id.navigation_your_packege:
                        YouPackageNavigation();
                        closeDrawer();
                        break;

                    case R.id.navigation_booking_history:
                        BookingHistoryNavigation();
                        closeDrawer();
                        break;
                    case R.id.navigation_available_history:
                        AvailableBookingHistoryNavigation();
                        closeDrawer();
                        break;
                    case R.id.navigation_payment_method:
                        closeDrawer();
                        break;
                    case R.id.navigation_support:
                        SupportNavigation();
                        closeDrawer();
                        break;
                    case R.id.navigation_logout:
                        DialogUtil.showLogoutAlertDialog(mThis, getSupportFragmentManager(), MainActivity.this);
                        break;
                    default:
                }
                return true;
            }
        });
    }

    @SuppressLint("WrongConstant")
    private void closeDrawer() {
        mBinding.drawerLayout.closeDrawer(Gravity.START);
    }

    private void claimNavigation() {
        addFragment(mBinding.appBarMain.contentMain.framFrag.getId(),
                new ClaimInitiateFragment(),
                "ClaimInitiate",
                "");
    }


    private void YouPackageNavigation() {
        addFragment(mBinding.appBarMain.contentMain.framFrag.getId(),
                new YourPackageFrag(),
                "YourPackage",
                "");
    }


    private void AvailableBookingHistoryNavigation() {
        addFragment(mBinding.appBarMain.contentMain.framFrag.getId(),
                new BookingsFragment(),
                "AvailableBookingHistory",
                "");
    }


    private void BookingHistoryNavigation() {
        addFragment(mBinding.appBarMain.contentMain.framFrag.getId(),
                new BookingsFragment(),
                "BookingHistory",
                "");
    }


    private void SupportNavigation() {
        addFragment(mBinding.appBarMain.contentMain.framFrag.getId(),
                new SupportFragment(),
                "SupportFragment",
                "");
    }

    //perform onClick
    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_menu:
                if (!mBinding.drawerLayout.isDrawerOpen(Gravity.START))
                    mBinding.drawerLayout.openDrawer(Gravity.START);
                else mBinding.drawerLayout.closeDrawer(Gravity.END);
                break;
        }
    }

    @Override
    public void okPressed() {
        WelcomeActivity.startActivity(this);
        finish();
        closeDrawer();
    }

    @Override
    public void cancelPressed() {
        closeDrawer();
    }



    /*private void getToken() {
        showHideProgressDialog(true);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<AccessTokenResponse> call = apiService.getToken("1234567890");
        call.enqueue(new Callback<AccessTokenResponse>() {
            @Override
            public void onResponse(@NonNull Call<AccessTokenResponse> call, @NonNull Response<AccessTokenResponse> response) {
                if (response.isSuccessful()) {
                    showHideProgressDialog(false);
                }
            }

            @Override
            public void onFailure(@NonNull Call<AccessTokenResponse> call, @NonNull Throwable t) {
                showHideProgressDialog(false);
                showToast(context, "Unable to login");
            }
        });
    }*/
}
