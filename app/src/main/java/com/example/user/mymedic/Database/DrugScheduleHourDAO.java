package com.example.user.mymedic.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.user.mymedic.Model.DrugScheduleHour;
import com.example.user.mymedic.Model.User;

import java.sql.Date;
import java.util.ArrayList;

public class DrugScheduleHourDAO {

    private SQLiteDatabase mDatabase;
    private DatabaseHandler dbHelper;
    private Context mContext;

    public DrugScheduleHourDAO(Context context) {
        this.mContext = context;
        dbHelper = new DatabaseHandler(context);

        try{
            Open();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void Open() throws SQLException {
        mDatabase = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public boolean add(DrugScheduleHour drugScheduleHour){
        ContentValues values = new ContentValues();

        values.put(DatabaseMaster.DrugScheduleHours.COLUMN_NAME_DURATION, drugScheduleHour.getDuration());
        values.put(DatabaseMaster.DrugScheduleHours.COLUMN_NAME_METHOD, drugScheduleHour.getMethod());
        values.put(DatabaseMaster.DrugScheduleHours.COLUMN_NAME_NOOFPILLS, drugScheduleHour.getNoOfPills());

        try {
            long newRowId = mDatabase.insert(DatabaseMaster.DrugScheduleHours.TABLE_NAME, null, values);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<DrugScheduleHour> GetAll(){
        ArrayList<DrugScheduleHour> drugScheduleHourArrayList = new ArrayList<>();

        String[] projection ={
                DatabaseMaster.DrugScheduleHours.COLUMN_NAME_ID,
                DatabaseMaster.DrugScheduleHours.COLUMN_NAME_DRUG_ID,
                DatabaseMaster.DrugScheduleHours.COLUMN_NAME_DURATION,
                DatabaseMaster.DrugScheduleHours.COLUMN_NAME_METHOD,
                DatabaseMaster.DrugScheduleHours.COLUMN_NAME_NOOFPILLS,
                DatabaseMaster.DrugScheduleHours.COLUMN_NAME_PRESCRIPTION_ID
        };
        String sortOrder = DatabaseMaster.DrugScheduleHours.COLUMN_NAME_ID + "ASC";

        Cursor cursor = mDatabase.query(
                DatabaseMaster.DrugScheduleHours.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        while (cursor.moveToNext()){
            DrugScheduleHour drugScheduleHour = new DrugScheduleHour();

            drugScheduleHour.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleHours.COLUMN_NAME_ID)));
            drugScheduleHour.setMethod(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleHours.COLUMN_NAME_METHOD)));
            drugScheduleHour.setNoOfPills(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleHours.COLUMN_NAME_NOOFPILLS)));
            drugScheduleHour.setDuration(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleHours.COLUMN_NAME_DURATION)));

            drugScheduleHourArrayList.add(drugScheduleHour);
        }
        cursor.close();
        return drugScheduleHourArrayList;
    }

}
