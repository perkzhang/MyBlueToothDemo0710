package com.bluetooth.perk.intent_transfer_object;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FirstActivity extends AppCompatActivity {

    //Serializable是序列化的意思，表示将一个对象转换成“可存储”或“可传输”的“状态”
    //Parcelable 也可以实现相同的效果(该方式的实现原理是将一个完整的对象进行分解)
    //序列化后的“对象”可以在网络上进行传输，也可以存储到本地


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Person person = new Person();
        person.setName("Tom");
        person.setAge(20);

        Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
        intent.putExtra("person_data", person);
        startActivity(intent);
        // 在另外一个界面中通过getSerializableExtra()方法获取传值
        // Person person = (Person) getIntent().getSerializableExtra("person_data");

        Person person2 = new Person();
        person.setName("Javk");
        person.setAge(15);

        Intent intent2 = new Intent(FirstActivity.this, SecondActivity.class);
        intent.putExtra("person_data", person);
        startActivity(intent);
        //接下来在 FirstActivity 中我们仍然可以使用相同的代码来传递 Person 对象
        //在SecondActivity中以
        // Person person = (Person) getIntent().getParcelableExtra("person_data");


    }

    //假设有一个 Person 类，其中包含了 name 和 age 这两个字段


}
