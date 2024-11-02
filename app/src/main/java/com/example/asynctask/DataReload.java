package com.example.asynctask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DataReload extends AppCompatActivity {
    Element_Adapter adapter;
    ArrayList<Element_Model> elementList;
    ListView listView;
    ExecutorService executorService;
    Context context =this;
    Handler handler = new Handler(Looper.getMainLooper());



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
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
        startPeriodicRefresh();


        listView.setOnItemClickListener((parent, view, position, id)->{
            Element_Model element = elementList.get(position);

            Intent intent = new Intent(DataReload.this, Edit_Element.class);
            intent.putExtra("position",position);
            intent.putExtra("name", element.getName());
            intent.putExtra("price", element.getPrice());
            intent.putExtra("description", element.getDescription());
            startActivity(intent);

        });

    }
    private void startPeriodicRefresh() {
        executorService.execute(() -> {
            try {
                while (true) {
                    TimeUnit.SECONDS.sleep(5);
                    handler.post(() -> {
                        elementList.clear();
                        try {
                            elementList.addAll(loadData());
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        adapter.notifyDataSetChanged();
                    });
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }



    protected ArrayList<Element_Model> loadData() throws JSONException {

        String jsonString = new ReadJSON(context,"elements.json").getJSONStr();
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


}
