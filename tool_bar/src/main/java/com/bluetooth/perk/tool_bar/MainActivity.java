package com.bluetooth.perk.tool_bar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        在 MainActivity.java 中加入 Toolbar 的声明：
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        再将之用 setSupportActionBar 设定，Toolbar即能取代原本的 actionbar
        setSupportActionBar(toolbar);

//        设置APP 的图标。
        toolbar.setLogo(R.mipmap.ic_launcher);
//        设置Title
        toolbar.setTitle("My Title");
//        设置Sub Title(分标题)
        toolbar.setSubtitle("Sub title");


        setSupportActionBar(toolbar);
        // Navigation Icon 要設定在 setSupoortActionBar 才有作用
        // 否則會出現 back button

        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        //和 setNavigationIcon 一样，需要將之设定在 setSupportActionBar 之后才有作用
        toolbar.setOnMenuItemClickListener(onMenuItemClickListener);

    }

    private Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            String msg = "";
            switch (item.getItemId()) {
                case R.id.action_edit:
                    msg += "Click edit";
                    break;
                case R.id.action_share:
                    msg += "Click share";
                    break;
                case R.id.action_settings:
                    msg += "Click setting";
                    break;
            }

            if (!msg.equals("")) {
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    };

}
