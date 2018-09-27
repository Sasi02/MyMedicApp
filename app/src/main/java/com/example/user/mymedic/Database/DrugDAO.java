package com.example.user.mymedic.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.example.user.mymedic.Model.Drug;
import java.util.ArrayList;

public class DrugDAO {

    private SQLiteDatabase mDatabase;
    private DatabaseHandler dbHelper;

    public DrugDAO(Context context) {
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

    public void add(Drug drug){
        ContentValues values = new ContentValues();

        values.put(DatabaseMaster.Drug.COLUMN_NAME_MANUFACTURER, drug.getManufacturer());
        values.put(DatabaseMaster.Drug.COLUMN_NAME_NAME, drug.getName());
        values.put(DatabaseMaster.Drug.COLUMN_NAME_DOSAGE, drug.getDosage());

        try{
            long newRowId = mDatabase.insert(DatabaseMaster.Drug.TABLE_NAME, null, values);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public ArrayList<Drug> GetAll(){
        ArrayList<Drug> drugList = new ArrayList<>();

        String[] projection ={
                DatabaseMaster.Drug.COLUMN_NAME_ID,
                DatabaseMaster.Drug.COLUMN_NAME_MANUFACTURER,
                DatabaseMaster.Drug.COLUMN_NAME_NAME,
                DatabaseMaster.Drug.COLUMN_NAME_DOSAGE
        };

        String sortOrder = DatabaseMaster.Drug.COLUMN_NAME_ID + "ASC";

        Cursor cursor = mDatabase.query(
                DatabaseMaster.Drug.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        while (cursor.moveToNext()){
            Drug tempDrug = new Drug();

            tempDrug.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.Drug.COLUMN_NAME_ID)));
            tempDrug.setManufacturer(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Drug.COLUMN_NAME_MANUFACTURER)));
            tempDrug.setName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Drug.COLUMN_NAME_NAME)));
            tempDrug.setDosage(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseMaster.Drug.COLUMN_NAME_DOSAGE)));

            drugList.add(tempDrug);
        }
        cursor.close();
        return drugList;
    }

    public boolean deleteByID(int id){
        try {
            mDatabase.delete(DatabaseMaster.Drug.TABLE_NAME, DatabaseMaster.Drug.COLUMN_NAME_ID +"="+ id, null);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }

    }

    public ArrayList<Drug> searchByName(String name){
        ArrayList<Drug> drugList = new ArrayList<>();

        String sqlQuery = "SELECT * FROM "+ DatabaseMaster.Drug.TABLE_NAME +" WHERE "+
                DatabaseMaster.Drug.COLUMN_NAME_NAME +" LIKE '% "+ name +" %'";

        Cursor cursor = mDatabase.rawQuery(sqlQuery, null);

        if(cursor.getCount() > 0){
            if(cursor.moveToFirst()){
                do{
                    Drug tempDrug = new Drug();

                    tempDrug.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.Drug.COLUMN_NAME_ID)));
                    tempDrug.setManufacturer(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Drug.COLUMN_NAME_MANUFACTURER)));
                    tempDrug.setName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Drug.COLUMN_NAME_NAME)));
                    tempDrug.setDosage(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseMaster.Drug.COLUMN_NAME_DOSAGE)));

                    drugList.add(tempDrug);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return drugList;
    }

    public Drug findById(int id){
        Drug retDrug = new Drug();

        String sqlQuery = "SELECT * FROM "+ DatabaseMaster.Drug.TABLE_NAME +" WHERE "+
                DatabaseMaster.Drug.COLUMN_NAME_ID +" = "+ id;

        Cursor cursor = mDatabase.rawQuery(sqlQuery, null);

        if(cursor.moveToFirst() && cursor.getCount() == 1){

            retDrug.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.Drug.COLUMN_NAME_ID)));
            retDrug.setManufacturer(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Drug.COLUMN_NAME_MANUFACTURER)));
            retDrug.setName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Drug.COLUMN_NAME_NAME)));
            retDrug.setDosage(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseMaster.Drug.COLUMN_NAME_DOSAGE)));
        }
        cursor.close();
        return retDrug;
    }

    public boolean updateUser(Drug drug){
        String sqlQuery = DatabaseMaster.Drug.COLUMN_NAME_ID +" = ?";
        String id = String.valueOf(drug.getId());
        String[] selectionArgs = {id};

        ContentValues values = new ContentValues();
        values.put(DatabaseMaster.Drug.COLUMN_NAME_MANUFACTURER, drug.getManufacturer());
        values.put(DatabaseMaster.Drug.COLUMN_NAME_NAME, drug.getName());
        values.put(DatabaseMaster.Drug.COLUMN_NAME_DOSAGE, drug.getDosage());

        int count = mDatabase.update(
                DatabaseMaster.Drug.TABLE_NAME,
                values,
                sqlQuery,
                selectionArgs
        );

        return count != 0;
    }


}
