package com.sample.program.sample_array_functions.Custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.Spannable;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;


import com.sample.program.sample_array_functions.R;
import com.sample.program.sample_array_functions.Utils.CommonUtils;




public class MyTextViewLight extends android.support.v7.widget.AppCompatTextView {
    Context context;

    public MyTextViewLight(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
        this.setLinkTextColor(ContextCompat.getColor(context, R.color.delred));

    }

    public MyTextViewLight(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        init();
        this.setLinkTextColor(ContextCompat.getColor(context, R.color.delred));

    }

    public MyTextViewLight(Context context) {
        super(context);
        this.context = context;
        init();
        this.setLinkTextColor(ContextCompat.getColor(context, R.color.delred));
    }

    @SuppressLint("WrongConstant")
    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Light.ttf");
        //setTextColor(getResources().getColor(R.color.text_grey));
        setTypeface(tf, 0);

    }

    @Override
    public void setText(CharSequence text, BufferType type) {

//        Spannable s = getCustomSpannableString(getContext(), text);

        String word = "ISI";

        String fulltext = String.valueOf(text);

        if (fulltext.contains(word)) {
//            setMovementMethod(LinkMovementMethod.getInstance());
//
//            ClickableSpan privacy = new ClickableSpan() {
//                @Override
//                public void updateDrawState(@NotNull TextPaint ds) {
//                    super.updateDrawState(ds);
//                    ds.setUnderlineText(false);
//                    ds.setColor(getCurrentTextColor());
//                }
//
//                @Override
//                public void onClick(@NotNull View widget) {
//
//                    if (context != null) {
//                        CommonUtils.showToastMessage(context.getApplicationContext(), "Clicked text");
//                    }
//
//                }
//            };
//
//            super.setText(addClickablePart(String.valueOf(text), word, privacy), BufferType.SPANNABLE);

            Spanned result;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                result = Html.fromHtml(fulltext, Html.FROM_HTML_MODE_LEGACY);
            } else {
                result = Html.fromHtml(fulltext);
            }
            setMovementMethod(LinkMovementMethod.getInstance());
            CommonUtils.removeUnderlines((Spannable) result);

            super.setText(result, type);
        } else {
            super.setText(text, type);
        }
    }

}

