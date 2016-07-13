package com.rostdev.survivalpack.model;

/**
 * Created by Rosty on 7/7/2016.
 */
public class SensorInfo {

    private int image;
    private String title;
    private String info;

    public SensorInfo(int image, String title, String info) {
        this.image = image;
        this.title = title;
        this.info = info;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return info;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SensorInfo that = (SensorInfo) o;

        return title.equals(that.title);

    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
