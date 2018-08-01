package com.bz.nativeapp.native_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bz.nativeapp.R;
import com.bz.nativeapp.plus_activity.Module0_WebView;
import com.bz.nativeapp.plus_activity.Module1_WebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button button;

    @BindView(R.id.button_sec)
    Button buttonSec;

    @BindView(R.id.textView)
    TextView textView;

    @BindView(R.id.editText)
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 绑定
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    public void startModule0() {
        ToastUtils.showShort("button");
        Intent intent = new Intent(getApplicationContext(), Module0_WebView.class);
        ActivityUtils.startActivity(intent);
    }

    @OnClick(R.id.button_sec)
    public void startModule1() {
        ToastUtils.showShort("button_sec");
        Intent intent1 = new Intent(getApplicationContext(), Module1_WebView.class);
        ActivityUtils.startActivity(intent1);
    }

}
