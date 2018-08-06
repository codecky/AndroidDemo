package com.example.chenkeyu.recyclerview;

/**
 * Created by chenkeyu on 2018/4/19.
 */

public class Fruit {
    private String name;
    private int imgId;
    public Fruit(String name,int imgId){
        this.name = name;
        this.imgId = imgId;
    }
    public String getName(){
        return name;
    }
    public int getImgId(){
        return imgId;
    }
}
