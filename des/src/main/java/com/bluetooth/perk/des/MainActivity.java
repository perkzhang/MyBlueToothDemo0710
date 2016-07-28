package com.bluetooth.perk.des;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.bluetooth.perk.des.R.id.ed_des_content;

public class MainActivity extends AppCompatActivity {


    //密码，长度要是8的倍数
    String password = "958802882010913257074332531189842634" +
            "7857298773549468758875018579537757772163084478" +
            "8736994473060344662006164119605741224340594691" +
            "00235892702736860872901247123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText mDesContent = (EditText) findViewById(ed_des_content);
        Button mDes = (Button) findViewById(R.id.but_des);
        mDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = mDesContent.getText().toString();//加密内容
                byte[] result = DesUtils.encrypt(content.getBytes(), password);
                //startActivity(new Intent(MainActivity.this,DisplayActivity.class).putExtra("des",result));
                Log.d("zgl", "result = " + result);// result = [B@9b0403b地址
                Log.d("zgl", "result = " + new String(result));// result = z�褂���
                byte[] decryptresult = new byte[]{};
                try {
                    decryptresult = DesUtils.decrypt(result, password);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("zgl", "decryptresult = " + decryptresult.toString());//decrypt = [B@c9be596（内存地址）
                Log.d("zgl", "decryptresult = " + new String(decryptresult));//decrypt = 所以说（测试内容）


            }
        });
    }


}
