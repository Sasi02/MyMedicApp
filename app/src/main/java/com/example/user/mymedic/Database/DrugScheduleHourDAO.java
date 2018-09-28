package com.example.user.mymedic.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.example.user.mymedic.Model.DrugScheduleHour;
import java.util.ArrayList;

public class DrugScheduleHourDAO {

    private SQLiteDatabase mDatabase;
    private DatabaseHandler dbHelper;

    public DrugScheduleHourDAO(Context context) {
        dbHelper = new DatabaseHandler(context);

        try{
            Open();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void Open() throws SQLException {
        mDatabase = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public void add(DrugScheduleHour drugScheduleHour){
        ContentValues values = new ContentValues();

        values.put(DatabaseMaster.DrugScheduleHours.COLUMN_NAME_DURATION, drugScheduleHour.getDuration());
        values.put(DatabaseMaster.DrugScheduleHours.COLUMN_NAME_METHOD, drugScheduleHour.getMethod());
        values.put(DatabaseMaster.DrugScheduleHours.COLUMN_NAME_NOOFPILLS, drugScheduleHour.getNoOfPills());
        values.put(DatabaseMaster.DrugScheduleHours.COLUMN_NAME_PRESCRIPTION_ID, drugScheduleHour.getPrescriptionId());
        values.put(DatabaseMaster.DrugScheduleHours.COLUMN_NAME_DRUG_ID, drugScheduleHour.getDrugId());

        try {
            long newRowId = mDatabase.insert(DatabaseMaster.DrugScheduleHours.TABLE_NAME, null, values);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public ArrayList<DrugScheduleHour> GetAll(){
        ArrayList<DrugScheduleHour> drugScheduleHourList = new ArrayList<>();

        String[] projection ={
                DatabaseMaster.DrugScheduleHours.COLUMN_NAME_ID,
                DatabaseMaster.DrugScheduleHours.COLUMN_NAME_DURATION,
                DatabaseMaster.DrugScheduleHours.COLUMN_NAME_METHOD,
                DatabaseMaster.DrugScheduleHours.COLUMN_NAME_NOOFPILLS,
                DatabaseMaster.DrugScheduleHours.COLUMN_NAME_PRESCRIPTION_ID,
                DatabaseMaster.DrugScheduleHours.COLUMN_NAME_DRUG_ID
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
            drugScheduleHour.setDuration(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleHours.COLUMN_NAME_DURATION)));
            drugScheduleHour.setMethod(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleHours.COLUMN_NAME_METHOD)));
            drugScheduleHour.setNoOfPills(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleHours.COLUMN_NAME_NOOFPILLS)));
            drugScheduleHour.setPrescriptionId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleHours.COLUMN_NAME_PRESCRIPTION_ID)));
            drugScheduleHour.setDrugId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleHours.COLUMN_NAME_DRUG_ID)));

            drugScheduleHourList.add(drugScheduleHour);
        }
        cursor.close();
        return drugScheduleHourList;
    }

    public boolean deleteByID(int id){
        try {
            mDatabase.delete(DatabaseMaster.DrugScheduleHours.TABLE_NAME,
                    DatabaseMaster.DrugScheduleHours.COLUMN_NAME_ID +"="+ id, null);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }

    }

    public ArrayList<DrugScheduleHour> searchByName(String name){
        ArrayList<DrugScheduleHour> drugScheHourList = new ArrayList<>();

        String sqlQuery = "SELECT * FROM "+ DatabaseMaster.DrugScheduleHours.TABLE_NAME +" WHERE "+
                DatabaseMaster.DrugScheduleHours.COLUMN_NAME_METHOD +" LIKE '% "+ name +" %'";

        Cursor cursor = mDatabase.rawQuery(sqlQuery, null);

        if(cursor.getCount() > 0){
            if(cursor.moveToFirst()){
                do{
                    DrugScheduleHour tempDrugScheHour = new DrugScheduleHour();

                    tempDrugScheHour.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleHours.COLUMN_NAME_ID)));
                    tempDrugScheHour.setDuration(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleHours.COLUMN_NAME_DURATION)));
                    tempDrugScheHour.setMethod(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleHours.COLUMN_NAME_METHOD)));
                    tempDrugScheHour.setNoOfPills(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleHours.COLUMN_NAME_NOOFPILLS)));
                    tempDrugScheHour.setPrescriptionId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleHours.COLUMN_NAME_PRESCRIPTION_ID)));
                    tempDrugScheHour.setDrugId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleHours.COLUMN_NAME_DRUG_ID)));

                    drugScheHourList.add(tempDrugScheHour);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return drugScheHourList;
    }

    public DrugScheduleHour findById(int id){
        DrugScheduleHour retDrugScheduleHour = new DrugScheduleHour();

        String sqlQuery = "SELECT * FROM "+ DatabaseMaster.DrugScheduleHours.TABLE_NAME +" WHERE "+
                DatabaseMaster.DrugScheduleHours.COLUMN_NAME_ID +" = "+ id;

        Cursor cursor = mDatabase.rawQuery(sqlQuery, null);

        if(cursor.moveToFirst() && cursor.getCount() == 1){

            retDrugScheduleHour.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleHours.COLUMN_NAME_ID)));
            retDrugScheduleHour.setDuration(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleHours.COLUMN_NAME_DURATION)));
            retDrugScheduleHour.setMethod(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleHours.COLUMN_NAME_METHOD)));
            retDrugScheduleHour.setNoOfPills(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleHours.COLUMN_NAME_NOOFPILLS)));
            retDrugScheduleHour.setPrescriptionId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleHours.COLUMN_NAME_PRESCRIPTION_ID)));
            retDrugScheduleHour.setDrugId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleHours.COLUMN_NAME_DRUG_ID)));

        }
        cursor.close();
        return retDrugScheduleHour;
    }

    public boolean updateDrugScheduleHour(DrugScheduleHour drugScheduleHour){
        String sqlQuery = DatabaseMaster.DrugScheduleHours.COLUMN_NAME_ID +" = ?";
        String id = String.valueOf(drugScheduleHour.getId());
        String[] selectionArgs = {id};

        ContentValues values = new ContentValues();
        values.put(DatabaseMaster.DrugScheduleHours.COLUMN_NAME_DURATION, drugScheduleHour.getDuration());
        values.put(DatabaseMaster.DrugScheduleHours.COLUMN_NAME_METHOD, drugScheduleHour.getMethod());
        values.put(DatabaseMaster.DrugScheduleHours.COLUMN_NAME_NOOFPILLS, drugScheduleHour.getNoOfPills());

        int count = mDatabase.update(
                DatabaseMaster.DrugScheduleHours.TABLE_NAME,
                values,
                sqlQuery,
                selectionArgs
        );

        return count != 0;
    }

}
