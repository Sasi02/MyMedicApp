package com.example.user.mymedic.Helper;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.user.mymedic.Model.Drug;
import com.example.user.mymedic.R;

import java.util.ArrayList;

/**
 * Created by User on 9/29/2018.
 */

public class DrugSpinner extends BaseAdapter {
    private ArrayList<Drug> drugs;
    private Activity activity;
    private LayoutInflater inflater;

    public DrugSpinner(ArrayList<Drug> drugs, Activity activity) {
        this.drugs = drugs;
        this.activity = activity;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return drugs.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(convertView == null)
            view = inflater.inflate(R.layout.activity_custom_list_view_drug,null);

        Drug singleDrugItem = (Drug) this.getItem(position);

        TextView manufacturer = (TextView)view.findViewById(R.id.manufacturer);
        TextView name = (TextView)view.findViewById(R.id.dname);
        TextView dosage = (TextView)view.findViewById(R.id.DrDosage);

        manufacturer.setText(singleDrugItem.getManufacturer());
        name.setText(singleDrugItem.getName());
        dosage.setText(String.valueOf(singleDrugItem.getDosage()));

        return view;
    }
}
