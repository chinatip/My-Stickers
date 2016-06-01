package com.example.asus.cameraapp.functions;

import android.graphics.Bitmap;

public class Originator {
    private Bitmap state;

    public void setState(Bitmap state){
        this.state = state;
    }

    public Bitmap getState(){
        return state;
    }

    public Memento saveStateToMemento(){
        return new Memento(state);
    }

    public void getStateFromMemento(Memento Memento){
        state = Memento.getState();
    }
}