package com.sample.program.sample_array_functions.Custom;


import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.webkit.WebView;

public class VerticalWebView extends WebView {


    public VerticalWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void draw(Canvas canvas) {



        canvas.save();        // IMPORTANT: save current state of clip and matrix (i.e. unclipped state) (let's say it's state #1)
       // do some clipping
        canvas.translate(getHeight(), 0);
        canvas.rotate(90);
        canvas.clipRect(0, 0, getWidth(), getHeight());
        canvas.save();

        super.draw(canvas);

    }
}