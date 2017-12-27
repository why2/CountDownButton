package com.sdbian.cdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.sdbian.cdbutton.CountDownButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_count).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CountDownButton) v).startCount();
            }
        });
    }
}
