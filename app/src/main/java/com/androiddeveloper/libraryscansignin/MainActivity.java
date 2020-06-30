package com.androiddeveloper.libraryscansignin;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    private Button btn_toSun;
    private Button btn_toNewspaper;
    private Button btn_toBorrow1;
    private Button btn_toBorrow2;
    private Button btn_toComputer;
    private Button btn_toChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        addListeners();

        if (!ActivityCompat.shouldShowRequestPermissionRationale(
                this, Manifest.permission.CAMERA)) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, 0);
        }
    }

    private void initView() {
        btn_toSun = findViewById(R.id.btn_toSun);
        btn_toNewspaper = findViewById(R.id.btn_toNewspaper);
        btn_toBorrow1 = findViewById(R.id.btn_toBorrow1);
        btn_toBorrow2 = findViewById(R.id.btn_toBorrow2);
        btn_toComputer = findViewById(R.id.btn_toComputer);
        btn_toChild = findViewById(R.id.btn_toChild);
    }

    private String roomId;

    private void addListeners() {
        final IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intentIntegrator.setBeepEnabled(false);
        intentIntegrator.setCaptureActivity(ScanActivity.class);

        btn_toSun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roomId = "ROOM_ID_SUN";
                intentIntegrator.initiateScan();
            }
        });

        btn_toNewspaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roomId = "ROOM_ID_NEWSPAPER";
                intentIntegrator.initiateScan();
            }
        });

        btn_toBorrow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roomId = "ROOM_ID_BORROW_1";
                intentIntegrator.initiateScan();
            }
        });
        btn_toBorrow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roomId = "ROOM_ID_BORROW_2";
                intentIntegrator.initiateScan();
            }
        });
        btn_toComputer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roomId = "ROOM_ID_COMPUTER";
                intentIntegrator.initiateScan();
            }
        });
        btn_toChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roomId = "ROOM_ID_CHILD";
                intentIntegrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            Toast.makeText(this, "成功" + result.getContents(),
                    Toast.LENGTH_SHORT).show();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
        Intent intent = new Intent(this, WebviewActivity.class);
        intent.putExtra("url",
                "http://baidu.server.qbserver.cn:5000/library-fake-reservation/page/toSign?roomId="
                        + roomId + "&type=in");
        startActivity(intent);
    }
}