package com.bawei.day_mvp.http;

import android.os.Handler;

import com.bawei.day_mvp.net.NetCallback;
import com.google.common.io.CharStreams;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/*
 *@Auther:cln
 *@Date: 时间
 *@Description:功能
 * */
public class HttpUtil {
    private static final HttpUtil ourInstance = new HttpUtil();
    private Handler mhandler;

    public static HttpUtil getInstance() {
        return ourInstance;
    }

    private HttpUtil() {
        mhandler = new Handler();
    }

    /**
     * 请求数据
     */
    public void doHttpPost(final String surl, final Map<String, String> param, final NetCallback callback) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    URL url = new URL(surl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    String body = paramToString(param);
                    connection.setDoOutput(true);
                    connection.getOutputStream().write(body.getBytes());
                    if (connection.getResponseCode() == 200) {
                        final String result = CharStreams.toString(new InputStreamReader(connection.getInputStream()));
                        mhandler.post(new Runnable() {
                            @Override
                            public void run() {
                                callback.onSuccess(result);
                            }
                        });
                    }
                } catch (final Exception e) {
                    e.printStackTrace();
                    mhandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onFail(e.getMessage());
                        }
                    });
                }
            }
        }.start();
    }

    private String paramToString(Map<String, String> param) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : param.entrySet()) {
            //把Map.Entry的键和值封装成参数
            builder.append(entry.getKey());
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue()));
            builder.append("&");
        }
        return builder.toString().substring(0, builder.toString().length() - 1);
    }
}
/*
* private String PostDataHttp(String server_url,String name,String pswd) {

        HttpURLConnection httpURLConnection = null;
        if (isWifiConnected(context)) {
            try {
                Log.d(TAG, "getDataHttp: " + server_url);
                URL url = new URL(server_url);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setReadTimeout(5000);

                //请求头的信息
                String body = "phone=" + URLEncoder.encode(name) + "&pwd=" + URLEncoder.encode(pswd);

                httpURLConnection.getOutputStream().write(body.getBytes());
                if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    String s = new String(ByteStreams.toByteArray(inputStream));
                    Log.d(TAG, "getDataHttp: " + s);
                    return s;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            Log.i("", "PostDataHttp: ");

        }
        return null;
    }*/