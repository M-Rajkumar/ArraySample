package com.sample.program.sample_array_functions;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sample.program.sample_array_functions.Custom.MyEditTextLight;
import com.sample.program.sample_array_functions.Utils.CommonUtils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.sample.program.sample_array_functions.Utils.CommonUtils.log;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ProgressDialog loadinprogress;
    Activity mActivity;
    JSONObject loginjs;
    String TAG ="MainActivity";
    public static final boolean DEBUG = true;

    MyEditTextLight txt_uname,txt_pwd;
    Button btn_login;
    String mUsernameTxt,mPasswordTxt;

    String LOGIN_URL="http://52.20.0.42:3000/api/v1/auth/signin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivity=this;

        initView();
    }

    private void initView() {
        txt_uname=findViewById(R.id.txt_uname);
        txt_pwd=findViewById(R.id.txt_pwd);
        btn_login=findViewById(R.id.btn_login);

        btn_login.setOnClickListener(this);
    }

    @SuppressLint("ShowToast")
    @Override
    public void onClick(View v) {

        mUsernameTxt=txt_uname.getText().toString().trim();
        mPasswordTxt=txt_pwd.getText().toString().trim();

        switch (v.getId()) {
        case R.id.btn_login:
            if (!isHavingValue(txt_uname.getText().toString())) {
                Toast.makeText(mActivity, "enter username", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!isHavingValue(txt_pwd.getText().toString())) {
                Toast.makeText(mActivity, "enter password", Toast.LENGTH_SHORT).show();
                return;
            }

            sampledata logintoserver = new sampledata();
            logintoserver.execute("");
            break;

        }

    }


    @SuppressLint("StaticFieldLeak")
    public class Logintoserver extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            CommonUtils.setProgressBar(mActivity);

        }

        @Override
        protected String doInBackground(String... params) {
            loginjs = new JSONObject();
            String serverresponse = null;

            try {
                loginjs.put("username", "jwarren@barkani.com");
                loginjs.put("password", "happy");

                Log.d(TAG, ">>>>>>>>>>>>>>>>log" + loginjs.toString());
                serverresponse = postDataToServer(LOGIN_URL, "", "POST", null, loginjs.toString());
                Log.d(TAG, ">>>>>>>>>>>>>>>>log" + serverresponse);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return serverresponse;
        }

        /*@Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate();
            loadinprogress = new ProgressDialog(mActivity);
            loadinprogress.setMessage("Please Wait");
            loadinprogress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            loadinprogress.setIndeterminate(true);
            loadinprogress.setProgress(0);
            //loadinprogress.setCanceledOnTouchOutside(getRetainInstance());
            loadinprogress.setCancelable(false);
            loadinprogress.show();
        }*/

        @Override
        protected void onPostExecute(String result) {
            if (isHavingValue(result)) {

                CommonUtils.cancelProgressBar();
                Log.d(TAG, ":::::::::::::::::: result::::::::" + result);
                try {
                    JSONObject serverobj = new JSONObject(result);

                    if (serverobj != null && serverobj.length() > 0) {
                        if (!result.contains("error")) {


                            if (result.contains("message")) {
                                Toast.makeText(mActivity, "" + serverobj.getString("message"), Toast.LENGTH_LONG).show();
                            }

                        }
                    }


                    if (loadinprogress != null && loadinprogress.isShowing()) {
                        loadinprogress.dismiss();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }

            } else {
                if (loadinprogress != null && loadinprogress.isShowing()) {
                    loadinprogress.dismiss();
                }
            }
        }
    }




   @SuppressLint("StaticFieldLeak")
   public class sampledata extends AsyncTask<String,Integer,String>{

       @Override
       protected void onPreExecute() {
           super.onPreExecute();
           CommonUtils.setProgressBar(mActivity);
       }



       @Override
       protected String doInBackground(String... strings) {
           loginjs= new JSONObject();
           String serverresponce=null;

           try {
               loginjs.put("username",mUsernameTxt);
               loginjs.put("password",mPasswordTxt);

               serverresponce=postDataToServer(LOGIN_URL,"","POST",null,loginjs.toString());
           } catch (JSONException e) {
               e.printStackTrace();
           }


           return serverresponce;
       }


       @Override
       protected void onPostExecute(String result) {
           super.onPostExecute(result);

           if(isHavingValue(result)){
               CommonUtils.cancelProgressBar();

               try {
                   JSONObject resultobj=new JSONObject("result");

                   String fname=resultobj.getString("firstName");
                   String lname=resultobj.getString("lastName");
                   String email=resultobj.getString("email");

                   if(result.contains("message")){
                       Toast.makeText(mActivity,""+resultobj.getString("message"),Toast.LENGTH_SHORT).show();
                   }

               } catch (JSONException e) {
                   e.printStackTrace();
               }


           }
       }
   }

    /*private String subscribeToPushService() {
        FirebaseApp.initializeApp(mActivity.getApplicationContext());
        FirebaseMessaging.getInstance().subscribeToTopic("news");
        Log.d("AndroidBash", "Subscribed");
        String token = FirebaseInstanceId.getInstance().getToken();
        // Log and toast
        Log.d("AndroidBash", "" + token);
        return token;
    }*/

        public String postDataToServer(String stringURL, String contentType, String requestMethod,
                                       HashMap<String, String> headerValueMap,
                                       String json) {

            InputStream inputStream = null;
            String response = "";
            if (stringURL == null || stringURL.equalsIgnoreCase("")) {
                return response;
            }
            try {
                log(TAG, "URL is  : " + stringURL);
                log(TAG, "json is  : " + json);
                // create HttpClient
                HttpClient httpclient = new DefaultHttpClient();
                // make POST request to the given URL
                HttpPost httpPost = new HttpPost(stringURL);

                // Set some headers to inform server about the type of the content
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-type", "application/json");
                String mHeaderKey = null;
                String mHeaderVal = null;
                if (headerValueMap != null && headerValueMap.size() > 0) {
                    Iterator it = headerValueMap.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pairs = (Map.Entry) it.next();
                        mHeaderKey = pairs.getKey().toString().trim();
                        mHeaderVal = pairs.getValue().toString().trim();
                        log(TAG, pairs.getKey() + " = "
                                + pairs.getValue());
                        if (isHavingValue(mHeaderKey) && isHavingValue(mHeaderVal)) {
                            httpPost.setHeader(mHeaderKey, mHeaderVal);
                        }
                    }
                }
                StringEntity entity = new StringEntity(json);
                httpPost.setEntity(entity);


                // Execute POST request to the given URL
                HttpResponse httpResponse = httpclient.execute(httpPost);

                // receive response as inputStream
                //			inputStream = httpResponse.getEntity().getContent();
                DataInputStream dataIn = new DataInputStream(
                        httpResponse.getEntity().getContent());
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(dataIn));
                String inputLine;
                while ((inputLine = br.readLine()) != null) {
                    response += inputLine;
                }
                if (DEBUG)
                    log(TAG, "final response is  : " + response);
                br.close();
                dataIn.close();

            } catch (Exception e) {

            }
            return response;
        }

        public boolean isHavingValue(String responce){
            if(responce!=null&& !responce.equalsIgnoreCase("")){
                return true;
            }
            return false;
        }



}
