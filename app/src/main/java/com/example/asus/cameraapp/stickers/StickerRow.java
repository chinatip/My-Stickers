package com.example.asus.cameraapp.stickers;

/**
 * Created by Asus on 31/5/2559.
 */
public class StickerRow {
    private int sticker1;
    private int sticker2;
    private int sticker3;
    private int sticker4;

    public StickerRow(int sticker1, int sticker2, int sticker3, int sticker4) {
        super();
        this.sticker1 = sticker1;
        this.sticker2 = sticker2;
        this.sticker3 = sticker3;
        this.sticker4 = sticker4;
    }

    public int getSticker1() {
        return sticker1;
    }

    public void setSticker1(int sticker1) {
        this.sticker1 = sticker1;
    }

    public int getSticker2() {
        return sticker2;
    }

    public void setSticker2(int sticker2) {
        this.sticker2 = sticker2;
    }

    public int getSticker3() {
        return sticker3;
    }

    public void setSticker3(int sticker3) {
        this.sticker3 = sticker3;
    }

    public int getSticker4() {
        return sticker4;
    }

    public void setSticker4(int sticker4) {
        this.sticker4 = sticker4;
    }

}