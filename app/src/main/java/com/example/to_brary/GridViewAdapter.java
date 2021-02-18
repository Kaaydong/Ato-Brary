package com.example.to_brary;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class GridViewAdapter extends BaseAdapter {

    private Context context;
    private int[] images;
    private LayoutInflater inflater;

    public GridViewAdapter(int[] images, Context context)
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

        image.setImageResource(images[position]);

        return view;
    }
}
