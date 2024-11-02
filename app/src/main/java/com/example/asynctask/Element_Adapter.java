package com.example.asynctask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Element_Adapter extends ArrayAdapter<Element_Model> {

    public Element_Adapter(Context context, List<Element_Model> products) {
        super(context, 0, products);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Element_Model element = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.element,parent,false);
        }

        TextView nameTextView = convertView.findViewById(R.id.textViewName);
        TextView priceTextView = convertView.findViewById(R.id.textViewPrice);
        TextView descTextView = convertView.findViewById(R.id.textViewDescription);

        nameTextView.setText(element.getName());
        priceTextView.setText(element.getPrice());
        descTextView.setText(element.getDescription());

        return convertView;
    }
}
