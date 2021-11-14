package com.example.workapplication;

//在recycleView中我想展示的是一些摄影作品，因此添加了name和imageid这两个属性
public class Photo {
    private String name;
    private int imageid;

    public Photo(String name, int imageid) {
        this.name = name;
        this.imageid = imageid;
    }

    public String getName() {
        return name;
    }

    public int getImageid() {
        return imageid;
    }
}
