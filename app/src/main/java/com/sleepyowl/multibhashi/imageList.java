package com.sleepyowl.multibhashi; /**
 * Created by hp on 9/16/2017.
 */
import android.graphics.Bitmap;
public class imageList {

    private String url;
    private Bitmap image;

    public imageList() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {

        this.url = url;


    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
