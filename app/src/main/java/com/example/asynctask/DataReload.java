package com.example.asynctask;

import android.app.Activity;
import android.os.AsyncTask;

public class DataReload extends AsyncTask<Void, Void, Void> {

    Activity activity;

    public DataReload(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Thread.sleep(5000);

        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return null;
    }
}
