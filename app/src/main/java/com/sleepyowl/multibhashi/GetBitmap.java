package com.sleepyowl.multibhashi;

/**
 * Created by hp on 9/17/2017.
 */
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import org.json.JSONObject;

import java.net.URL;



public class GetBitmap extends AsyncTask<Void,Void,Void> {

  //  private Context context;
    private String[] urls;
    private Blank1Fragment mainActivity;

    public GetBitmap(Blank1Fragment mainActivity, String[] urls){
      //  this.context = context;
        this.urls = urls;
        this.mainActivity = mainActivity;
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
     //   loading.dismiss();
        mainActivity.showData();
    }

    @Override
    protected Void doInBackground(Void... params) {
        for(int i=0; i<urls.length; i++){
            Config.bitmaps[i] = getImage(urls[i]);
        }
        return null;
    }

    private Bitmap getImage(String bitmapUrl){
        URL url;
        Bitmap image = null;
        try {
            url = new URL(bitmapUrl);
            image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        }catch(Exception e){}
        return image;
    }
}
