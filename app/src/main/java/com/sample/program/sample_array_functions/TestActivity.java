package com.sample.program.sample_array_functions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sample.program.sample_array_functions.Custom.MyEditTextLight;
import com.sample.program.sample_array_functions.Utils.CommonUtils;

public class TestActivity extends AppCompatActivity {

    int factnumber;
    int fact=1;
    int facts;
    MyEditTextLight testid;
    Button testbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        testid=(MyEditTextLight)findViewById(R.id.testid);
        testbtn=(Button) findViewById(R.id.testbtn);

        testbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               test();
            }
        });




    }


    private void test(){
        facts= Integer.valueOf(testid.getText().toString());

        CommonUtils.log("rr","fact value >> "+facts);

     // facts=testid.getText().toString();

        for(int i=1;i<=facts;i++){
            fact=fact*i;
        }

        CommonUtils.showToastMessage(getApplicationContext(),"Test result >> "+fact);




    }
}
