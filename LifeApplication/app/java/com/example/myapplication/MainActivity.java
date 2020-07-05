/*
 * @Author: cpu_code
 * @Date: 2020-07-05 14:16:21
 * @LastEditTime: 2020-07-05 14:19:52
 * @FilePath: \android\LifeApplication\app\java\com\example\myapplication\MainActivity.java
 * @Gitee: https://gitee.com/cpu_code
 * @CSDN: https://blog.csdn.net/qq_44226094
 */ 
package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    // onPause() called -> onSaveInstanceState ->
    // onStop() called -> onDestroy() called ->
    // onCreate(Bundle) called -> onStart() called ->
    // onResume() called

    /*
     *ERROR Log.e(...) 错误
     * WARNING Log.w(...) 警告
     * INFO Log.i(...) 信息型消息
     * DEBUG Log.d(...) 调试输出（可能被过滤掉）
     * VERBOSE Log.v(...) 仅用于开发
     * */

    //定义TAG常量
    private static final String TAG = "MainActivity";
    //键 - 值对的键
    private static final String KEY_INDEX = "index";

    private Button mTrueButton;
    private Button mFalseButton;
    //按钮变量
    private ImageButton mNextButton;
    private ImageButton mPrevButton;

    private TextView mQuestionTextView;
    //当前数组索引
    private int mCurrentIndex = 0;

    //Question对象数组
    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, false)
    };

    /*
     *@Override注解，要求编译器保证当前类拥有你要覆盖的方法
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        //activity创建
        super.onCreate(savedInstanceState);
        //获取activity的用户界面
        //activity_main: 布局资源ID参数
        setContentView(R.layout.activity_main);

        //记录日志
        Log.d(TAG, "onCreate(Bundle) called");

        //确认是否成功获取该数值
        if(savedInstanceState != null)
        {
            //获取成功，就将它赋值给变量mCurrentIndex
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        updateQuestion();

        //下一个
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                //每次加一, 取余, 形成循环
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        //上一个
        mPrevButton = (ImageButton) findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                //判断是否为0
                if(mCurrentIndex == 0)
                {
                    mCurrentIndex = mQuestionBank.length - 1;
                }
                else
                {
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                }

                updateQuestion();
            }
        });

        mTrueButton = (Button) findViewById(R.id.true_button);
        //设置了一个监听器
        mTrueButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                checkAnswer(false);
            }
        });
    }


    //五个生命周期方法
    @Override
    public void onStart()
    {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onStop()
    {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    // 新增的常量值作为键
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");

        //将 mCurrentIndex变量值保存到bundle中
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    //把公共代码放在单独的私有方法
    private void updateQuestion()
    {
        // Log a message at "debug" log level
        Log.d(TAG, "Current question index: " + mCurrentIndex);

        Question question;

        try
        {
            question = mQuestionBank[mCurrentIndex];
        }
        catch (ArrayIndexOutOfBoundsException ex)
        {
            // Log a message at "error" log level, along with an exception stack trace
            Log.e(TAG, "Index was out of bounds", ex);
        }

        //获取ID
        int mQuestion = mQuestionBank[mCurrentIndex].getTextResId();
        //显示
        mQuestionTextView.setText(mQuestion);

    }

    //判别用户单击了TRUE还是FALSE按钮
    private void checkAnswer(boolean userPressedTrue)
    {
        int messageResId = 0;
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        if (userPressedTrue == answerIsTrue)
        {
            messageResId = R.string.correct_toast;
        }
        else
        {
            messageResId = R.string.incorrect_toast;
        }

        //提示消息
        //Activity的一个实例
        //字符串消息的资源ID
        //停留时间
        //屏幕上显示toast消息
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();

        //每次加一, 取余, 形成循环
        mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
        updateQuestion();
    }
}