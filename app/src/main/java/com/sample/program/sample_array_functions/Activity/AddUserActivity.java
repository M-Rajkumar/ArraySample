package com.sample.program.sample_array_functions.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.sample.program.sample_array_functions.Custom.MyEditTextLight;
import com.sample.program.sample_array_functions.Custom.MyTextViewMedium;
import com.sample.program.sample_array_functions.R;
import com.sample.program.sample_array_functions.Utils.CommonUtils;

import java.util.Arrays;
import java.util.List;

public class AddUserActivity extends BaseActivity {

    MyTextViewMedium txt_type;
    Activity mActivity;
    MyEditTextLight days;



    String[] stringarray=new String[]{"sss", "type 2"};

    List<String> list = Arrays.asList(stringarray);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        mActivity=this;
        initView();
    }

    private void initView() {
        txt_type=(MyTextViewMedium)findViewById(R.id.txt_type);
        days=(MyEditTextLight) findViewById(R.id.days);



        txt_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    CommonUtils.showPopmenu(mActivity, txt_type, stringarray);

            }
        });

   txt_type.addTextChangedListener(new TextWatcher() {
       @Override
       public void beforeTextChanged(CharSequence s, int start, int count, int after) {

       }

       @Override
       public void onTextChanged(CharSequence s, int start, int before, int count) {

           if(s.toString().equalsIgnoreCase("sss")){
               days.setText("20%");
               days.setEnabled(false);

           }else {
               days.setText("");
               days.setEnabled(true);
           }

       }

       @Override
       public void afterTextChanged(Editable s) {

       }
   });


    }
}
