package com.example.to_brary;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.example.to_brary.data_classes.Image;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private GridView displayGridView;
    private GridViewAdapter customAdapter;
    private int[] pictures = {R.drawable.test_image, R.drawable.test_image2}; // tester array


    private static final String APPLICATION_ID = "8F1C82C8-0934-3197-FFC8-E3AF33624600";

    private List<Image> imageList;
    private String[] images;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Inflate the layout for this fragment
        //   return inflater.inflate(R.layout.fragment_home,null);

        displayGridView = (GridView) rootView.findViewById(R.id.gridview_display_home);
        getDefaultImageList();


        displayGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Gson gson = new Gson();
                String json = gson.toJson(imageList.get(position));

                Bundle bundle = new Bundle();
                bundle.putString("bundle",json);

                getActivity().getFragmentManager().popBackStack();
                Navigation.findNavController(getActivity(), R.id.fragment_navhost_main).navigate(R.id.action_homeFragment_to_imageViewFragment, bundle);

            }
        });

        return rootView;
    }

    public void getDefaultImageList()
    {
        ApiKey key = new ApiKey();
        Backendless.initApp(getActivity(),APPLICATION_ID, key.getApiKey());


       DataQueryBuilder queryBuilder = DataQueryBuilder.create();
       queryBuilder.setPageSize( 20 ).setOffset( 0 );

        Backendless.Data.of(Image.class).find(queryBuilder, new AsyncCallback<List<Image>>() {
            @Override
            public void handleResponse(List<Image> response) {
                Log.e("HELLO",response.size()+"");
                imageList = response;

                String listFromActvity = ((MainActivity)getActivity()).getItemsList();

                if(listFromActvity != "thereIsNoGodDamnWaySomeoneIsPuttingThisIntoTHeTextView") {
                    listFromActvity = listFromActvity.toLowerCase();
                    ArrayList<String> tagsList = stringsToJson(listFromActvity);

                    for(int i = 0; i < tagsList.size();i++)
                    {
                        createCustomImageList(tagsList.get(i));
                    }
                }

                images = new String[imageList.size()];

                for(int i=0; imageList.size()>i; i++)
                {
                    images[i] = imageList.get(i).getImageFile();
                }


                customAdapter = new GridViewAdapter(images, getActivity());
                displayGridView.setAdapter(customAdapter);

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e("NOTICE", "U FUCKING DONKEY");
            }
        });
    }

    public void createCustomImageList(String searchTerm)
    {
        String word = searchTerm;
        word = "\"" + word + "\"";
        ArrayList<Image> newImageList = new ArrayList<>();

        for (int i = 0; i < imageList.size();i++)
        {
            String combinedString = imageList.get(i).getArtists() + imageList.get(i).getCopyright() + imageList.get(i).getCharacters() + imageList.get(i).getDetails();

            if (combinedString.indexOf(word) != -1)
            {
                newImageList.add(imageList.get(i));
            }
        }

        imageList = newImageList;
    }

    public ArrayList<String> stringsToJson(String text)
    {
        ArrayList<String> arrayList = new ArrayList<>();
        String word = text;

        while (word.indexOf(" ") == 0)
        {
            word = word.substring(1);
        }

        while (word.endsWith(" "))
        {
            word = word.substring(0, word.length()-1);
        }

        if(word.indexOf(" ") == -1)
        {
            arrayList.add(word);
            return arrayList;
        }


        while (word.indexOf(" ") != -1)
        {
            String tag = word.substring(0,word.indexOf(" "));
            arrayList.add(tag);
            word = word.substring(word.indexOf(" ")+1);
        }

        if(word.indexOf(" ") == -1)
        {
            arrayList.add(word);
            return arrayList;
        }
        return  arrayList;
    }
}



