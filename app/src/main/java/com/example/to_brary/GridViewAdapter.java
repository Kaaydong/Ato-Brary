package com.example.to_brary;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class GridViewAdapter extends BaseAdapter {

    private Context context;
    private String[] images;
    private LayoutInflater inflater;
    private List<Image> defaultClassImageList;


    public GridViewAdapter(String[] images, Context context)
    {
        this.context = context;
        this.images = images;

        this.inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(R.layout.row_data, null);


        ImageView image = view.findViewById(R.id.imageView_picture_rowdata);

        Picasso.get().load(images[position]).into(image);
      //  image.setImageResource(images[position]);

        return view;
    }

}
