package com.bluetooth.perk.intent_transfer_object;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by perk on 2016/7/15.
 */

//1、实现Parcelable接口（必须重写 describeContents()（可以返回0） 和 writeToParcel()这两个方法）
public class Person2 implements Parcelable {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override

    //2、 Parcel的 writeXxx()方法将 Person 类中的字段一一“写出
    //字符串型数据就调用 writeString()方法，整型数据就调用 writeInt()方法，以此类推
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name); //  写出name
        parcel.writeInt(age); //  写出age
    }

    //3、在 Person 类中提供一个名为 CREATOR 的常量
    // 创建了Parcelable.Creator 接口的一个实现，并将泛型指定为 Person

    public static final Creator<Person2> CREATOR = new Creator<Person2>() {
        @Override
        public Person2 createFromParcel(Parcel in) {
            Person2 person2 = new Person2();
            //注意这里读取的顺序一定要和刚才写出的顺序完全相同
            person2.name = in.readString();//  读取name
            person2.age = in.readInt();//  读取age
            return person2;
        }

        @Override
        public Person2[] newArray(int size) {
            return new Person2[size];
        }
    };


}
