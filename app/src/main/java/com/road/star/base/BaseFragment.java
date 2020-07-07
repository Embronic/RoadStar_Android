package com.road.star.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.road.star.R;
import com.road.star.base.BaseActivity;
import com.road.star.listners.BaseListener;
import com.road.star.utils.AppUtils;
import com.road.star.utils.DialogUtil;

public class BaseFragment extends Fragment implements BaseListener, View.OnClickListener {

    public BaseActivity context;
    private ProgressDialog progressDialog;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = (BaseActivity) getActivity();
    }

    @Override
    public void showHideProgressDialog(boolean isShow) {
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                if (!isShow) {
                    progressDialog.dismiss();
                    progressDialog = null;
                } else
                    return;
            } else {
                if (isShow)
                    progressDialog.show();
            }
        } else {
            progressDialog = DialogUtil.getProgressDialog(getActivity());
            showHideProgressDialog(isShow);

        }
    }

    @Override
    public void isTokenRefreshed(boolean iShow) {
    }


    @Override
    public void onClick(View view) {
        AppUtils.hideKeyboard(requireActivity());
        AppUtils.preventMultiTab(requireActivity(), view);
        switch (view.getId()) {
            case R.id.iv_back:
                requireActivity().finish();
                break;
            case R.id.innerView_back_iv:
                requireActivity().finish();
                break;
        }
    }

    public void setHeaderTitle(String title,View view) {
        TextView titleTv = view.findViewById(R.id.toolbar_title_tv);
        if (titleTv != null) {
            titleTv.setVisibility(View.VISIBLE);
            titleTv.setText(title);
        }
    }

    public void setInnerviewBackEnabled(boolean isBackEnabled,View view) {
        ImageView backArrow = view.findViewById(R.id.innerView_back_iv);
        if (backArrow != null) {
            if (isBackEnabled) {
                backArrow.setVisibility(View.VISIBLE);
                backArrow.setOnClickListener(this);
            } else {
                backArrow.setVisibility(View.GONE);
            }
        }
    }


    public void setBackEnabled(boolean isBackEnabled,View view) {
        ImageView backArrow = view.findViewById(R.id.iv_back);
        if (backArrow != null) {
            if (isBackEnabled) {
                backArrow.setVisibility(View.VISIBLE);
                backArrow.setOnClickListener(this);
            } else {
                backArrow.setVisibility(View.GONE);
            }
        }
    }

}
