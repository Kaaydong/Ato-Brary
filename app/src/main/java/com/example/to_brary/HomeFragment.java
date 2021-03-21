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

    private static final String API_KEY = "";
    private static final String APPLICATION_ID = "8F1C82C8-0934-3197-FFC8-E3AF33624600";

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

                getActivity().getFragmentManager().popBackStack();
                Navigation.findNavController(getActivity(), R.id.fragment_navhost_main).navigate(R.id.action_homeFragment_to_imageViewFragment);

            }
        });

        return rootView;
    }

    public void getDefaultImageList()
    {
        Backendless.initApp(getActivity(),APPLICATION_ID, API_KEY);

        Backendless.Data.of(Image.class).find(new AsyncCallback<List<Image>>() {
            @Override
            public void handleResponse(List<Image> response) {

                for(int i=0; response.size()>i; i++)
                {
                    images = new String[response.size()];
                    images[i] = response.get(i).getImageFile();
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
}

