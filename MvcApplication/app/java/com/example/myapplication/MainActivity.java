package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    private Button mTrueButton;
    private Button mFalseButton;
    //按钮变量
    private Button mNextButton;
    private Button mPrevButton;

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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        //activity创建
        super.onCreate(savedInstanceState);
        //获取activity的用户界面
        //activity_main: 布局资源ID参数
        setContentView(R.layout.activity_main);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        updateQuestion();

        //下一个
        mNextButton = (Button) findViewById(R.id.next_button);
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
        mPrevButton = (Button) findViewById(R.id.prev_button);
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

    //把公共代码放在单独的私有方法
    private void updateQuestion()
    {
        //获取ID
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        //显示
        mQuestionTextView.setText(question);
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
    }
}