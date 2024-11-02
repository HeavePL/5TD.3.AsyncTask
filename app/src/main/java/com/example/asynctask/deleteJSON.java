package com.example.asynctask;

import android.annotation.SuppressLint;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class deleteJSON {
    @SuppressLint("NewApi")
    public deleteJSON(Context context) {
        File file = new File(context.getDataDir(), "elements.json");
        boolean deleted = file.delete();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name","test");
            jsonObject.put("price","test");
            jsonObject.put("description","test");
            JSONArray jsonArray = new JSONArray("[]");
            jsonArray.put(jsonObject);
            new SaveJSON(context.getApplicationContext(), jsonArray.toString());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
