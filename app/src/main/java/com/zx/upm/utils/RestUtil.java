package com.zx.upm.utils;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
 import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RestUtil {

    private static String UP_URL = null;

    public static void doPost(Activity activity, final Handler handler, String uri, final Object body) {
        if (UP_URL == null) {
            UP_URL = ProfileUtil.GetProfile(activity, ProfileUtil.KEY_UP_URL);

           // if (UP_URL == null) {
                UP_URL = "http://10.30.22.112:8080/viia";
                ProfileUtil.SetProfile(activity, ProfileUtil.KEY_UP_URL, UP_URL);
         //   }
        }
        final String reqURL = UP_URL + uri;
        //开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(reqURL);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                    connection.setRequestMethod("POST");
                    // http正文内，因此需要设为true
                    connection.setDoOutput(true);
                    // Read from the connection. Default is true.
                    connection.setDoInput(true);
                    connection.setConnectTimeout(8888);
                    connection.setReadTimeout(8888);
                    connection.connect();
                    DataOutputStream wr = new DataOutputStream(connection.getOutputStream());

                    Gson gson = new Gson();
                    String jsonString = gson.toJson(body);
                    wr.writeBytes(jsonString);
                    wr.flush();
                    wr.close();

                    // 摘要认证
                    MessageDigest md5 = null;
                    try {
                        md5 = MessageDigest.getInstance("MD5");
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }

//                    // Digest password using the MD5 algorithm
//                    String password = "1234";
//                    md5.update(password.getBytes());
//                    String digestedPass = digest2HexString(md5.digest());
//
//                    // Set header "Authorization"
//                    String credentials = "testuser:" + digestedPass;
//                    connection.setRequestProperty("Authorization", "Digest " + credentials);


                    InputStream in = connection.getInputStream();
                    //下面对获取到的输入流进行读取
                    BufferedReader buffer = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = buffer.readLine()) != null) {
                        response.append(line);
                    }

                    Message message = new Message();
                    message.what = 1;
                    //将服务器返回的数据存放到Message中
                    message.obj = response.toString();
                    handler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }


    public void doGet(Activity activity, final Handler handler, String uri) {
        if (UP_URL == null) {
            UP_URL = ProfileUtil.GetProfile(activity, ProfileUtil.KEY_UP_URL);

            if (UP_URL == null) {
                ProfileUtil.SetProfile(activity, ProfileUtil.KEY_UP_URL, "http://localhost:8080:UP");
            }
        }
        final String reqURL = UP_URL + uri;
        //开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(reqURL);
                    connection = (HttpURLConnection) url.openConnection();

                    // 摘要认证
                    MessageDigest md5 = null;
                    try {
                        md5 = MessageDigest.getInstance("MD5");
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }

                    // Digest password using the MD5 algorithm
                    String password = "1234";
                    md5.update(password.getBytes());
                    String digestedPass = digest2HexString(md5.digest());

                    // Set header "Authorization"
                    String credentials = "testuser:" + digestedPass;
                    connection.setRequestProperty("Authorization", "Digest " + credentials);

                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8888);
                    connection.setReadTimeout(8888);

                    InputStream in = connection.getInputStream();
                    //下面对获取到的输入流进行读取
                    BufferedReader buffer = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = buffer.readLine()) != null) {
                        response.append(line);
                    }

                    Message message = new Message();
                    message.what = 1;
                    //将服务器返回的数据存放到Message中
                    message.obj = response.toString();
                    handler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    private static String digest2HexString(byte[] digest) {
        String digestString = "";
        int low, hi;

        for (int i = 0; i < digest.length; i++) {
            low = (digest[i] & 0x0f);
            hi = ((digest[i] & 0xf0) >> 4);
            digestString += Integer.toHexString(hi);
            digestString += Integer.toHexString(low);
        }
        return digestString;
    }
}
