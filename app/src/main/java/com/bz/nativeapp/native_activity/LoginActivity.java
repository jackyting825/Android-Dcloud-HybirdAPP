package com.bz.nativeapp.native_activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bz.nativeapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bz on 18-5-5.
 */

public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.textView)
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 绑定布局
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String val = (String) intent.getExtras().get("text");
        textView.setText(val);
    }
}
