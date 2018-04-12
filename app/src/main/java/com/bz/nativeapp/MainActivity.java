package com.bz.nativeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bz.nativeapp.plus_activity.Module0_WebView;
import com.bz.nativeapp.plus_activity.Module1_WebView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button button;
    private Button buttonSec;
    private TextView textView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.button = this.findViewById(R.id.button);
        this.button.setOnClickListener(this);
        this.buttonSec = this.findViewById(R.id.button_sec);
        this.buttonSec.setOnClickListener(this);

        textView = this.findViewById(R.id.textView);
        editText = this.findViewById(R.id.editText);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                Toast.makeText(this, "button", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Module0_WebView.class);
                startActivity(intent);
                break;
            case R.id.button_sec:
                Intent intent1 = new Intent(getApplicationContext(), Module1_WebView.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }
}
