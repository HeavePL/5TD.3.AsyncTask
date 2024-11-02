package com.example.asynctask;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class SaveJSON {
    public SaveJSON(Context context, String jsonString) throws IOException {
        String path = String.valueOf(context.getDataDir());
        File file = new File(path, "elements.json");
        InputStream inputStream = new ByteArrayInputStream(jsonString.getBytes());
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(jsonString);
        bufferedWriter.close();


    }
}
