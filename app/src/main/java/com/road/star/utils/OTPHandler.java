package com.road.star.utils;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.road.star.R;
import com.road.star.base.TextPasteListener;
import com.road.star.databinding.OtpViewBinding;

public class OTPHandler {

    private static OtpViewBinding mBinding;


    public static void setRefrence(OtpViewBinding binding) {
        mBinding = binding;
    }

    public static void setFocus(EditText editText) {
        editText.requestFocus();
        editText.setSelection(editText.getText().length());

    }

    public static class TextPasteCallback implements TextPasteListener {
        private View view;

        public TextPasteCallback(View view) {
            this.view = view;
        }

        @Override
        public void onTextPaste(String text) {
            if (mBinding == null)
                return;
            switch (view.getId()) {
                case R.id.etOtp1:
                    for (int i = 0; i < text.length(); i++) {
                        switch (i) {
                            case 0:
                                mBinding.etOtp1.setText(text.substring(i, i + 1));
                                break;
                            case 1:
                                mBinding.etOtp2.setText(text.substring(i, i + 1));
                                break;
                            case 2:
                                mBinding.etOtp3.setText(text.substring(i, i + 1));
                                break;
                            case 3:
                                mBinding.etOtp4.setText(text.substring(i, i + 1));
                                break;
                        }
                    }
                    break;
            }
        }
    }


    public static class GenericTextWatcher implements TextWatcher {
        private View view;

        public GenericTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (mBinding == null)
                return;
            String text = editable.toString();
            checkForOtpCompletion();
            switch (view.getId()) {

                case R.id.etOtp1:
                    if (text.length() == 1)
                        setFocus(mBinding.etOtp2);
                    break;
                case R.id.etOtp2:
                    if (text.length() == 1)
                        setFocus(mBinding.etOtp3);
                    else if (text.length() == 0)
                        setFocus(mBinding.etOtp1);
                    break;
                case R.id.etOtp3:
                    if (text.length() == 1)
                        setFocus(mBinding.etOtp4);
                    else if (text.length() == 0)
                        setFocus(mBinding.etOtp2);
                    break;
                case R.id.etOtp4:
                    if (text.length() == 1) {
                        //continueBtn.actionBtnParent.performClick();
                    } else
                        setFocus(mBinding.etOtp3);
                    break;
            }

        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//            otpColorBox();
        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

        }

        public void checkForOtpCompletion() {
            if (TextUtils.isEmpty(mBinding.etOtp1.getText()) || TextUtils.isEmpty(mBinding.etOtp2.getText()) ||
                    TextUtils.isEmpty(mBinding.etOtp3.getText()) || TextUtils.isEmpty(mBinding.etOtp4.getText())) {
                //continueBtn.actionBtnParent.setBackground(mActivity.getResources().getDrawable(R.drawable.shape_inactive_gray_color_rounded_corner));
            } else {
                //continueBtn.actionBtnParent.setBackground(mActivity.getResources().getDrawable(R.drawable.shape_green_color_rounded_corner));
            }
        }
    }
}
