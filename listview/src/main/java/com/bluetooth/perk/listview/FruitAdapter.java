package com.bluetooth.perk.listview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by perk on 2016/7/15.
 */
public class FruitAdapter extends ArrayAdapter<Fruit> {
    private int item_format_resourceId;

    public FruitAdapter(Context context, int itemViewResourceId, List<Fruit> objects) {
        super(context, itemViewResourceId, objects);
        item_format_resourceId = itemViewResourceId;
    }

    class ViewHolder {//用于对控件的实例进行缓存
        ImageView fruitImage;
        TextView fruitName;
    }

    @Override//这个方法在每个子项“一旦被滚动到屏幕内哪怕是一点点”的时候会被调用不论之前的item是否出现过
    public View getView(int position, View convertView, ViewGroup parent) {
        //convertView：用于将之前加载好的布局进行缓存
        Log.i("zgl", "position = " + getItemId(position));
        Fruit fruit = getItem(position); // 获取当前项的Fruit实例
        View view;
        ViewHolder viewHolder;
        /*
        * 如果 convertView 为空，则使用LayoutInflater 去加载"布局"
        * 如果不为空则直接对convertView进行重用
        * 在快速滚动的时候也可以表现出更好的性能
        * */
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(item_format_resourceId, null);

            viewHolder = new ViewHolder();
            viewHolder.fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            viewHolder.fruitName = (TextView) view.findViewById(R.id.fruit_name);

            view.setTag(viewHolder);//将viewHolder存储在View中
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();//将convertView保存的“布局”和“控件”
        }

//        获取到 ImageView 和 TextView 的实例
//        ImageView fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
//        TextView fruitName = (TextView) view.findViewById(R.id.fruit_name);
//
//        fruitImage.setImageResource(fruit.getImageId());
//        fruitName.setText(fruit.getName());
        viewHolder.fruitImage.setImageResource(fruit.getImageId());
        viewHolder.fruitName.setText(fruit.getName());
        return view;

    }
}
