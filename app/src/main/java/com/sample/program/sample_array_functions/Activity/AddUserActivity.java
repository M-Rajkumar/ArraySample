package com.sample.program.sample_array_functions.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;

import com.sample.program.sample_array_functions.Custom.MyEditTextLight;
import com.sample.program.sample_array_functions.Custom.MyTextViewMedium;
import com.sample.program.sample_array_functions.R;
import com.sample.program.sample_array_functions.Utils.CommonUtils;

import java.util.Arrays;
import java.util.List;

public class AddUserActivity extends BaseActivity implements View.OnClickListener {

    MyTextViewMedium txt_type,weeks;
    Activity mActivity;
    MyEditTextLight days;
    ImageView leftArrow,rightArrow;



    String[] stringarray = new String[]{"sss", "type 2"};
    String[] stringweek = new String[]{"sss", "type 2"};

    List<String> list = Arrays.asList(stringarray);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        mActivity = this;
        initView();
    }

    private void initView() {
        txt_type = (MyTextViewMedium) findViewById(R.id.txt_type);
        weeks = (MyTextViewMedium) findViewById(R.id.weeks);
        days = (MyEditTextLight) findViewById(R.id.days);
        leftArrow = (ImageView) findViewById(R.id.leftarrow);
        rightArrow = (ImageView) findViewById(R.id.rightarrow);
        leftArrow.setOnClickListener(this);
        rightArrow.setOnClickListener(this);
        txt_type.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString().equalsIgnoreCase("sss")) {
                    days.setText("20%");
                    days.setEnabled(false);

                } else {
                    days.setText("");
                    days.setEnabled(true);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });





    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.txt_type:{
                CommonUtils.showPopmenu(mActivity, txt_type, stringarray);
                break;
            }
            case R.id.rightarrow:{
              int currentvalue=Integer.parseInt(weeks.getText().toString());

              if(currentvalue!=52){
                  currentvalue=currentvalue+1;
              }

              weeks.setText(""+currentvalue);

                break;
            }

            case R.id.leftarrow:{

                int currentvalue=Integer.parseInt(  weeks.getText().toString());

                if(currentvalue!=1){
                    currentvalue=currentvalue-1;
                }

                weeks.setText(""+currentvalue);

                break;

            }
        }

    }
}
