package com.road.star.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.road.star.R;
import com.road.star.adapter.CitySpinnerAdapter;
import com.road.star.adapter.StatesSpinnerAdapter;
import com.road.star.api.ApiClient;
import com.road.star.api.ApiInterface;
import com.road.star.base.BaseActivity;
import com.road.star.databinding.ActivityViewProfileBinding;
import com.road.star.models.city.CityData;
import com.road.star.models.city.CityResponse;
import com.road.star.models.state.StatesData;
import com.road.star.models.state.StatesResponse;
import com.road.star.utils.AppUtils;
import com.road.star.utils.Constants;
import com.road.star.utils.Lg;
import com.road.star.utils.NetworkHandler;
import com.road.star.utils.SessionManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewProfileActivity extends BaseActivity {

    ActivityViewProfileBinding mBinding;
    private String mStateId = "";
    private String mCityId = "";
    private String TAG = "ViewProfileActivity";

    /*Start ViewProfile Activity intent*/
    public static void startActivity(Context context) {
        Intent homeIntent = new Intent(context, ViewProfileActivity.class);
        context.startActivity(homeIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_view_profile);
        initToolbar();
        initUI();
        initClickListeners();
    }

    private void initClickListeners() {
       // mBinding.registerBtn.actionBtnParent.setOnClickListener(this);
    }

    private void initToolbar() {
        setHeaderTitle(getString(R.string.my_profile));
        setBackEnabled(true);
        mBinding.screenToolbar.toolbarTitleTv.setTextColor(getResources().getColor(R.color.colorwhite));
        mBinding.screenToolbar.ivBack.setColorFilter(getResources().getColor(R.color.colorwhite));
    }


    private void initUI() {
        //Name
        mBinding.tvFirstName.setText(SessionManager.get().getFirstName());
        mBinding.tvlastName.setText(SessionManager.get().getLastName());
        mBinding.tvEmail.setText(SessionManager.get().getEmail());
        mBinding.etMobile.setText(SessionManager.get().getMobile());
        mBinding.countryPicker.setDefaultCountryUsingNameCode("IN");
        mBinding.etAddress.setText(SessionManager.get().getStateName()+" , "+ SessionManager.get().getCountryName());
        fetchStateList();
    }

    private void setStateSpinnerAdapter(ArrayList<StatesData> arrayList) {
        StatesSpinnerAdapter adapter = new StatesSpinnerAdapter(mThis, R.layout.spinner_dropdown_item, arrayList);
        int statePosition=0;
        for(int i=0;i<arrayList.size();i++){
            if(arrayList.get(i).getState_id().contains(SessionManager.get().getState())){
                statePosition=i;
                break;
            }
        }

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        mBinding.stateSpinner.setAdapter(adapter);
        mBinding.stateSpinner.setSelection(statePosition);
        mBinding.stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mStateId = adapter.getId(position);
                fetchCity();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setCitySpinnerAdapter(ArrayList<CityData> arrayListCity) {
        CitySpinnerAdapter adapter = new CitySpinnerAdapter(mThis, R.layout.spinner_dropdown_item,
                arrayListCity);

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        mBinding.citySpinner.setAdapter(adapter);

        mBinding.citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mCityId = adapter.getId(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mBinding.stateSpinner.setEnabled(false);
        mBinding.citySpinner.setEnabled(false);
    }




    //perform onClick
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.register_btn:
                EditProfileActivity.startActivity(mThis);
                break;
        }
    }

    private void fetchStateList() {
        if (NetworkHandler.isConnected(mThis)) {
            AppUtils.hideKeyboard(mThis);

            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<StatesResponse> call = apiService.getStates(SessionManager.get().getCountryCode());

            call.enqueue(new Callback<StatesResponse>() {
                @Override
                public void onResponse(Call<StatesResponse> call, Response<StatesResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getCode() == 200) {
                            ArrayList<StatesData> arrayList = response.body().getData();
                            setStateSpinnerAdapter(arrayList);

                        } else {
                            AppUtils.showToast(mThis, response.body().getMessage());

                        }

                    } else {
                        showHideProgressDialog(false);
                        AppUtils.showToast(R.string.server_error);
                    }
                }

                @Override
                public void onFailure(Call<StatesResponse> call, Throwable t) {
                    Lg.e(TAG, t.getMessage());
                    AppUtils.showToast(R.string.server_error);
                }
            });
        }
    }


    private void fetchCity() {
        if (NetworkHandler.isConnected(mThis)) {
            AppUtils.hideKeyboard(mThis);

            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<CityResponse> call = apiService.getCity(mStateId);

            call.enqueue(new Callback<CityResponse>() {
                @Override
                public void onResponse(Call<CityResponse> call, Response<CityResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getCode() == 200) {
                            setCitySpinnerAdapter(response.body().getData());

                        } else {
                            AppUtils.showToast(mThis, response.body().getMessage());

                        }

                    } else {
                        AppUtils.showToast(R.string.server_error);
                    }
                }

                @Override
                public void onFailure(Call<CityResponse> call, Throwable t) {
                    Lg.e(TAG, t.getMessage());
                    AppUtils.showToast(R.string.server_error);
                }
            });
        }
    }

}
