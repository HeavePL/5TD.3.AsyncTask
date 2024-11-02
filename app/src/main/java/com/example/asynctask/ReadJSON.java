package com.example.asynctask;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadJSON {

    private final StringBuilder buf = new StringBuilder();

    public String getJSONStr() {
        return buf.toString();
    }

    public ReadJSON(Context context, String filename) {

        String strJSON;

        InputStream json;
        try {
            File file = new File(context.getDataDir(), "elements.json");

            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader in = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));

            while ((strJSON = in.readLine()) != null) {
                buf.append(strJSON);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
