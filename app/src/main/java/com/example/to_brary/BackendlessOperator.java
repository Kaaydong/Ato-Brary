package com.example.to_brary;

import android.content.Context;

import com.backendless.Backendless;

public class BackendlessOperator {

    Context context;

    public static final String API_KEY = "INSERT KEY";
    public static final String APPLICATION_ID = "Insert APP ID";

    public BackendlessOperator(Context context)
    {
        this.context = context;

        Backendless.initApp(context, APPLICATION_ID, API_KEY);
    }

    public void uploadImage()
    {

    }

}
