package com.zx.upm.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import com.zx.upm.HTApp;

import java.io.File;

public class ProfileUtil {

    public static final String KEY_UP_URL = "http://localhost:8080/up";

    public static boolean SetProfile(Activity view, String key, String value) {
        //Use 0 or MODE_PRIVATE for the default operation
        SharedPreferences settings = view.getSharedPreferences("global", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);

        // 提交本次编辑
        return editor.commit();
    }

    public static String GetProfile(Activity view, String key) {
        //Use 0 or MODE_PRIVATE for the default operation
        SharedPreferences settings = view.getSharedPreferences("global", Context.MODE_PRIVATE);
        return settings.getString(key, null);
    }


    public static Uri getUriForFile(Context context, File file) {
        if (context == null || file == null) {
            throw new NullPointerException();
        }
        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider
                    .getUriForFile(context, "com.zx.upm.fileprovider", file);
        } else {
            uri = Uri.fromFile(file);
        }
        return uri;
    }

    public static String getAvatarName() {
        return HTApp.getInstance().getDirFilePath() + "org_" + System.currentTimeMillis() + ".png";
    }

}
