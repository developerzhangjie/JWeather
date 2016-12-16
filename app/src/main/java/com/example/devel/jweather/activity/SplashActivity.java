package com.example.devel.jweather.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.devel.jweather.R;
import com.example.devel.jweather.utils.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Description:欢迎界面
 * Created by devel on 12/10/2016.
 */

public class SplashActivity extends Activity {

    private Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        initData();
    }

    private void initData() {
        copyDB("china_province_city_zone.db");
    }

    private void initView() {
        start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * 拷贝数据库
     */
    private void copyDB(String name) {
        File file = new File(getFilesDir(), name);
        if (!file.exists()) {
            AssetManager assetManager = getAssets();
            InputStream in = null;
            FileOutputStream out = null;
            try {
                //2.读取数据库
                in = assetManager.open(name);
                out = new FileOutputStream(file);
                byte[] b = new byte[1024];
                int len;
                while ((len = in.read(b)) != -1) {
                    out.write(b, 0, len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                IOUtils.closeQuietly(in);
                IOUtils.closeQuietly(out);
            }
        }
    }
}
