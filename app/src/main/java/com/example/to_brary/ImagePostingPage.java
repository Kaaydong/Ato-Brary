package com.example.to_brary;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.files.BackendlessFile;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ImagePostingPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImagePostingPage extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int STORAGE_PERMISSION_CODE = 1;

    private ImageView previewImage;
    private Bitmap imageBitmap;
    private String bitmapName;
    private Button imageSelectionButton, postingButton;
    private AutoCompleteTextView artistsEditText, copyrightEditText, charactersEditText, descriptionsEditText;


    public ImagePostingPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ImagePostingPage.
     */
    // TODO: Rename and change types and number of parameters
    public static ImagePostingPage newInstance(String param1, String param2) {
        ImagePostingPage fragment = new ImagePostingPage();
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
        final View rootView = inflater.inflate(R.layout.fragment_image_posting_page, container, false);

        checkOrRequestionPermission();

        previewImage = rootView.findViewById(R.id.imageView_preview_postimage);
        imageSelectionButton = rootView.findViewById(R.id.button_uploader_postimage);
        postingButton = rootView.findViewById(R.id.button_post_postimage);
        artistsEditText = rootView.findViewById(R.id.autoCompleteTextView_artists_postimage);
        copyrightEditText = rootView.findViewById(R.id.autoCompleteTextView_copyright_postimage);
        charactersEditText = rootView.findViewById(R.id.autoCompleteTextView_characters_postimage);
        descriptionsEditText = rootView.findViewById(R.id.autoCompleteTextView_descriptions_postimage);

        setListeners();

        return rootView;
    }


    public void setListeners()
    {
        imageSelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        postingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageBitmap == null)
                {
                    Toast.makeText(getActivity(),"PLEASE SELECT AN IMAGE",Toast.LENGTH_SHORT).show();
                }
                else if(isEmpty(artistsEditText) || isEmpty(copyrightEditText) ||
                        isEmpty(charactersEditText) || isEmpty(descriptionsEditText))
                {
                    Toast.makeText(getActivity(),"PLEASE FILL OUT EVERY BOX",Toast.LENGTH_SHORT).show();
                }
                else{
                    uploadImageFile();
                }
            }
        });
    }


    public void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, 100);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            Uri targetUri = data.getData();

            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(targetUri));
                imageBitmap = bitmap;
                bitmapName = bitmap.toString();
                previewImage.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {

                e.printStackTrace();
            }
        }
    }

    public void uploadImageFile()
    {

        Backendless.Files.Android.upload(imageBitmap, Bitmap.CompressFormat.JPEG, 100, bitmapName, "images_folder", new AsyncCallback<BackendlessFile>() {
            @Override
            public void handleResponse(BackendlessFile response) {

                Image theImage = new Image();
                theImage.setImageFile(response.getFileURL());
                theImage.setArtists(StringsToJson(artistsEditText));
                theImage.setCopyright(StringsToJson(copyrightEditText));
                theImage.setCharacters(StringsToJson(charactersEditText));
                theImage.setDetails(StringsToJson(descriptionsEditText));

                HashMap hashMap = new HashMap();
                hashMap.put("imageFile", response.getFileURL());
                hashMap.put("artists", StringsToJson(artistsEditText));
                hashMap.put("copyright", StringsToJson(copyrightEditText));
                hashMap.put("characters", StringsToJson(charactersEditText));
                hashMap.put("details", StringsToJson(descriptionsEditText));

                Backendless.Data.of(Image.class).save(theImage, new AsyncCallback<Image>() {
                    @Override
                    public void handleResponse(Image response) {
                        Toast.makeText(getActivity(),"IMAGE SUCCESSFULLY UPLOADED",Toast.LENGTH_SHORT).show();

                        Gson gson = new Gson();
                        String json = gson.toJson(response);

                        Bundle bundle = new Bundle();
                        bundle.putString("bundle",json);

                        getActivity().getFragmentManager().popBackStack();
                        Navigation.findNavController(getActivity(), R.id.fragment_navhost_main).navigate(R.id.action_imagePostingPage_to_imageViewFragment, bundle);
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(getActivity(),"IMAGE WAS NOT UPLOADED",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(getActivity(),"PROBLEMS WITH IMAGE UPLOAD, TRY AGAIN LATER",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String StringsToJson(AutoCompleteTextView textView)
    {
        ArrayList<String> arrayList = new ArrayList<>();

        String text = textView.getText().toString();

        while (text.indexOf(" ") == 0)
        {
            text = text.substring(1);
        }

        while (text.indexOf(" ") == text.length())
        {
            Log.e("THIS HAPPENS","HELLO");
            text = text.substring(0, text.length() - 1);
        }

        if (text.indexOf(" ") == -1)
        {

            return "[\"" + text + "\"]";
        }

        while (text.indexOf(" ") != -1)
        {
            String tag = text.substring(0,text.indexOf(" "));
            arrayList.add(tag);
            text = text.substring(text.indexOf(" ") + 1);
        }

        String json = new Gson().toJson(arrayList);
        return json;
    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }

    private void checkOrRequestionPermission()
    {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_GRANTED)
        {
            Log.i("Permissions", "Access to photos already granted");
        }
        else
        {
            requestStoragePermission();
        }
    }

    private void requestStoragePermission()
    {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE))
        {
            new AlertDialog.Builder(getActivity())
                    .setTitle("Permission Needed")
                    .setMessage("Access to Photos Needed for Image Uploading")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Navigation.findNavController(getActivity(), R.id.fragment_navhost_main).navigateUp();
                        }
                    })
                    .create().show();
        }
        else
        {
            //requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//                if (requestCode == STORAGE_PERMISSION_CODE)
//        {
//            if (grantResults.length > 0  && grantResults[0] == PackageManager.PERMISSION_GRANTED)
//            {
//                Toast.makeText(getActivity(), "App Has Been Given Access to Photos", Toast.LENGTH_SHORT).show();
//            }
//            else
//            {
//                Toast.makeText(getActivity(), "App Has Been Denied Access to Photos", Toast.LENGTH_SHORT).show();
//                Navigation.findNavController(getActivity(), R.id.fragment_navhost_main).navigateUp();
//            }
//        }
//    }
}

