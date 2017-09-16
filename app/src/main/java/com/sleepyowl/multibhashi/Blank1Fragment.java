package com.sleepyowl.multibhashi;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

import static com.sleepyowl.multibhashi.R.id.recyclerView;

/**
 * Created by hp on 9/12/2017.
 */




public class Blank1Fragment extends Fragment {
    RecyclerView rv;
    public String[] xyv;
    public Bitmap[] abc;
    private Config config;
    public Blank1Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new fetchdata().execute();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_blank1, container, false);

         rv = (RecyclerView) rootView.findViewById(R.id.rv1_recycler_view);
        rv.setHasFixedSize(true);
        //MyAdapter1 adapter1 = new MyAdapter1(new String[]{"Example One", "Example Two", "Example Four", "Example Five", "Example Six", "Example Seven"});
       // rv.setAdapter(adapter1);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        return rootView;
    }

    public class fetchdata  extends AsyncTask<Void, Void, String> {


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            parseJSON(s);
        }

        @Override
        protected String doInBackground(Void... params) {
            BufferedReader bufferedReader = null;
            try {
                URL url = new URL(Config.GET_URL);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                StringBuilder sb = new StringBuilder();

                bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                String json;
                while ((json = bufferedReader.readLine()) != null) {
                    sb.append(json + "\n");
                }

                return sb.toString().trim();

            } catch (Exception e) {
                return null;
            }
        }
    }

    public void showData(){
        MyAdapter1 adapter = new MyAdapter1( Config.bitmaps);
        rv.setAdapter(adapter);
    }

    private void parseJSON(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray array = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            config = new Config(array.length());

            for(int i=0; i<array.length(); i++){
                JSONObject j = array.getJSONObject(i);
                //Config.names[i] = getName(j);
                Config.urls[i] = getURL(j);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        GetBitmap gb = new GetBitmap(this, Config.urls);
        gb.execute();
    }
//
//    private String getName(JSONObject j){
//        String name = null;
//        try {
//            name = j.getString(Config.TAG_IMAGE_NAME);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return name;
//    }

    private String getURL(JSONObject j){
        String url = null;
        try {
            url = j.getString(Config.TAG_IMAGE_URL);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return url;
    }


}