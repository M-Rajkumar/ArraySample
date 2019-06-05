package com.sample.program.sample_array_functions.Activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sample.program.sample_array_functions.Constants.StringConstants;
import com.sample.program.sample_array_functions.R;

public class BaseActivity extends AppCompatActivity {

    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mActivity=this;
        sharedPreferences=getApplicationContext().getSharedPreferences(""+ StringConstants.APP_PREFS_ACCOUNT,0);
        editor=sharedPreferences.edit();
        editor.apply();
    }
}
