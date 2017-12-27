# CountDownButton
可以倒计时的button，倒计时阶段onClick方法不会触发

###### 引入：
implementation 'com.sdbian:countdownButton:1.1'
```
<com.sdbian.cdbutton.CountDownButton
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="获取"
        android:textAllCaps="false"
		app:countUnit="Chinese"
        app:totalTime="20" />
```

1.totalTime设置总时间，单位秒</br>
2.countUnit可以设置显示时间单位是中文还是英文，默认英文(s)</br>
3.countingText可以设置倒计时过程中显示的内容，例如设置"再次重新获取",倒计时过程显示为**秒后再次重新获取
