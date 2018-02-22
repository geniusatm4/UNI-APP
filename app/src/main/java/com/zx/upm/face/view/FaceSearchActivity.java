package com.zx.upm.face.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.soundcloud.android.crop.Crop;
import com.zx.upm.R;
import com.zx.upm.bo.FaceSearchReq;
import com.zx.upm.utils.HTAlertDialog;
import com.zx.upm.utils.ProfileUtil;
import com.zx.upm.utils.RestUtil;
import com.zx.upm.view.MainActivity;

import java.io.File;
import java.io.IOException;

public class FaceSearchActivity extends AppCompatActivity {

    private static final int PHOTO_REQUEST_TAKE_PHOTO = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int REQUEST_CODE = 100;
    public static final int CORP_CAMERA_IMAGE = 1002;

    private ImageView btnSelectImage;
    private EditText edIdNumber;
    private String imagePathOrigin;
    private FaceSearchActivity instance;

    private Handler handler = new Handler() {

        /* (non-Javadoc)
         * @see android.os.Handler#handleMessage(android.os.Message)
         */
        @Override
        public void handleMessage(Message msg) {
//          接收消息并且去更新UI线程上的控件内容
            switch (msg.what) {
                case 1:
                    String response = (String) msg.obj;
                    break;
                default:
//                    Toast.makeText(this, "show fail!!", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    File pifFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FaceSearchReq req = new FaceSearchReq();
                req.setImageBase64("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");
                RestUtil.doPost(instance, handler, "/stface/FaceSearch", req);

            }
        });

        // Layout
        instance = this;
        btnSelectImage = (ImageView) findViewById(R.id.btnSelectImage);
        edIdNumber = (EditText) findViewById(R.id.etIdNumber);

        btnSelectImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showCamera();
            }

        });

        // bi
        imagePathOrigin = ProfileUtil.getAvatarName();
    }


    /**
     * 打开照相机
     */
    private void showCamera() {
        HTAlertDialog HTAlertDialog = new HTAlertDialog(this, null, new String[]{getString(R.string.attach_take_pic), getString(R.string.image_manager)});
        HTAlertDialog.init(new HTAlertDialog.OnItemClickListner() {
            @Override
            public void onClick(int position) {
                switch (position) {
                    case 0:
                        if (!checkPermission(Manifest.permission.CAMERA)) {
                            return;
                        }

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                        pifFile = new File(imagePathOrigin);
                        Uri fileUri = getOutputMediaFileUri(pifFile);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                        instance.startActivityForResult(intent, FaceContract.PHOTO_REQUEST_TAKEPHOTO);
                        break;
                    case 1:
                        if (!checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                            return;
                        }

                        Crop.pickImage(instance, FaceContract.PHOTO_REQUEST_GALLERY);
                        break;
                }
            }
        });
    }

    // 将裁剪回来的数据进行处理
    private void handleCrop(int resultCode, Intent result) {
        if (resultCode == RESULT_OK) {
            // mImageView.setImageURI(Crop.getOutput(result));
        } else if (resultCode == Crop.RESULT_ERROR) {


        }
    }

    /***
     * 拍照和选择图片回调
     *
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        int a = 1;
        int b = a;
        switch (requestCode) {
            case PHOTO_REQUEST_TAKE_PHOTO: {
                if (resultCode == RESULT_OK) {
                    if (result != null) {
                        Bundle extras = result.getExtras();
                        Bitmap bm = (Bitmap) extras.get("data");
                        btnSelectImage.setImageBitmap(bm);
                    } else {
                        ////由于指定了目标uri，存储在目标uri，intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                        // 通过目标uri，找到图片
                        // 对图片的缩放处理
                        Uri uri = Uri.fromFile(pifFile);
                        btnSelectImage.setImageURI(uri);
                    }
                }
            }
            case PHOTO_REQUEST_GALLERY: {
                if (resultCode == RESULT_OK) {
                    try {
                        String val = result.getDataString();
                        File tempFile = new File(val);
                        Uri cameraFileUri = getOutputMediaFileUri(tempFile);

                        pifFile = new File(imagePathOrigin);
                        Uri fileUri = getOutputMediaFileUri(pifFile);


                        btnSelectImage.setImageURI(cameraFileUri);

//                        Crop.of(cameraFileUri, fileUri).asSquare()
//                                .start(instance, CORP_CAMERA_IMAGE);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            case CORP_CAMERA_IMAGE: {
                // handleCrop(resultCode, result);


            }


            break;
        }
    }

    /**
     * 获取临时文件路径
     */
    private Uri getOutputMediaFileUri(File file) {
        return ProfileUtil.getUriForFile(this, file);
    }

    /***
     * 检查权限
     *
     */
    private boolean checkPermission(String permissionName) {
        PackageManager pm = this.getPackageManager();
        boolean permission = (PackageManager.PERMISSION_GRANTED ==
                pm.checkPermission(permissionName, this.getPackageName()));
        if (permission) {
            return true;
        } else {
            requestPermissions(new String[]{permissionName}, REQUEST_CODE);
            return false;
        }
    }
}
