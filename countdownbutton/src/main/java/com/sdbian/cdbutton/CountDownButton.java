package com.sdbian.cdbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.CountDownTimer;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.Gravity;

/**
 * @author by SDBian
 * 2017/12/14
 * 倒计时Button
 */

/**
 * 可以倒计时的button
 * 在使用的
 */
public class CountDownButton extends AppCompatButton {
    private int totalTime;//总时间
    private String countingText;  //倒计时过程时间后面显示内容
    private String text;//默认文字
    private CountDownTimer countDownTimer;
    private String unit;//时间单位，s或者秒

    public CountDownButton(Context context) {
        super(context);
        initStyle(context, null);
    }

    public CountDownButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initStyle(context, attrs);
    }

    public CountDownButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initStyle(context, attrs);
    }

    /**
     * 初始化配置
     *
     * @param context
     * @param attrs
     */
    private void initStyle(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CountDownButton);
        if (typedArray != null) {
            //默认60秒
            totalTime = typedArray.getInt(R.styleable.CountDownButton_totalTime, 60) * 1000;
            countingText = typedArray.getString(R.styleable.CountDownButton_countingText);
            //默认英文
            int countUnit = typedArray.getInt(R.styleable.CountDownButton_countUnit, 0);
            switch (countUnit) {
                case 1:
                    unit = "秒";
                    break;
                default:
                    unit = "s";
                    break;
            }
            typedArray.recycle();
            text = getText().toString();
            //防止没有设置倒计时过程中显示的内容
            countingText = countingText == null ? text : countingText;
            setEnabled(true);
            setLines(1);
            setGravity(Gravity.CENTER);
        }
    }

    /**
     * 开始计时
     */
    private void startCount() {
        if (isEnabled()) {
            setEnabled(false);
            countDownTimer = new CountDownTimer(totalTime, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    setText(millisUntilFinished / 1000 + unit + "后" + countingText);
                }

                @Override
                public void onFinish() {
                    setText(text);
                    setEnabled(true);
                }
            }.start();
        }
    }


    /**
     * performClick()在view被点击的时候触发，
     * 设置setOnClickListener后的的回调方法onClick，
     * 也是在performClick中间执行的
     *
     * @return
     */
    @Override
    public boolean performClick() {
        startCount();
        return super.performClick();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    public int getTotalTime() {
        return totalTime;
    }

    /**
     * 设置倒计时的总时间
     *
     * @param totalTime 总时间，单位秒
     */
    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime*1000;
    }

    public String getCountingText() {
        return countingText;
    }

    /**
     * 设置倒计时过程中显示文字，**S后**，或者**秒后**
     *
     * @param countingText
     */
    public void setCountingText(String countingText) {
        this.countingText = countingText;
    }
}
