package com.sleepyowl.multibhashi;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.ViewHolder> {

    List<imageList> items;

    public MyAdapter1( Bitmap[] images){
        super();
        items = new ArrayList<imageList>();
        for(int i =0; i<images.length; i++){
            imageList item = new imageList();

           // item.setUrl(urls[i]);
            item.setImage(images[i]);
            items.add(item);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card1_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        imageList list =  items.get(position);
        holder.imageView.setImageBitmap(list.getImage());
      //  holder.textViewName.setText(list.getName());
     //   holder.textViewUrl.setText(list.getUrl());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
       // public TextView textViewName;
      //  public TextView textViewUrl;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.load_image);
          //  textViewName = (TextView) itemView.findViewById(R.id.textViewName);
                // textViewUrl = (TextView) itemView.findViewById(R.id.textViewUrl);

        }
    }
}