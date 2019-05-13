package com.sample.program.sample_array_functions.Utils;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.ListPopupWindow;
import android.text.Spannable;
import android.text.method.ScrollingMovementMethod;
import android.text.style.URLSpan;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sample.program.sample_array_functions.Custom.MyEditTextLight;
import com.sample.program.sample_array_functions.Custom.MyTextViewBold;
import com.sample.program.sample_array_functions.Custom.MyTextViewMedium;
import com.sample.program.sample_array_functions.Custom.MyTextViewRegular;
import com.sample.program.sample_array_functions.Custom.URLSpanNoUnderline;
import com.sample.program.sample_array_functions.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;

import javax.sql.DataSource;

public class CommonUtils {
    private static final String TAG = "CommonUtil";
    private static Dialog dialog;

    public static void log(String tag, String string) {
        if (StringConstants.DEBUG) {

            int maxLogSize = 1000;
            for (int i = 0; i <= string.length() / maxLogSize; i++) {
                int start = i * maxLogSize;
                int end = (i + 1) * maxLogSize;
                end = end > string.length() ? string.length() : end;
                Log.d(tag, string.substring(start, end));
            }
        }
    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        InputMethodManager methodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        assert methodManager != null && view != null;
        methodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void showKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        InputMethodManager methodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        assert methodManager != null && view != null;
        methodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    public static void setProgressBar(Context context) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (!activity.isFinishing()) {

                dialog = new Dialog(context, R.style.AlertDialogCustom);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.swipeprogress);
                dialog.setCancelable(false);

                Objects.requireNonNull(dialog.getWindow()).setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().getAttributes().windowAnimations = R.style.AlertDialogCustom;

                SwipeRefreshLayout swipeRefreshLayout = dialog.findViewById(R.id.activity_main_swipe_refresh_layout);
                swipeRefreshLayout.setColorSchemeResources(R.color.maincolor);
                swipeRefreshLayout.setRefreshing(true);

                dialog.show();
            }


        }
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }
//    public static void setProgressBar(Context context) {
//        progressdialog = new Dialog(context, R.style.AlertDialogCustom);
//        progressdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        progressdialog.setContentView(R.layout.progressdialogxml);
//        progressdialog.setCancelable(false);
//
//        Objects.requireNonNull(progressdialog.getWindow()).setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
//        progressdialog.getWindow().getAttributes().windowAnimations = R.style.AlertDialogCustom;
//
//        progressdialog.show();
//        SwipeRefreshLayout swipeRefreshLayout = progressdialog.findViewById(R.id.swipe_layout);
//        swipeRefreshLayout.setRefreshing(true);
//
//    }

    public static void cancelProgressBar() {
        if (dialog != null && dialog.isShowing()) {
            dialog.cancel();
        }
    }


    public static boolean checkCameraFront(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)) {
            return true;
        } else {
            return false;
        }
    }

    public static void showNoNetworkDialog(final Context context) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog
                .setMessage(StringConstants.ERROR_MESSAGE_NO_INTERNET)
                .setCancelable(false)
                .setPositiveButton("Settings",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, close
                                context.startActivity(new Intent(
                                        Settings.ACTION_SETTINGS));

                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // show it
        alertDialog.show();

    }

    public static void showToastMessage(final Context context,
                                        final String message) {

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

    }

    public static void showError(final Context context, final String msg) {

        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (!activity.isFinishing()) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(msg)
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();

                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }
        }
    }


    public static void showErrorclose(final Context context, final String msg) {

        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (!activity.isFinishing()) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(msg)
                        .setCancelable(false)
                        .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();

                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }
        }
    }

    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        return (int) (dpWidth / 180);
    }





    public static void showsimpledialogcustom(final Context context, final String msg, String title) {

        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (!activity.isFinishing()) {

                final Dialog dialog = new Dialog(context, R.style.AlertDialogCustom);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.alert_message);
                dialog.setCancelable(true);

                Objects.requireNonNull(dialog.getWindow()).setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().getAttributes().windowAnimations = R.style.AlertDialogCustom;

                Window window = dialog.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();

                wlp.gravity = Gravity.BOTTOM;
                window.setAttributes(wlp);

//                DisplayMetrics displayMetrics = new DisplayMetrics();
//                activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//
//
//                int height = displayMetrics.heightPixels;
//                int width = displayMetrics.widthPixels;
//
//                int newheight = height * 70 / 100;
//                int newwidth = width * 70 / 100;


                MyTextViewRegular description = dialog.findViewById(R.id.description);
                MyTextViewBold dialoghead = dialog.findViewById(R.id.dialoghead);
                MyTextViewBold dismissdialog = dialog.findViewById(R.id.dismissdialog);
//                description.setMaxHeight(newheight);
//                description.setWidth(newwidth);

                description.setMovementMethod(ScrollingMovementMethod.getInstance());
                description.setText(msg);

                dialoghead.setText(title);

                dismissdialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        }
    }

    public static String encodeImage(Bitmap bm) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if (bm != null) {
            bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);

            byte[] b = baos.toByteArray();
            //Base64.de
            return Base64.encodeToString(b, Base64.DEFAULT);
        } else {
            return null;
        }

    }







    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    public static boolean isNetworkAvailable(Context mContext) {
        boolean connection = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm != null) {
                NetworkInfo net_info = cm.getActiveNetworkInfo();
                if (net_info != null && net_info.isConnected()) {
                    connection = true;
                    int type = net_info.getType();
                    switch (type) {
                        case ConnectivityManager.TYPE_MOBILE:
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }





    public static Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
        Map<String, Object> retMap = new HashMap<String, Object>();

        if (json != JSONObject.NULL) {
            retMap = toMap(json);
        }
        return retMap;
    }


    public static Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();

        Iterator<String> keysItr = object.keys();
        while (keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

    public static List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }


    public static String ConvertLocaltoUtcTimezone(String timestr, String Timeformat) {
        String convertdate = "";
        try {

            /*DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;

            DateFormat convertionformat = new SimpleDateFormat(""+Timeformat, Locale.US);
        date = dateFormat.parse(timestr);
            convertdate=convertionformat.format(date.getTime());*/


            CommonUtils.log(TAG, " CUrret convertion  intput date " + timestr);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
            df.setTimeZone(TimeZone.getDefault());
            Date date = df.parse(timestr);

            SimpleDateFormat dff = new SimpleDateFormat(Timeformat, Locale.ENGLISH);
            dff.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date dateutc = dff.parse(dff.format(date));

            //df.setTimeZone(TimeZone.getTimeZone(""+TimeZone.getDefault()));

            String formattedDate = dff.format(dateutc);
            convertdate = formattedDate;


            CommonUtils.log(TAG, " CUrret convertion  temp date " + formattedDate);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertdate;
    }


    public static String ConvertUTCTimetoLocal(String timestr, String currentformat, String Timeformat) {
        String convertdate = "";
        String convertutcdate = "";
        try {

            CommonUtils.log(TAG, " CUrret convertion " + timestr);


            SimpleDateFormat df = new SimpleDateFormat("" + currentformat, Locale.ENGLISH);
            df.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = df.parse(timestr);

            SimpleDateFormat dff = new SimpleDateFormat(Timeformat, Locale.ENGLISH);
            df.setTimeZone(TimeZone.getDefault());
            Date dateutc = dff.parse(dff.format(date));

            //df.setTimeZone(TimeZone.getTimeZone(""+TimeZone.getDefault()));

            String formattedDate = dff.format(dateutc);
            convertdate = formattedDate;


            CommonUtils.log(TAG, " CUrret convertion  temp date " + timestr);

            CommonUtils.log(TAG, " CUrret convertion " + convertdate);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertdate;
    }

    public static String TimeConvertion(String timestr, String currentformat, String Timeformat) {
        String convertionstr = "";

        try {
            CommonUtils.log(TAG, currentformat + " CUrret TimeConvertion " + timestr);
            SimpleDateFormat df = new SimpleDateFormat("" + currentformat, Locale.ENGLISH);
            Date date = df.parse(timestr);
            SimpleDateFormat convertdf = new SimpleDateFormat("" + Timeformat, Locale.ENGLISH);
            convertionstr = convertdf.format(date.getTime());
            CommonUtils.log(TAG, Timeformat + " CUrret TimeConvertion OUTPUT " + convertionstr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertionstr;

    }


    public static boolean isHavingvalue(String str) {
        if (str == null) {
            return false;
        }
        if (str.length() == 0) {
            return false;
        } else {
            return true;
        }

    }


    public static void showPopmenu(Activity mActivity, final View customview, final String[] arraylist) {


        final ListPopupWindow listPopupWindow = new ListPopupWindow(
                mActivity);
        listPopupWindow.setAdapter(new ArrayAdapter(
                mActivity,
                R.layout.widget_daysperfixlit, arraylist));
        listPopupWindow.setAnchorView(customview);
        listPopupWindow.setModal(true);
        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                if (customview instanceof MyTextViewRegular) {

                    ((MyTextViewRegular) customview).setText("" + arraylist[position]);

                } else if (customview instanceof MyTextViewMedium) {
                    ((MyTextViewMedium) customview).setText("" + arraylist[position]);
                } else if (customview instanceof MyTextViewBold) {
                    ((MyTextViewBold) customview).setText("" + arraylist[position]);
                } else if (customview instanceof MyEditTextLight) {
                    ((MyEditTextLight) customview).setText("" + arraylist[position]);
                } else if (customview instanceof MyEditTextLight) {
                    ((MyEditTextLight) customview).setText("" + arraylist[position]);
                }


                listPopupWindow.dismiss();
            }
        });
        listPopupWindow.show();

    }

    public static int calculateScreenSize(Activity mActivity,int type){
        int height, width;
        int resultsize;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

        CommonUtils.log(TAG,"screen height >> "+height+" screen width >> "+width);
        if(type==1){
            return width;
        }else {
            return height;
        }
    }

    public static Fragment getFragment(String fragmentName){
        Fragment fragment=null;
        try{
          fragment  =(Fragment) Class.forName(""+fragmentName).newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
      return fragment;
    }

    public static void removeUnderlines(Spannable p_Text) {
        URLSpan[] spans = p_Text.getSpans(0, p_Text.length(), URLSpan.class);

        for (URLSpan span : spans) {
            int start = p_Text.getSpanStart(span);
            int end = p_Text.getSpanEnd(span);
            p_Text.removeSpan(span);
            span = new URLSpanNoUnderline(span.getURL());
            p_Text.setSpan(span, start, end, 0);
        }
    }
}
