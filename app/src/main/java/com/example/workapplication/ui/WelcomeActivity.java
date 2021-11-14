package com.example.workapplication.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.TextView;

import com.example.workapplication.MainActivity;
import com.example.workapplication.R;
import com.example.workapplication.Login;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isClick=false;
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.new_welcome);
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setFlags(flag, flag);
        initView();
        timer.schedule(task, 1000, 1000);//等待一秒，停顿一秒
        handler = new Handler();
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                if (!isClick){
                    Intent intent = new Intent(WelcomeActivity.this, Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, count* 1000L);
    }
    private void initView(){
        textView=findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isClick=true;
                Intent intent = new Intent(WelcomeActivity.this, Login.class);  //页面跳转到登录界面
                startActivity(intent);
                finish();
                }
        });
    }
    TimerTask task=new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView=findViewById(R.id.textView);
                    if (count>0){
                        textView.setText("跳过"+count+"");
                        count-=1;
                    }else{
                        timer.cancel();
                    }

                }
            });
        }
    };

}

