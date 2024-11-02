package com.example.asynctask;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Edit_Element extends AppCompatActivity {

    private EditText editName, editPrice, editDescription;
    private int position;
    private String originalData;
    private Context context = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent intent = getIntent();
        position = intent.getIntExtra("position", -1);
        String name = intent.getStringExtra("name");
        String price = intent.getStringExtra("price");
        String description = intent.getStringExtra("description");

        editName = findViewById(R.id.productNameEditText);
        editPrice = findViewById(R.id.productPriceEditText);
        editDescription = findViewById(R.id.productDescriptionEditText);
        Button saveButton = findViewById(R.id.saveBtn);
        Button deleteButton = findViewById(R.id.deleteBtn);
        editName.setText(name);
        editPrice.setText(price);
        editDescription.setText(description);

        ReadJSON readJSON = new ReadJSON(context,"elements.json");
        try {
            JSONArray jsonArray = new JSONArray(readJSON.getJSONStr());

            saveButton.setOnClickListener(v -> saveChanges(jsonArray));

            deleteButton.setOnClickListener(v -> deleteElement(jsonArray));

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }





    }

    @SuppressLint("NewApi")
    private void saveChanges(JSONArray jsonArray) {
        String newName = editName.getText().toString();
        String newPrice = editPrice.getText().toString();
        String newDescription = editDescription.getText().toString();
        try {
            jsonArray.getJSONObject(position).put("name",newName);
            jsonArray.getJSONObject(position).put("price",newPrice);
            jsonArray.getJSONObject(position).put("description",newDescription);
            new SaveJSON(context,jsonArray.toString());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finish();
    }

    private void deleteElement(JSONArray jsonArray){
        jsonArray.remove(position);
        try {
            new SaveJSON(context,jsonArray.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finish();
    }
}
