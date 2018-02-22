package com.zx.upm.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.zx.upm.R;
import com.zx.upm.utils.ProfileUtil;

public class GlobalSettingsActivity extends AppCompatActivity {

    String serverURL;
    EditText etServerURL;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_settings);

        etServerURL = (EditText) findViewById(R.id.et_serverURL);

        btnSave = (Button) findViewById(R.id.btn_save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveGlobalSetting();
            }
        });
        loadGlobalSetting();
    }

    private void loadGlobalSetting() {
        serverURL = ProfileUtil.GetProfile(this, "serverURL");

        if (serverURL != null) {
            etServerURL.setText(serverURL);
        }
    }


    private void saveGlobalSetting() {
        serverURL = etServerURL.getText().toString();
        if (TextUtils.isEmpty(serverURL)) {
            etServerURL.setError(getString(R.string.input_server_url));
            etServerURL.requestFocus();
            return;
        }

        ProfileUtil.SetProfile(this, "serverURL", serverURL);
        this.finish();
    }
}
