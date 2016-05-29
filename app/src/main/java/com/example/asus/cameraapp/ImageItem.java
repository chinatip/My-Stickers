package com.example.asus.cameraapp;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.File;

/**
 * Created by Asus on 28/5/2559.
 */
public class ImageItem {
    private String title, path;

    public ImageItem(String title, String path) {
        super();
        this.title = title;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
