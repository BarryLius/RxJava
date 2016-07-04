package com.liuwei.rxjava.bean;

import java.util.List;

/**
 * Created by mhl on 2016/6/23.
 */
public class Stduent {
    private String name;
    private List<Course> list;

    public List<Course> getList() {
        return list;
    }

    public void setList(List<Course> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
