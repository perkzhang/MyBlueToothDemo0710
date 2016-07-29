package com.bluetooth.perk.spinner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private AppCompatSpinner mSpinner;
    private Button mShowSnack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSpinner = (AppCompatSpinner) findViewById(R.id.spinner1);

        // 建立数据源
        String[] items = getResources().getStringArray(R.array.spinnername);
        // 建立Adapter并且绑定数据源
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);//用系统的布局
        //绑定 Adapter到控件
        mSpinner.setAdapter(adapter);

        mSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String[] city = getResources().getStringArray(R.array.spinnername);
            }
        });

        mShowSnack = (Button) findViewById(R.id.btn_show);
    }


}
