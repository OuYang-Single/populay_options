package com.pine.populay_options.mvp.model.mvp.ui.activity;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.pine.populay_options.R;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    RadioGroup mRadioGroup;
    RadioButton RadioButton1;
    RadioButton RadioButton2;
    RadioButton RadioButton3;
    RadioButton RadioButton4;
    RadioButton RadioButton5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.complaint);
        setContentView(R.layout.activity_main2);
        mRadioGroup=(RadioGroup)findViewById(R.id. RadioGroup);
        RadioButton1=(RadioButton)findViewById(R.id.RadioButton1);
        RadioButton2=(RadioButton)findViewById(R.id.RadioButton2);
        RadioButton3=(RadioButton)findViewById(R.id.RadioButton3);
        RadioButton4=(RadioButton)findViewById(R.id.RadioButton4);
        RadioButton5=(RadioButton)findViewById(R.id.RadioButton5);
        findViewById(R.id.log_bt_log_in).setOnClickListener(this);
        findViewById(R.id.toolbar_back).setVisibility(View.VISIBLE);
        RadioButton1.setOnClickListener(this);
        RadioButton2.setOnClickListener(this);
        RadioButton3.setOnClickListener(this);
        RadioButton4.setOnClickListener(this);
        RadioButton5.setOnClickListener(this);
        RadioButton1.setChecked(true);
        RadioButton2.setChecked(false);
        RadioButton3.setChecked(false);
        RadioButton4.setChecked(false);
        RadioButton5.setChecked(false);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.log_bt_log_in:
                if (RadioButton1.isChecked()||RadioButton2.isChecked()||RadioButton3.isChecked()||RadioButton4.isChecked()||RadioButton5.isChecked()){
                    Toast.makeText(this, R.string.Submitted_successfully, Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            case R.id.RadioButton1:
                RadioButton1.setChecked(true);
                RadioButton2.setChecked(false);
                RadioButton3.setChecked(false);
                RadioButton4.setChecked(false);
                RadioButton5.setChecked(false);
                break;
            case R.id.RadioButton2:
                RadioButton1.setChecked(false);
                RadioButton2.setChecked(true);
                RadioButton3.setChecked(false);
                RadioButton4.setChecked(false);
                RadioButton5.setChecked(false);
                break;
            case R.id.RadioButton3:
                RadioButton1.setChecked(false);
                RadioButton2.setChecked(false);
                RadioButton3.setChecked(true);
                RadioButton4.setChecked(false);
                RadioButton5.setChecked(false);
                break;
            case R.id.RadioButton4:
                RadioButton1.setChecked(false);
                RadioButton2.setChecked(false);
                RadioButton3.setChecked(false);
                RadioButton4.setChecked(true);
                RadioButton5.setChecked(false);
                break;
            case R.id.RadioButton5:
                RadioButton1.setChecked(false);
                RadioButton2.setChecked(false);
                RadioButton3.setChecked(false);
                RadioButton4.setChecked(false);
                RadioButton5.setChecked(true);
                break;

        }
    }
}