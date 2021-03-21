package com.example.to_brary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.files.BackendlessFile;

import org.json.JSONObject;

import java.util.List;

public class BackendlessOperator {

    Context context;

    private static final String API_KEY = "";
    private static final String APPLICATION_ID = "8F1C82C8-0934-3197-FFC8-E3AF33624600";

    List<Image> defaultImageList;
    String[] images;

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

    public void uploadImageFile()
    {
        Bitmap image = BitmapFactory.decodeResource(context.getResources(),R.drawable.test_image); // might need to change for realistic file uploading from phone

        Backendless.Files.Android.upload(image, Bitmap.CompressFormat.JPEG, 100, "test_image.jpg", "images_folder", new AsyncCallback<BackendlessFile>() {
            @Override
            public void handleResponse(BackendlessFile response) {
              //  Toast.makeText(context,"YAY", Toast.LENGTH_SHORT).show();
                String url = response.getFileURL();


            }

            @Override
            public void handleFault(BackendlessFault fault) {
              //  Toast.makeText(context,"NAY", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getImageList() // Alter later for more specific
    {
        Backendless.Data.of(Image.class).find(new AsyncCallback<List<Image>>() {
            @Override
            public void handleResponse(List<Image> response) {
                defaultImageList = response;
                for(int i=0; response.size()>i; i++)
                {
                    images = new String[response.size()];
                    images[i] = response.get(i).getImageFile();
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });

    }

    public List<Image> getDefaultImageList()
    {
        return defaultImageList;
    }

    public String[] getImages()
    {
        return images;
    }

    public void wait(int millisec) {
        try {
            Thread.currentThread().sleep(millisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
