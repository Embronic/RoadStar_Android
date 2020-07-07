package com.road.star.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;

import com.road.star.R;
import com.road.star.adapter.CustomSpinnerAdapter;
import com.road.star.base.BaseActivity;
import com.road.star.databinding.ActivityModifyRequestBinding;
import com.road.star.models.CustomSpinnerModel;
import com.road.star.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

public class ModifyRequestActivity extends BaseActivity {

    ActivityModifyRequestBinding mBinding;

    private String mSelectedCategory, mProductTypeCategory;

    /*Start welcome activity intent*/
    public static void startActivity(Context context) {
        Intent homeIntent = new Intent(context, ModifyRequestActivity.class);
        context.startActivity(homeIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_modify_request);

        init();
    }

    private void init() {
        initToolbar();
        initClicklistner();
        setCategorySpinnerAdapter();
        setProductTypeSpinnerAdapter();
    }


    private void initToolbar() {
        setHeaderTitle(getString(R.string.modify_request));
        setBackEnabled(true);

    }

    private void initClicklistner() {
        mBinding.nextBtn.actionBtnParent.setOnClickListener(this);
    }

    private void setCategorySpinnerAdapter() {
        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(mThis, R.layout.spinner_dropdown_item, getCategorySpinnerData());

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        mBinding.spinner.setAdapter(adapter);

        mBinding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSelectedCategory = ((TextView) view.findViewById(R.id.spinnertitle)).getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void setProductTypeSpinnerAdapter() {
        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(mThis, R.layout.spinner_dropdown_item, getProductTypeSpinnerData());

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        mBinding.productTypeSpinner.setAdapter(adapter);

        mBinding.productTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mProductTypeCategory = ((TextView) view.findViewById(R.id.spinnertitle)).getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //get subscription spinner data
    private List<CustomSpinnerModel> getCategorySpinnerData() {
        List<CustomSpinnerModel> subscriptionList = new ArrayList<>();
        for (int i = 0; i < AppUtils.getCategorySpinnerData(mThis).length; i++) {
            CustomSpinnerModel model = new CustomSpinnerModel();
            model.setTitle(AppUtils.getCategorySpinnerData(mThis)[i]);
            subscriptionList.add(model);
        }
        return subscriptionList;
    }

    //get subscription spinner data
    private List<CustomSpinnerModel> getProductTypeSpinnerData() {
        List<CustomSpinnerModel> subscriptionList = new ArrayList<>();
        for (int i = 0; i < AppUtils.getProductTypeSpinnerData(mThis).length; i++) {
            CustomSpinnerModel model = new CustomSpinnerModel();
            model.setTitle(AppUtils.getProductTypeSpinnerData(mThis)[i]);
            subscriptionList.add(model);
        }
        return subscriptionList;
    }

    //perform onClick
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.next_btn:
                LiveTrackingActivity.startActivity(mThis, "");
                break;
        }
    }

}
