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
    private int totalTime; //total time
    private String countingText;//counting content
    private String text;//default content
    private CountDownTimer countDownTimer;
    private String unit;//time unit

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
     * init style
     *
     * @param context
     * @param attrs
     */
    private void initStyle(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CountDownButton);
        if (typedArray != null) {
            //default 60 s
            totalTime = typedArray.getInt(R.styleable.CountDownButton_totalTime, 60) * 1000;
            countingText = typedArray.getString(R.styleable.CountDownButton_countingText);
            //default english
            int countUnit = typedArray.getInt(R.styleable.CountDownButton_countUnit, 0);
            switch (countUnit) {
                case 1:
                    unit = getResources().getString(R.string.time_unit);
                    break;
                default:
                    unit = "s";
                    break;
            }
            typedArray.recycle();
            text = getText().toString();
            countingText = countingText == null ? text : countingText;
            setEnabled(true);
            setLines(1);
            setGravity(Gravity.CENTER);
        }
    }

    /**
     * start count
     */
    public void startCount() {
        if (isEnabled()) {
            setEnabled(false);
            countDownTimer = new CountDownTimer(totalTime, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    setText(millisUntilFinished / 1000 + unit + getResources().getString(R.string.after) + countingText);
                }

                @Override
                public void onFinish() {
                    setText(text);
                    setEnabled(true);
                }
            }.start();
        }
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
     * setting totalTime
     *
     * @param totalTime
     */
    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime * 1000;
    }

    public String getCountingText() {
        return countingText;
    }

    /**
     * setting counting content
     *
     * @param countingText
     */
    public void setCountingText(String countingText) {
        this.countingText = countingText;
    }
}
