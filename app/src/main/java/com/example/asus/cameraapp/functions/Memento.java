package com.example.asus.cameraapp.functions;

import android.graphics.Bitmap;

public class Memento {
    private Bitmap bitmap;

    public Memento(Bitmap state){
        this.bitmap = state;
    }

    public Bitmap getState(){
        return bitmap;
    }
}
