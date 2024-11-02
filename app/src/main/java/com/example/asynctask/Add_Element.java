package com.example.asynctask;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;

public class Add_Element extends AppCompatActivity {




    Button addButton;
    EditText name, price, description;
    Context context = this;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_new);

        addButton =  findViewById(R.id.buttonAddNew);
        name = findViewById(R.id.elementNameEditText);
        price = findViewById(R.id.elementPriceEditText);
        description = findViewById(R.id.elementDescriptionEditText);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String jsonString =  new ReadJSON(context,"elements.json").getJSONStr();
                JSONObject newElementJSON = new JSONObject();

                String nameStr = String.valueOf(name.getText());
                String priceStr = String.valueOf(price.getText());
                String descStr = String.valueOf(description.getText());

                if (descStr.isEmpty()){
                    descStr = "";
                }
                if (nameStr.isEmpty() || priceStr.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Prosze uzupelnic wszystkie dane!", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        Toast.makeText(getApplicationContext(), "Poprawnie dodano element!", Toast.LENGTH_SHORT).show();
                        newElementJSON.put("name",nameStr);
                        newElementJSON.put("description",descStr);
                        newElementJSON.put("price",priceStr);
                        JSONArray jsonArray = new JSONArray(jsonString);
                        jsonArray.put(newElementJSON);
                        new SaveJSON(context,jsonArray.toString());
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);

                    }
                    finish();
                }

            }
        });
    }
}
