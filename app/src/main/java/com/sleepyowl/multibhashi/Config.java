package com.sleepyowl.multibhashi;

/**
 * Created by hp on 9/16/2017.
 */
import android.graphics.Bitmap;



public class Config {


    public static String[] urls;
    public static Bitmap[] bitmaps;

    public static final String GET_URL = "http://akshaycrt2k.com/getSampleImages.php";
    public static final String TAG_IMAGE_URL = "url";

    public static final String TAG_JSON_ARRAY="images";

    public Config(int i){

        urls = new String[i];
        bitmaps = new Bitmap[i];
    }
}