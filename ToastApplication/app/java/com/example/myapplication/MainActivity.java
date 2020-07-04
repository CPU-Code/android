/*
 * @Author: cpu_code
 * @Date: 2020-07-04 14:14:42
 * @LastEditTime: 2020-07-04 14:31:44
 * @FilePath: \android\ToastApplication\app\java\com\example\myapplication\MainActivity.java
 * @Gitee: https://gitee.com/cpu_code
 * @CSDN: https://blog.csdn.net/qq_44226094
 */ 
package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    private Button mTrueButton;
    private Button mFalseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        //activity创建
        super.onCreate(savedInstanceState);
        //获取activity的用户界面
        //activity_main: 布局资源ID参数
        setContentView(R.layout.activity_main);

        //引用已生成的组件
        mTrueButton = (Button) findViewById(R.id.true_button);
        //设置了一个监听器
        mTrueButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //提示消息
                //Activity的一个实例
                //字符串消息的资源ID
                //停留时间
                //屏幕上显示toast消息
                Toast.makeText(MainActivity.this,
                                R.string.correct_toast,
                                Toast.LENGTH_LONG).show();
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(MainActivity.this,
                                R.string.incorrect_toast,
                                Toast.LENGTH_SHORT).show();
                // Does nothing yet, but soon!
            }
        });
    }
}