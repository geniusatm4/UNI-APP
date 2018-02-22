package com.zx.upm.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.File;


/**
 * Created by Administrator on 2017/7/14.
 */
public class SystemPhotoUtil {
    public static final int PICK_PHOTO = 12371;
    public static final int CROP_PHOTO = 12372;
    public static final int TAKE_PHOTO = 12373;
    /* 头像名称 */
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private File tempFile;
    private int width = 250;
    private int height = 250;

    private void crop(Activity context, Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", width);
        intent.putExtra("outputY", height);
        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        context.startActivityForResult(intent, CROP_PHOTO);
    }

    public void setCropPhotoSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void pickPhoto(Activity context) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        context.startActivityForResult(intent, PICK_PHOTO);
    }

    public Bitmap onResult(Activity context, int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK)
            return null;
        if (requestCode == PICK_PHOTO) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                crop(context, uri);
            }
        } else if (requestCode == TAKE_PHOTO) {
            if (hasSdcard()) {
                crop(context, Uri.fromFile(tempFile));
            } else {
                Toast.makeText(context, "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == CROP_PHOTO) {
            if (data != null) {
                Bitmap bitmap = data.getParcelableExtra("data");
                return bitmap;
            }
        }
        return null;
    }

    //相机
    public void takePhoto(Activity context
    ) {
        // 激活相机
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        // 判断存储卡是否可以用，可用进行存储
        if (hasSdcard()) {
            tempFile = new File(Environment.getExternalStorageDirectory(), PHOTO_FILE_NAME);
            // 从文件中创建uri
            Uri uri = Uri.fromFile(tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
        context.startActivityForResult(intent, TAKE_PHOTO);
    }

    /*
* 判断sdcard是否被挂载
*/
    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }
}
