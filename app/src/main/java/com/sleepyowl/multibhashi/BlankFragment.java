package com.sleepyowl.multibhashi;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

/**
 * Created by hp on 9/12/2017.
 */




public class BlankFragment extends Fragment {

    public String[] xyz;
    RecyclerView rv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new http().execute();

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       final View rootView = inflater.inflate(R.layout.fragment_blank, container, false);



        //RecyclerView rv;
        rv = (RecyclerView) rootView.findViewById(R.id.rv_recycler_view);
        rv.setHasFixedSize(true);



        //MyAdapter adapter = new MyAdapter(xyz);

       // rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        return rootView;
    }




        class http extends AsyncTask<URL, String, String> {


            public String xxx="";
            private static final String TAG ="http" ;
            @Override
            protected String doInBackground(URL... urls) {
                HttpURLConnection connection = null;
                BufferedReader reader = null;

                try {
                    URL url = new URL("http://api.icndb.com/jokes/");

                    connection = (HttpURLConnection) url.openConnection();
                    connection.connect();

                    InputStream stream = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(stream));
                    StringBuffer buffer = new StringBuffer();
                     String line = "";
                    while ((line = reader.readLine()) != null) {

                        buffer.append(line);
                    }
                    String finalJson = buffer.toString();
                    JSONObject parentObject = new JSONObject(finalJson);
                    JSONArray parentArray = parentObject.getJSONArray("value");
                    for (int i = 0; i < parentArray.length(); i++)
                   // for (int i = 0; i <3; i++)

                    {
                        JSONObject jsonObject = parentArray.getJSONObject(i);

                        String name = jsonObject.getString("joke");
                        xxx += name+"@#" ;
                        Log.d(TAG,xxx);

                    }
                    return xxx;


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                    try {
                        if (reader != null) {
                            reader.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }

            @Override
            public void onPostExecute(String result) {
               super.onPostExecute(result);
               //xyz = xxx.split("\"");
                xyz = xxx.split("@#");
                //xyz = xxx.split("\\.");
                //xyz = xxx.split("\\.");

                MyAdapter adapter = new MyAdapter(xyz);

                rv.setAdapter(adapter);

            }


        }

    }

