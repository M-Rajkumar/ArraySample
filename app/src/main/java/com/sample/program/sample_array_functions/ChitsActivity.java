package com.sample.program.sample_array_functions;

import android.app.Activity;
import android.app.Service;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.sample.program.sample_array_functions.Custom.MyEditTextLight;

public class ChitsActivity extends AppCompatActivity implements View.OnFocusChangeListener, View.OnKeyListener, TextWatcher {

    MyEditTextLight edt_firstdigit, edt_seconddigit, edt_thirddigit, edt_fourthdigit;
    EditText      mPinHiddenEditText;
    Typeface typeface;

    Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_withcode);
        mActivity=this;
        initmethod();
    }

    private void initmethod() {
        edt_firstdigit = (MyEditTextLight) findViewById(R.id.edt_firstdigit);
        edt_seconddigit = (MyEditTextLight) findViewById(R.id.edt_seconddigit);
        edt_thirddigit = (MyEditTextLight) findViewById(R.id.edt_thirddigit);
        edt_fourthdigit = (MyEditTextLight) findViewById(R.id.edt_fourthdigit);
        mPinHiddenEditText = (EditText) findViewById(R.id.pin_hidden_edittext);

        edt_firstdigit.setTypeface(typeface);
        edt_seconddigit.setTypeface(typeface);
        edt_thirddigit.setTypeface(typeface);
        edt_fourthdigit.setTypeface(typeface);

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        final int id = v.getId();
        switch (id) {
            case R.id.edt_firstdigit:
                if (hasFocus) {
                    setFocus(mPinHiddenEditText);
                    showSoftKeyboard(mPinHiddenEditText);
                }
                break;

            case R.id.edt_seconddigit:
                if (hasFocus) {
                    setFocus(mPinHiddenEditText);
                    showSoftKeyboard(mPinHiddenEditText);

                }
                break;

            case R.id.edt_thirddigit:
                if (hasFocus) {
                    setFocus(mPinHiddenEditText);
                    showSoftKeyboard(mPinHiddenEditText);

                }
                break;

            case R.id.edt_fourthdigit:
                if (hasFocus) {
                    setFocus(mPinHiddenEditText);
                    showSoftKeyboard(mPinHiddenEditText);

                }
                break;


            default:
                break;
        }

    }

    public static void setFocus(EditText editText) {
        if (editText == null)
            return;

        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);

        editText.requestFocus();
    }

    public void showSoftKeyboard(EditText editText) {
        if (editText == null)
            return;
        InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Service.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, 0);

        editText.setImeOptions(EditorInfo.IME_ACTION_DONE);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
