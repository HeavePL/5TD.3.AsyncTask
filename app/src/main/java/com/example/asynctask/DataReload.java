package com.example.asynctask;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DataReload extends AppCompatActivity {

    Activity activity;
    Element_Adapter adapter;
    ArrayList<Element_Model> elementList;
    ListView listView;
    ExecutorService executorService;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_all);

        executorService = Executors.newSingleThreadExecutor();

        listView = findViewById(R.id.listView);
        try {
            elementList = loadData();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        adapter = new Element_Adapter(this,elementList);
        listView.setAdapter(adapter);

//        listView.setOnClickListener(());

    }

    protected ArrayList<Element_Model> loadData() throws JSONException {
        String jsonString =  readJson(activity,"elements.json");
        JSONArray jsonArray = new JSONArray(jsonString);
        ArrayList<Element_Model> elements = new ArrayList<>();
        for (int i=0; i<jsonArray.length();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String name = jsonObject.getString("name");
            String description = jsonObject.getString("description");
            String price = jsonObject.getString("price");
            elements.add(new Element_Model(name,price,description));
        }
        return elements;
    }

    protected String readJson(Context context, String filename){
        String strJSON;
        StringBuilder buf = new StringBuilder();
        InputStream json;
        try {
            json = context.getAssets().open(filename);

            BufferedReader in = new BufferedReader(new InputStreamReader(json, "UTF-8"));

            while ((strJSON = in.readLine()) != null) {
                buf.append(strJSON);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return buf.toString();
    }
}
