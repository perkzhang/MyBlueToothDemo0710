package com.bluetooth.perk.intent_transfer_object;

import java.io.Serializable;

/**
 * Created by perk on 2016/7/15.
 */

//假设有一个 Person 类，其中包含了 name 和 age 这两个字段

public class Person implements Serializable {
    //最重要的的是实现Serializable接口，这样所有的 Person 对象就都是可序列化的了
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

}
