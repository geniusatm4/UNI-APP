package com.zx.upm.face.view;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import com.zx.upm.R;

//import com.soundcloud.android.crop.Crop;

public class FaceMainActivity extends AppCompatActivity implements View.OnClickListener, FaceContract.View {

    private static final int REQUEST_CODE = 100;

    ImageButton btnFaceSearch;
    ImageButton btnAuditSearch;
    ImageButton btnFaceAdd;
    ImageButton btnFaceTrajectory;

    private FaceMainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_main);

        instance = this;
        btnFaceSearch = (ImageButton) findViewById(R.id.btnFaceSearch);
        btnFaceAdd = (ImageButton) findViewById(R.id.btnFaceAdd);
        btnFaceTrajectory = (ImageButton) findViewById(R.id.btnFaceTrajectory);

        btnFaceSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCamera();
            }
        });
    }

    private void showCamera() {
        Intent intent = new Intent(this.getApplicationContext(), FaceSearchActivity.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {

//        switch (v.getId()) {
//
//            case R.id.btnFaceSearch:
//                Intent intent=new Intent(this.getApplicationContext(), FaceSearchActivity.class);
//                startActivity(intent);
//
//                break;
//
//        }
    }


    @Override
    public void showAvatar(String imagePath) {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void cancelDialog() {

    }

    @Override
    public void showPassword() {

    }

    @Override
    public void hidePassword() {

    }

    @Override
    public void enableButton() {

    }

    @Override
    public void disableButton() {

    }

    @Override
    public void showToast(Object msgRes) {

    }

    @Override
    public String getOriginImagePath() {
        return null;
    }

    @Override
    public void setPresenter(FaceContract.Presenter presenter) {

    }

    @Override
    public Activity getBaseActivity() {
        return null;
    }

    @Override
    public void showSmsCode(String code) {

    }

    @Override
    public void clearCacheCode() {

    }

    @Override
    public void startTimeDown() {

    }

    @Override
    public void finishTimeDown() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                if (permissions[i].equals(Manifest.permission.CAMERA) || permissions[i].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    if (PackageManager.PERMISSION_GRANTED == grantResults[i]) {
                        showToast(R.string.permission_ok);
                    } else {
                        showToast(R.string.permission_error);
                    }
                }
            }
        }
    }
}
