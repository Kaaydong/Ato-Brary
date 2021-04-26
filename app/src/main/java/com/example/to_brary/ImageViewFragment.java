package com.example.to_brary;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import static com.backendless.rt.RTTypes.log;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ImageViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImageViewFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ImageView imageView;
    private TextView artistsTextView, copyrightTextView, charactersTextView, descriptionsTextView;
    private Image image;
    private Button downloadButton;

    public ImageViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ImageViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ImageViewFragment newInstance(String param1, String param2) {
        ImageViewFragment fragment = new ImageViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_image_view, container, false);

        downloadButton = rootView.findViewById(R.id.button_imagePage_downloadImage);

        imageView = rootView.findViewById(R.id.imageView_imagePage_image);
        artistsTextView = rootView.findViewById(R.id.textView_imagePage_artists);
        copyrightTextView = rootView.findViewById(R.id.textView_imagePage_copyright);
        charactersTextView = rootView.findViewById(R.id.textView_imagePage_characters);
        descriptionsTextView = rootView.findViewById(R.id.textView_imagePage_descriptions);

        setListeners();

        String json = getArguments().getString("bundle");

        Gson gson = new Gson();
        image = gson.fromJson(json, Image.class);

        Picasso.get().load(image.getImageFile()).into(imageView);
        artistsTextView.setText(image.getArtists());
        copyrightTextView.setText(image.getCopyright());
        charactersTextView.setText(image.getCharacters());
        descriptionsTextView.setText(image.getDetails());

        return rootView;
    }

    public void setListeners()
    {
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
                Bitmap bitmap = drawable.getBitmap();

                String destPath = getActivity().getExternalFilesDir(null).getAbsolutePath();
                File directory = new File(destPath + "/Ato-Brary Image/");
                directory.mkdir();
                File file = new File(directory, System.currentTimeMillis() + ".jpg");
                try{FileOutputStream outputStream = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
                    Toast.makeText(getActivity(),"Image Has Successfully Been Downloaded",Toast.LENGTH_SHORT).show();
                    }
                catch (FileNotFoundException e){
                    e.printStackTrace();
                    Toast.makeText(getActivity(),"Image Was Not Downloaded",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

}