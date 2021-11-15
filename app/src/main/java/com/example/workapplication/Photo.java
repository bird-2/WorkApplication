package com.example.workapplication;

//在recycleView中我想展示的是一些摄影作品，因此添加了name和imageid这两个属性
public class Photo {
    private String path;
    private int imageid;
    public boolean isStorage;

    public Photo(String path, int imageid, boolean isStorage) {
        this.path = path;
        this.imageid = imageid;
        this.isStorage = isStorage;
    }

    public String getName() {
        return path;
    }

    public int getImageid() {
        return imageid;
    }
}
