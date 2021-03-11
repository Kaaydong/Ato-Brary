package com.example.to_brary;

import android.content.Context;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import org.json.JSONObject;

public class BackendlessOperator {

    Context context;

    public static final String API_KEY = "INSERT KEY";
    public static final String APPLICATION_ID = "Insert APP ID";

    public BackendlessOperator(Context context)
    {
        this.context = context;

    }

    public void uploadImage(JSONObject artists, JSONObject characters, JSONObject copyright,
                            JSONObject details, JSONObject meta, String originalFileSource, String imageFile)

    {
        Image image = new Image();
        image.setArtists(artists);
        image.setCharacters(characters);
        image.setCopyright(copyright);
        image.setDetails(details);
        image.setDetails(details);
        image.setMeta(meta);
        image.setOriginalImageSource(originalFileSource);
        image.setImageFile(imageFile);


        Backendless.Data.of(Image.class).save(image, new AsyncCallback<Image>() {
            @Override
            public void handleResponse(Image response) {
                Toast.makeText(context,"Image Uploaded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }

}
