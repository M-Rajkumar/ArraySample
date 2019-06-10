package com.sample.program.sample_array_functions.Activity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.sample.program.sample_array_functions.Constants.StringConstants;
import com.sample.program.sample_array_functions.Custom.MyEditTextLight;
import com.sample.program.sample_array_functions.Custom.MyTextViewBold;
import com.sample.program.sample_array_functions.Custom.MyTextViewRegular;
import com.sample.program.sample_array_functions.R;
import com.sample.program.sample_array_functions.Utils.CommonUtils;

import java.util.Objects;

public class ChangePasswordActivity extends BaseActivity implements View.OnClickListener, View.OnFocusChangeListener {


    String TAG = "ChangePasswordActivity";
    ImageView back;
    MyEditTextLight pass, newpassword, newpassword2;
    String passtxt, newpasstxt, newpasstxt2;
    MyTextViewBold changepassword;
    String oldPassword;
    String type="";
    MyTextViewRegular passhead;
    ImageView eye_newpassword, eye_confirm_password, eye_currentpassword;
    boolean eyevisible_current = false;
    boolean eyevisible_newpassword = false;
    boolean eyevisible_confirmpassword = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initview();
        listeners();

        /*fd
        * My brother was admited in aristo hospital, so i need 2hours permission (2pm - 4pm) to visit him.
        *
        *
        *
        * */
    }

    private void initview() {

        oldPassword=sharedPreferences.getString(StringConstants.PREF_PASSCODE,"");

        back = findViewById(R.id.back);
        pass = findViewById(R.id.password);
        passhead = findViewById(R.id.passhead);
        newpassword = findViewById(R.id.newpassword);
        newpassword2 = findViewById(R.id.newpassword2);
        changepassword = findViewById(R.id.changepassword);
        eye_newpassword = findViewById(R.id.eye_newpassword);
        eye_confirm_password = findViewById(R.id.eye_confirm_password);
        eye_currentpassword = findViewById(R.id.eye_currentpassword);
    }
    private void listeners() {
        back.setOnClickListener(this);
        changepassword.setOnClickListener(this);
        pass.setOnFocusChangeListener(this);
        newpassword.setOnFocusChangeListener(this);
        newpassword2.setOnFocusChangeListener(this);
        newpassword2.setOnFocusChangeListener(this);
        eye_newpassword.setOnClickListener(this);
        eye_confirm_password.setOnClickListener(this);
        eye_currentpassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.back:
                //EventBus.getDefault().post(new MainProceed("login"));
                finish();
                break;
            case R.id.changepassword:
                checkvalues();
                break;
            case R.id.eye_currentpassword:
                eyefuntion_currentpassword();
                break;
            case R.id.eye_newpassword:
                eyefuntion_newpassword();
                break;
            case R.id.eye_confirm_password:
                eyefuntion_confirmpassword();
                break;

        }

    }

    private void eyefuntion_currentpassword() {

        if (!eyevisible_current) {
            eyevisible_current = true;

            eye_currentpassword.setImageResource(R.drawable.visible);
            enableInputVisiblePassword(pass);
        } else {
            eyevisible_current = false;

            eye_currentpassword.setImageResource(R.drawable.invisible);
            enableInputHiddenPassword(pass);

        }


    }

    private void eyefuntion_newpassword() {

        if (!eyevisible_newpassword) {
            eyevisible_newpassword = true;

            eye_newpassword.setImageResource(R.drawable.visible);

            enableInputVisiblePassword(newpassword);
        } else {
            eyevisible_newpassword = false;
            eye_newpassword.setImageResource(R.drawable.invisible);

            enableInputHiddenPassword(newpassword);

        }


    }

    private void eyefuntion_confirmpassword() {

        if (!eyevisible_confirmpassword) {
            eyevisible_confirmpassword = true;

            eye_confirm_password.setImageResource(R.drawable.visible);
            enableInputVisiblePassword(newpassword2);
        } else {
            eyevisible_confirmpassword = false;

            eye_confirm_password.setImageResource(R.drawable.invisible);
            enableInputHiddenPassword(newpassword2);

        }


    }



    private void checkvalues() {

        passtxt=pass.getText().toString();

        //passtxt = Objects.requireNonNull(pass.getText()).toString();
        newpasstxt = Objects.requireNonNull(newpassword.getText()).toString();
        newpasstxt2 = Objects.requireNonNull(newpassword2.getText()).toString();

        if (!type.equals("force")) {


            if (!passtxt.equals("") && !newpasstxt.equals("") && !newpasstxt2.equals("")) {

                if (newpasstxt.length() > 3 && newpasstxt2.length() > 3) {
                    if (newpasstxt.equals(newpasstxt2)) {

                        savepwd();
                    } else {
                        Toast.makeText(this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(this, "Password require minimum 4 characters", Toast.LENGTH_SHORT).show();
                }
            } else {

                if (passtxt.equals("")) {
                    pass.setBackgroundResource(R.drawable.curvedred);

                } else if (newpasstxt.equals("")) {
                    newpassword.setBackgroundResource(R.drawable.curvedred);

                } else if (newpasstxt2.equals("")) {
                    newpassword2.setBackgroundResource(R.drawable.curvedred);
                }

            }


        } else {


            if (!newpasstxt.equals("") && !newpasstxt2.equals("")) {

                if (newpasstxt.length() > 3 && newpasstxt2.length() > 3) {
                    if (newpasstxt.equals(newpasstxt2)) {

                        savepwd();
                    } else {
                        Toast.makeText(this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(this, "Password require minimum 4 characters", Toast.LENGTH_SHORT).show();
                }
            } else {

                if (newpasstxt.equals("")) {
                    newpassword.setBackgroundResource(R.drawable.curvedred);

                } else if (newpasstxt2.equals("")) {
                    newpassword2.setBackgroundResource(R.drawable.curvedred);
                }

            }


        }
    }

    private void savepwd(){


        CommonUtils.log(TAG,"check password >> "+ oldPassword);
        CommonUtils.log(TAG,"check password >> "+ oldPassword);

        if(!passtxt.equals(sharedPreferences.getString(StringConstants.PREF_PASSCODE,""))){
            Toast.makeText(this, "Sorry!!! Your old password was wrong...", Toast.LENGTH_SHORT).show();
        }else {
            editor.putString(StringConstants.PREF_PASSCODE,newpasstxt);
            editor.commit();
            Toast.makeText(this, "Changed successfully..", Toast.LENGTH_SHORT).show();
        }


    }

    private void enableInputVisiblePassword(MyEditTextLight editText) {
//  Typeface cache = editText.getTypeface();
//  editText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
//  editText.setTypeface(cache);
        editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

    }

    private void enableInputHiddenPassword(MyEditTextLight editText) {
//        Typeface cache = editText.getTypeface();
//        editText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
//        editText.setTypeface(cache);

        editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

    }
}
