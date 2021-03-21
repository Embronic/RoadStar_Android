package com.road.star.activities;

import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.road.star.R;
import com.road.star.adapter.CitySpinnerAdapter;
import com.road.star.adapter.StatesSpinnerAdapter;
import com.road.star.api.ApiClient;
import com.road.star.api.ApiInterface;
import com.road.star.base.BaseActivity;
import com.road.star.databinding.ActivityRegisterOneBinding;
import com.road.star.fragments.DashboardFragment;
import com.road.star.models.city.CityData;
import com.road.star.models.city.CityResponse;
import com.road.star.models.registration.RegistrationResponse;
import com.road.star.models.state.StatesData;
import com.road.star.utils.AppUtils;
import com.road.star.utils.Constants;
import com.road.star.utils.Lg;
import com.road.star.utils.NetworkHandler;
import com.road.star.utils.SessionManager;
import com.road.star.utils.ValidUtils;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class RegisterOneActivity extends BaseActivity {

    ActivityRegisterOneBinding mBinding;
    private String mStateId = "";
    private String mCityId = "";
    private String TAG = "RegisterOneActivity";

    /*Start RegisterOne activity intent*/
    public static void startActivity(Context context, ArrayList<StatesData> arrayList) {
        Intent homeIntent = new Intent(context, RegisterOneActivity.class);
        homeIntent.putParcelableArrayListExtra(Constants.STATE_LIST, arrayList);
        context.startActivity(homeIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_register_one);
        initToolbar();
        initView();
        initClickListeners();
        setStateSpinnerAdapter();
    }

    private void initView() {
        mBinding.screenBannerView.screenBannerImageview.setImageDrawable(getResources().getDrawable(R.drawable.register_bg));
    }

    private void setStateSpinnerAdapter() {
        StatesSpinnerAdapter adapter = new StatesSpinnerAdapter(mThis, R.layout.spinner_dropdown_item,
                getIntent().getParcelableArrayListExtra(Constants.STATE_LIST));

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        mBinding.stateSpinner.setAdapter(adapter);

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

    private void fetchCity() {
        if (NetworkHandler.isConnected(mThis)) {
            showHideProgressDialog(true);
            AppUtils.hideKeyboard(mThis);

            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<CityResponse> call = apiService.getCity(mStateId);

            call.enqueue(new Callback<CityResponse>() {
                @Override
                public void onResponse(Call<CityResponse> call, Response<CityResponse> response) {
                    showHideProgressDialog(false);
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
                    showHideProgressDialog(false);
                    Lg.e(TAG, t.getMessage());
                    AppUtils.showToast(R.string.server_error);
                }
            });
        }
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
    }

    private void initToolbar() {
        setHeaderTitle(getString(R.string.register));
        setBackEnabled(true);
    }

    private void initClickListeners() {
        mBinding.viewSignIn.setOnClickListener(this);
        mBinding.registerBtn.actionBtnParent.setOnClickListener(this);
    }

    //perform onClick
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.viewSignIn:
                SignInActivity.startActivity(mThis);
                finishAffinity();
                break;
            case R.id.register_btn:
                if (isAllFieldsValid()) {
                    onButtonClicked();
                }
                break;
        }
    }
    private boolean isAllFieldsValid() {
        if (ValidUtils.isBlank(mBinding.etFirstName)) {
            AppUtils.showToast(R.string.error_first_name);
            return false;
        }

        if (ValidUtils.isBlank(mBinding.etLastName)) {
            AppUtils.showToast(R.string.error_last_name);
            return false;
        }

        if (ValidUtils.isBlank(mBinding.etEmail)) {
            AppUtils.showToast(R.string.error_blank_email);
            return false;
        }


        if (ValidUtils.isBlank(mBinding.etMobile)) {
            AppUtils.showToast(R.string.error_blank_mobile);
            return false;
        }

        if (ValidUtils.isBlank(mBinding.etAddress)) {
            AppUtils.showToast(R.string.error_blank_address);
            return false;
        }

        if (ValidUtils.isBlank(mBinding.etPassword)) {
            AppUtils.showToast(R.string.error_blank_password);
            return false;
        }

        if (mStateId.isEmpty()) {
            AppUtils.showToast(R.string.error_state);
            return false;
        }

        if (mCityId.isEmpty()) {
            AppUtils.showToast(R.string.error_city);
            return false;
        }

        return true;
    }

    private void onButtonClicked() {
        if (NetworkHandler.isConnected(mThis)) {
            showHideProgressDialog(true);

            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

            Call<RegistrationResponse> call = apiService.addUser(
                    mBinding.etFirstName.getText().toString(),
                    mBinding.etLastName.getText().toString(),
                    mBinding.etMobile.getText().toString(),
                    SessionManager.get().getCountryMobileCode(),
                    mBinding.etAddress.getText().toString(),
                    mStateId,
                    mCityId,
                    mBinding.etEmail.getText().toString(),
                    mBinding.etPassword.getText().toString());

            call.enqueue(new Callback<RegistrationResponse>() {
                @Override
                public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                    showHideProgressDialog(false);

                    if (response.isSuccessful()) {
                        if (response.body().getCode() == 200) {
                            AppUtils.showToast(mThis, response.body().getMessage());
                            SignInActivity.startActivity(mThis);
                            finishAffinity();
                        } else {
                            AppUtils.showToast(mThis, response.body().getMessage());

                        }
                    } else {
                        AppUtils.showToast(R.string.server_error);
                    }
                }

                @Override
                public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                    Lg.e(TAG, t.getMessage());
                    showHideProgressDialog(false);
                    AppUtils.showToast(R.string.server_error);
                }
            });
        }
    }


}
