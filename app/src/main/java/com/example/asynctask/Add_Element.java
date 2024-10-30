package com.example.asynctask;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.FileOutputStream;

public class Add_Element extends AppCompatActivity {

    Button addButton;
    EditText name, price, description;
    JSONObject jsonObject;
    String filename = "elements.json";
    public Add_Element() {

        addButton =  findViewById(R.id.buttonAddNew);
        name = findViewById(R.id.elementNameEditText);
        price = findViewById(R.id.elementPriceEditText);
        description = findViewById(R.id.elementDescriptionEditText);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FileOutputStream outputStream;



            }
        });
    }

}
