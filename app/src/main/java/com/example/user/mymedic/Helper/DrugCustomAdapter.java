package com.example.user.mymedic.Helper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.user.mymedic.Model.Drug;
import com.example.user.mymedic.R;

import java.util.ArrayList;
import java.util.List;

public class DrugCustomAdapter extends ArrayAdapter<Drug>{

    public DrugCustomAdapter(Context context, ArrayList<Drug> drugs) {
        super(context, R.layout.activity_custom_list_view_drug, drugs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.activity_custom_list_view_drug, parent, false);

        Drug singleDrugItem = getItem(position);

        TextView manufacturer = (TextView)customView.findViewById(R.id.manufacturer);
        TextView name = (TextView)customView.findViewById(R.id.dname);
        TextView dosage = (TextView)customView.findViewById(R.id.DrDosage);

        manufacturer.setText(singleDrugItem.getManufacturer());
        name.setText(singleDrugItem.getName());
        dosage.setText(String.valueOf(singleDrugItem.getDosage()));

        return customView;
    }


}
