package com.baway.monthtest.http;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/*
 *@Auther:cln
 *@Date: 时间
 *@Description:功能
 * */
public class HttpUntil {

    //单例模式--懒汉式
    private static HttpUntil until;

    private HttpUntil() {
    }

    public synchronized static HttpUntil getHttpUntil() {
        if (until == null) {
            until = new HttpUntil();
        }
        return until;
    }

    public boolean isNetWork(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info == null) {
            info.isAvailable();
        }
        return false;
    }

    //创建getString
    public String getString(String surl) {
        try {
            URL url = new URL(surl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            if (connection.getResponseCode() == 200) {
                //获取流
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                //拼接字符串
                StringBuffer buffer = new StringBuffer();
                String s = "";
                while ((s = reader.readLine()) != null) {
                    buffer.append(s);
                }
                //关闭
                connection.disconnect();
                //关闭流
                reader.close();
                //返回字符串
                return buffer.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //创建接口
    public interface CallBackPostExecute {
        void getPostExecute(String s);
    }

    //创建AsyncTask
    @SuppressLint("StaticFieldLeak")
    public void AsyncTask(String str, final CallBackPostExecute post) {
        new AsyncTask<String, Integer, String>() {
            @Override
            protected String doInBackground(String... strings) {
                return getString(strings[0]);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                post.getPostExecute(s);
            }
        }.execute(str);
    }
}
