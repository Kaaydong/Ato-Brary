package com.example.to_brary;

import androidx.appcompat.app.AppCompatActivity;

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

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private GridView displayGridView;
    private CustomAdapter customAdapter;
    private int[] images = {R.drawable.test_image, R.drawable.test_image2}; // tester array

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayGridView = findViewById(R.id.gridview_display_mainactivity);

        customAdapter = new CustomAdapter(images,MainActivity.this);
        displayGridView.setAdapter(customAdapter);

        displayGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "DIS WORKS", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private class CustomAdapter extends BaseAdapter {

        private Context context;
        private int[] images;
        private LayoutInflater inflater;

        public CustomAdapter(int[] images, Context context)
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

            if(convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.row_data, parent, false);
            }

            ImageView image = convertView.findViewById(R.id.imageView_picture_rowdata);

            image.setImageResource(images[position]);

            Toast.makeText(MainActivity.this,"hi",Toast.LENGTH_SHORT).show();
            return convertView;
        }
    }
}
