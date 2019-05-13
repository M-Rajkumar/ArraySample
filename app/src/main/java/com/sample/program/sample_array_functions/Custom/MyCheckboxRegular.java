package com.sample.program.sample_array_functions.Custom;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;


public class MyCheckboxRegular extends android.support.v7.widget.AppCompatEditText {

    public MyCheckboxRegular(Context context) {
        super(context);
        init();
    }

    public MyCheckboxRegular(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MyCheckboxRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Regular.ttf");
        setTypeface(tf ,1);
    }
}
