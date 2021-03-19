package com.francisnd.natureapplication;

public class DataHandler {
    String title, thumbnail, image;

    public DataHandler(String title, String thumbnail, String image) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getImage() {
        return image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
