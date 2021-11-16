package com.example.workapplication.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.workapplication.MainActivity;
import com.example.workapplication.R;
import com.example.workapplication.Login;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends Activity {
    private TextView textView;//声明控件对象
    private int count = 5; //声明时间是5秒
    private Animation animation;
    Timer timer = new Timer();
    private Handler handler;
    private Runnable runnable;
    private boolean isClick;
    private String path;
    private String words;
    Timer welcome = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isClick = false;
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.new_welcome);
        //加载json
        String fileName = "welcome.json";
        String myjson = getJson(this, fileName);
        JSONObject jb = JSONObject.parseObject(myjson);
        Object path = jb.get("picPath");
        Object words = jb.get("words");
        this.path = path.toString();
        this.words = words.toString();
        Log.e("picPath", path.toString());
        Log.e("words", words.toString());
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setFlags(flag, flag);
        initView();
        timer.schedule(task, 1000, 1000);//等待一秒，停顿一秒
        welcome.schedule(welcomeTask, 0, 1000);
        handler = new Handler();
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                if (!isClick) {
                    Intent intent = new Intent(WelcomeActivity.this, Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, count * 1000L);
    }

    private void initView() {
        textView = findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isClick = true;
                Intent intent = new Intent(WelcomeActivity.this, Login.class);  //页面跳转到登录界面
                startActivity(intent);
                finish();
            }
        });
    }

    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        //获得assets资源管理器
        AssetManager assetManager = context.getAssets();
        //使用IO流读取json文件内容
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName), "utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }


    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView = findViewById(R.id.textView);
                    if (count > 0) {
                        textView.setText("跳过" + count + "");
                        count -= 1;
                    } else {
                        timer.cancel();
                    }

                }
            });
        }
    };
    TimerTask welcomeTask = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ImageView img = findViewById(R.id.imageView);
                    Bitmap file = getImageFromAssetsFile(path);
                    img.setImageBitmap(file);
                    textView = findViewById(R.id.textView);
                    textView.setText(words);
                    welcome.cancel();
                }
            });
        }
    };

    public Bitmap getImageFromAssetsFile(String fileName) {
        Bitmap image = null;
        AssetManager am = getAssets();
        InputStream is = null;
        try {
            is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return image;
        } finally {
            if (is != null) {
                try {
                    is.close();
                    is = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

