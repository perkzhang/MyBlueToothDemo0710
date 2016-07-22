package com.bluetooth.perk.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String[] data = {"Apple", "Banana", "Orange",
            "Watermelon", "Pear", "Grape", "Pineapple",
            "Strawberry", "Cherry", "Mango", "Cat", "Dog", "大象"};
    private ListView mListView;
    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initFruit();//初始化数据
        mListView = (ListView) findViewById(R.id.list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);

        FruitAdapter fruitAdapter = new FruitAdapter(this, R.layout.fruit_item, fruitList);

        mListView.setAdapter(fruitAdapter);
        listviewListenner();

    }

    private void initFruit() {
        Fruit Apple = new Fruit("Apple", R.mipmap.ic_launcher);
        Fruit Banana = new Fruit("Banana", R.mipmap.ic_launcher);
        Fruit Watermelon = new Fruit("Watermelon", R.mipmap.ic_launcher);
        Fruit Orange = new Fruit("Orange", R.mipmap.ic_launcher);
        Fruit Pear = new Fruit("Pear", R.mipmap.ic_launcher);
        Fruit Grape = new Fruit("Grape", R.mipmap.ic_launcher);
        Fruit Pineapple = new Fruit("Pineapple", R.mipmap.ic_launcher);
        Fruit Strawberry = new Fruit("Strawberry", R.mipmap.ic_launcher);
        Fruit Cherry = new Fruit("Cherry", R.mipmap.ic_launcher);
        Fruit Mango = new Fruit("Mango", R.mipmap.ic_launcher);
        Fruit Cat = new Fruit("Cat", R.mipmap.ic_launcher);
        Fruit Dog = new Fruit("Dog", R.mipmap.ic_launcher);
        fruitList.add(Apple);
        fruitList.add(Banana);
        fruitList.add(Watermelon);
        fruitList.add(Orange);
        fruitList.add(Pear);
        fruitList.add(Pineapple);
        fruitList.add(Grape);
        fruitList.add(Strawberry);
        fruitList.add(Cherry);
        fruitList.add(Mango);
        fruitList.add(Cat);
        fruitList.add(Dog);


    }

    private void listviewListenner() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = fruitList.get(i).getName();
                Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
            }
        });
    }

//    ListView中的数据也是用一个数组保存起来然后再通过adapter设置进去

}
