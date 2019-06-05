package com.sample.program.sample_array_functions.Fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sample.program.sample_array_functions.Constants.StringConstants;
import com.sample.program.sample_array_functions.Main.LauncherActivity;
import com.sample.program.sample_array_functions.R;
import com.sample.program.sample_array_functions.Utils.CommonUtils;

import in.arjsna.passcodeview.PassCodeView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    private final String PASSCODE = "1234";
    private PassCodeView passCodeView;
    public Activity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            mActivity = (Activity) context;
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mActivity != null) {

            sharedPreferences = mActivity.getSharedPreferences(StringConstants.APP_PREFS_ACCOUNT, 0);
            editor = sharedPreferences.edit();
            editor.apply();


        }
    }

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_main, container, false);
        passCodeView = (PassCodeView) mRootView.findViewById(R.id.pass_code_view);
        TextView promptView = (TextView) mRootView.findViewById(R.id.promptview);
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Font-Bold.ttf");
        passCodeView.setTypeFace(typeFace);
        passCodeView.setKeyTextColor(R.color.black_shade);
        passCodeView.setEmptyDrawable(R.drawable.empty_dot);
        passCodeView.setFilledDrawable(R.drawable.filled_dot);
        promptView.setTypeface(typeFace);
        bindEvents();
        return mRootView;
    }

    private void bindEvents() {
        passCodeView.setOnTextChangeListener(new PassCodeView.TextChangeListener() {
            @Override public void onTextChanged(String text) {
                CommonUtils.log("ff","text text > "+text);

                if (text.length() == 4) {
                    if (text.equals(sharedPreferences.getString(StringConstants.PREF_PASSCODE,"1234"))) {
                        editor.putString(StringConstants.PREF_PASSCODE, text);
                        editor.commit();
                        Intent intent = new Intent(getActivity(), LauncherActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    } else {
                        passCodeView.setError(true);
                    }
                }
            }
        });
    }


}
