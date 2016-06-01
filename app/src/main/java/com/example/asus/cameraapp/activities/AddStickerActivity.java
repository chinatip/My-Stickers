package com.example.asus.cameraapp.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.asus.cameraapp.R;
import com.example.asus.cameraapp.functions.CareTaker;
import com.example.asus.cameraapp.functions.Originator;
import com.example.asus.cameraapp.functions.Save;
import com.example.asus.cameraapp.stickers.StickerView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class AddStickerActivity extends AppCompatActivity {
    private Bitmap bitmap, temp;
    private static ImageView image;
    private ImageButton homeButton;
    private ImageButton addButton;
    private ImageButton saveButton;
    private ImageButton shareButton;
    private ImageButton reverseButton;
    private ImageButton infoButton;
    private static Resources resources;
    private static StickerView stickerView;
    Originator originator = new Originator();
    CareTaker careTaker = new CareTaker();
    int countState=-1,tempCount;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsticker);

        resources = getResources();
        bitmap = getIntent().getParcelableExtra("bitmap");
        temp = bitmap;
        image = (ImageView)findViewById(R.id.img_image);
        originator.setState(bitmap);
        countState++;
        careTaker.add(originator.saveStateToMemento());
        stickerView = new StickerView(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        params.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.img_image);
        params.addRule(RelativeLayout.ALIGN_TOP, R.id.img_image);
        ((ViewGroup)image.getParent()).addView(stickerView, params);

        initComponents();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initComponents() {
        image.setImageBitmap(temp);

        homeButton = (ImageButton) findViewById(R.id.btn_home);

        homeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AddStickerActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        addButton = (ImageButton) findViewById(R.id.btn_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AddStickerActivity.this, ChooseActivity.class);
                startActivity(intent);
            }
        });

        saveButton = (ImageButton) findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bitmap sticker = stickerView.getBitmap();
                temp = mergeBitmap(temp, sticker);
                image.setImageBitmap(temp);
                originator.setState(temp);
                careTaker.add(originator.saveStateToMemento());
                countState++;
                Save saveFile = new Save();
                saveFile.SaveImage(getApplicationContext(), temp);
            }
        });

        shareButton = (ImageButton) findViewById(R.id.btn_share);
        shareButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                OutputStream output;

                File filepath = Environment.getExternalStorageDirectory();

                File dir = new File(filepath.getAbsolutePath() + "/Share Image/");
                dir.mkdirs();

                File file = new File(dir, "Image.png");

                try {
                    Intent share = new Intent(Intent.ACTION_SEND);
                    share.setType("image/jpeg");

                    output = new FileOutputStream(file);

                    temp.compress(Bitmap.CompressFormat.PNG, 100, output);
                    output.flush();
                    output.close();

                    Uri uri = Uri.fromFile(file);
                    share.putExtra(Intent.EXTRA_STREAM, uri);

                    startActivity(Intent.createChooser(share, "Share This Image .."));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        reverseButton = (ImageButton) findViewById(R.id.btn_addS_back);
        reverseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(countState>=1)
                countState--;
                Bitmap bitmapState = careTaker.get(countState).getState();
                Matrix matrix = new Matrix();
                Bitmap rotated = Bitmap.createBitmap(bitmapState, 0, 0, bitmap.getWidth(), bitmap.getHeight(),
                        matrix, true);
                image = (ImageView) findViewById(R.id.img_image);
                image.setImageBitmap(rotated);
                temp = bitmapState;
            }
        });

        infoButton = (ImageButton) findViewById(R.id.btn_info);
        infoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AddStickerActivity.this, InfoPopUpActivity.class);
                startActivity(intent);
            }
        });
    }

    public Bitmap mergeBitmap(Bitmap back, Bitmap front){
        Bitmap bitmap = null;
        try {

            bitmap = Bitmap.createBitmap(back.getWidth(), back.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(bitmap);
            Resources res = resources;

            Drawable drawable1 = new BitmapDrawable(back);
            Drawable drawable2 = new BitmapDrawable(front);

            drawable1.setBounds(0, 0, back.getWidth(), back.getHeight());
            drawable2.setBounds(0,0, back.getWidth(), back.getHeight());
            drawable1.draw(c);
            drawable2.draw(c);

        } catch (Exception e) {
        }
        return bitmap;
    }

    public static void setSticker(int id) {
        Bitmap StickerBitmap = BitmapFactory.decodeResource(resources, id);
        stickerView.setWaterMark(StickerBitmap);
    }

}
