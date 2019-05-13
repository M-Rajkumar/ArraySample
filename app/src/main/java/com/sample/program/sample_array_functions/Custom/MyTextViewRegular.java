package com.sample.program.sample_array_functions.Custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;

import com.sample.program.sample_array_functions.Utils.CommonUtils;

import org.jetbrains.annotations.NotNull;


public class MyTextViewRegular extends android.support.v7.widget.AppCompatTextView {

    Context context;

    public MyTextViewRegular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();

    }

    public MyTextViewRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();

    }

    public MyTextViewRegular(Context context) {
        super(context);
        this.context = context;
        init();
    }

    @SuppressLint("WrongConstant")
    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Regular.ttf");
        setTypeface(tf, 0);
    }


    @Override
    public void setText(CharSequence text, BufferType type) {

//        Spannable s = getCustomSpannableString(getContext(), text);


        String word = "ISI";

        String fulltext = String.valueOf(text);

        if (fulltext.contains(word)) {
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                super.setText(Html.fromHtml(fulltext, Html.FROM_HTML_MODE_LEGACY), type);
//            } else {
//                super.setText(Html.fromHtml(fulltext), type);
//            }

            setMovementMethod(LinkMovementMethod.getInstance());


            ClickableSpan privacy = new ClickableSpan() {
                @Override
                public void updateDrawState(@NotNull TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setUnderlineText(false);
                    ds.setColor(getCurrentTextColor());
                }

                @Override
                public void onClick(@NotNull View widget) {

                    if (context != null) {
                        CommonUtils.showToastMessage(context.getApplicationContext(), "Clicked text");
                    }

                }
            };

            super.setText(addClickablePart(String.valueOf(text), word, privacy), BufferType.SPANNABLE);
        } else {
            super.setText(text, type);
        }
    }

    private SpannableStringBuilder addClickablePart(String str, String word, ClickableSpan privacy) {
        SpannableStringBuilder ssb = new SpannableStringBuilder(str);


        int idx1 = str.indexOf(word);
        int idx2 = 0;
        while (idx1 != -1) {
            idx2 = idx1 + word.length();
            ssb.setSpan(privacy, idx1, idx2, 0);
            idx1 = str.indexOf(word, idx2);
        }


        return ssb;
    }


}