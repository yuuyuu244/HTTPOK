package com.example.a172113.test;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class AsyncHttpRequest extends AsyncTask<String, Void, String> {
    private Activity mActivity;

    public AsyncHttpRequest(Activity activity) {
        mActivity = activity;
    }

    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection connection = null;
        StringBuilder sb = new StringBuilder();
        Log.e("aaa",params[0]);

        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line = reader.readLine();

            while (line != null) {
                Log.e("bbb",line);
                line = reader.readLine();
                sb.append(line);
            }

            is.close();
        } catch (IOException e) {
            Log.e("ccc",e.toString());
            e.printStackTrace();
        } finally{
            assert connection != null;
        }
        return sb.toString();
    }

    public void onPostExecute(String string) {
        ((TextView)mActivity.findViewById(R.id.textView)).setText(string);
    }

}