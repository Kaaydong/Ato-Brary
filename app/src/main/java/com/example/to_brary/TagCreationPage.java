package com.example.to_brary;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.to_brary.data_classes.ArtistsTags;
import com.example.to_brary.data_classes.CharactersTags;
import com.example.to_brary.data_classes.CopyrightsTags;
import com.example.to_brary.data_classes.DetailsTags;
import com.example.to_brary.data_classes.Image;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TagCreationPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TagCreationPage extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ArrayList<String> listOfTags;

    EditText insertTagEditText;
    Spinner tagCategorySpinner;
    Button uploadTagButton;

    public TagCreationPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TagCreationPage.
     */
    // TODO: Rename and change types and number of parameters
    public static TagCreationPage newInstance(String param1, String param2) {
        TagCreationPage fragment = new TagCreationPage();
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
        final View rootview = inflater.inflate(R.layout.fragment_tag_creation_page, container, false);

        createListofTags();

        insertTagEditText = rootview.findViewById(R.id.edittext_taginput_tagcreation);
        uploadTagButton = rootview.findViewById(R.id.button_uploadtag_tagcreation);

        tagCategorySpinner = rootview.findViewById(R.id.spinner_tagcategory_tagcreation);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.spinner_tag_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tagCategorySpinner.setAdapter(adapter);

        tagCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                uploadTagButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tag = insertTagEditText.getText().toString();

                        if(isEmpty(insertTagEditText))
                        {
                            Toast.makeText(getActivity(),"Please Insert Tag To Textbox",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                           tag = removeAllSpaces(tag);
                           tag = tag.toLowerCase();
                        }

                        if(isEmpty(insertTagEditText)) {
                        }
                        else if(checkIfSpacesInbetween(tag))
                        {
                            Toast.makeText(getActivity(),"Please replace spaces with underscores _ ", Toast.LENGTH_LONG).show();
                        }
                        else if(checkIfTagExists(tag))
                        {
                            Toast.makeText(getActivity(), "Tag Already Exists",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            postTag(position,tag);
                        }
                    }
                });
        }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        return rootview;
}

public void postTag(int position, String tagName)
{
    if(position == 0)
    {
        ArtistsTags dataClass = new ArtistsTags();
        dataClass.setArtist(tagName);
        Backendless.Data.of(ArtistsTags.class).save(dataClass, new AsyncCallback<ArtistsTags>() {
            @Override
            public void handleResponse(ArtistsTags response) {
                Toast.makeText(getActivity(),"TAG SUCCESSFULLY UPLOADED",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(getActivity(),"TAG WAS NOT UPLOADED",Toast.LENGTH_SHORT).show();
            }
        });
    }
    else if(position == 1)
    {
        CopyrightsTags dataClass = new CopyrightsTags();
        dataClass.setCopyright(tagName);
        Backendless.Data.of(CopyrightsTags.class).save(dataClass, new AsyncCallback<CopyrightsTags>() {
            @Override
            public void handleResponse(CopyrightsTags response) {
                Toast.makeText(getActivity(),"TAG SUCCESSFULLY UPLOADED",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(getActivity(),"TAG WAS NOT UPLOADED",Toast.LENGTH_SHORT).show();
            }
        });
    }
    else if (position == 2)
    {
        CharactersTags dataClass = new CharactersTags();
        dataClass.setCharacter(tagName);
        Backendless.Data.of(CharactersTags.class).save(dataClass, new AsyncCallback<CharactersTags>() {
            @Override
            public void handleResponse(CharactersTags response) {
                Toast.makeText(getActivity(),"TAG SUCCESSFULLY UPLOADED",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(getActivity(),"TAG WAS NOT UPLOADED",Toast.LENGTH_SHORT).show();
            }
        });
    }
    else if (position == 3)
    {
        DetailsTags dataClass = new DetailsTags();
        dataClass.setDetail(tagName);

        Backendless.Data.of(DetailsTags.class).save(dataClass, new AsyncCallback<DetailsTags>() {
            @Override
            public void handleResponse(DetailsTags response) {
                Toast.makeText(getActivity(),"TAG SUCCESSFULLY UPLOADED",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(getActivity(),"TAG WAS NOT UPLOADED",Toast.LENGTH_SHORT).show();
            }
        });
    }
    getActivity().getFragmentManager().popBackStack();
    Navigation.findNavController(getActivity(), R.id.fragment_navhost_main).navigate(R.id.action_tagCreationPage_to_homeFragment);
}

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }

    private boolean checkIfTagExists(String tag){
        int counter = 0;
        for(int i=0; i<listOfTags.size();i++)
        {
            if(tag.equals(listOfTags.get(i)))
            {
                return true;
            }
        }
        return false;
    }

    private void createListofTags()
    {
        listOfTags = new ArrayList<>();
        Backendless.Data.of(CharactersTags.class).find(new AsyncCallback<List<CharactersTags>>() {
            @Override
            public void handleResponse(List<CharactersTags> response) {
                for(int i=0; i < response.size();i++)
                {
                    listOfTags.add(response.get(i).getCharacter());
                }
            }
            @Override
            public void handleFault(BackendlessFault fault) {
            }
        });
        Backendless.Data.of(ArtistsTags.class).find(new AsyncCallback<List<ArtistsTags>>() {
            @Override
            public void handleResponse(List<ArtistsTags> response) {
                for(int i=0; i < response.size();i++)
                {
                    listOfTags.add(response.get(i).getArtist());
                }
            }
            @Override
            public void handleFault(BackendlessFault fault) {
            }
        });
        Backendless.Data.of(CopyrightsTags.class).find(new AsyncCallback<List<CopyrightsTags>>() {
            @Override
            public void handleResponse(List<CopyrightsTags> response) {
                for(int i=0; i < response.size();i++)
                {
                    listOfTags.add(response.get(i).getCopyright());
                }
            }
            @Override
            public void handleFault(BackendlessFault fault) {
            }
        });
        Backendless.Data.of(DetailsTags.class).find(new AsyncCallback<List<DetailsTags>>() {
            @Override
            public void handleResponse(List<DetailsTags> response) {
                for(int i=0; i < response.size();i++)
                {
                    listOfTags.add(response.get(i).getDetail());
                }
            }
            @Override
            public void handleFault(BackendlessFault fault) {
            }
        });
    }

    private String removeAllSpaces(String rawEditTextString)
    {
        String text = rawEditTextString;
        while (text.indexOf(" ") == 0)
        {
            text = text.substring(1);
        }

        while (text.indexOf(" ") == text.length())
        {
            text = text.substring(0, text.length() - 1);
        }
        return text;
    }

    private boolean checkIfSpacesInbetween(String stringAfterRemoveSpace)
    {
        if(stringAfterRemoveSpace.indexOf(" ") != -1)
        {
            return true;
        }
        return false;
    }
}