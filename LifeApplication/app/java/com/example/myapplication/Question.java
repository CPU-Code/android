/*
 * @Author: cpu_code
 * @Date: 2020-07-05 14:16:21
 * @LastEditTime: 2020-07-05 14:19:55
 * @FilePath: \android\LifeApplication\app\java\com\example\myapplication\Question.java
 * @Gitee: https://gitee.com/cpu_code
 * @CSDN: https://blog.csdn.net/qq_44226094
 */ 
package com.example.myapplication;

public class Question
{
    //成员变量
    //地理知识问题字符串的资源ID
    private int mTextResId;
    private boolean mAnswerTrue;

    //构造方法
    public Question(int textResId, boolean answerTrue)
    {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }

    //右击构造方法后方区域，选择Generate... → Getter and Setter菜单项

    //getter方法
    public int getTextResId()
    {
        return mTextResId;
    }

    //setter方法
    public void setTextResId(int textResId)
    {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue()
    {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue)
    {
        mAnswerTrue = answerTrue;
    }
}
