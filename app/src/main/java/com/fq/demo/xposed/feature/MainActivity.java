package com.fq.demo.xposed.feature;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.fq.demo.xposed.R;

/**
 * MainActivity2019/3/12 10:08 AM
 *
 * @desc :
 */
public class MainActivity extends AppCompatActivity {


    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.m_tv_label);
        textView.setText("Hello init");
    }
}
