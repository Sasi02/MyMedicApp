package com.example.user.mymedic.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.example.user.mymedic.Model.DrugScheduleMeal;
import java.util.ArrayList;

public class DrugScheduleMealDAO {

    private SQLiteDatabase mDatabase;
    private DatabaseHandler dbHelper;

    public DrugScheduleMealDAO(Context context) {
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

    public void add(DrugScheduleMeal drugScheduleMeal){
        ContentValues values = new ContentValues();

        values.put(DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_METHOD, drugScheduleMeal.getMethod());
        values.put(DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_NOOFPILLS, drugScheduleMeal.getNoOfPills());
        values.put(DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_SELECTEDMEAL, drugScheduleMeal.getSelectedMeal());
        values.put(DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_BEFOREORAFTER, drugScheduleMeal.getBeforeOrAfter());
        values.put(DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_PRESCRIPTION_ID, drugScheduleMeal.getPrescriptionId());
        values.put(DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_DRUG_ID, drugScheduleMeal.getDrugId());

        try{
            long newRowId = mDatabase.insert(DatabaseMaster.DrugScheduleMeal.TABLE_NAME, null, values);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public ArrayList<DrugScheduleMeal> GetAll(){
        ArrayList<DrugScheduleMeal> drugScheMealList = new ArrayList<>();

        String[] projection ={
                DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_METHOD,
                DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_NOOFPILLS,
                DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_SELECTEDMEAL,
                DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_BEFOREORAFTER,
                DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_PRESCRIPTION_ID,
                DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_DRUG_ID
        };

        String sortOrder = DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_ID + " ASC ";

        Cursor cursor = mDatabase.query(
                DatabaseMaster.DrugScheduleMeal.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        while (cursor.moveToNext()){
            DrugScheduleMeal tempDrugScheMeal = new DrugScheduleMeal();

            tempDrugScheMeal.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_ID)));
            tempDrugScheMeal.setMethod(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_METHOD)));
            tempDrugScheMeal.setNoOfPills(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_NOOFPILLS)));
            tempDrugScheMeal.setSelectedMeal(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_SELECTEDMEAL)));
            tempDrugScheMeal.setBeforeOrAfter(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_BEFOREORAFTER)));
            tempDrugScheMeal.setPrescriptionId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_PRESCRIPTION_ID)));
            tempDrugScheMeal.setDrugId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_DRUG_ID)));

            drugScheMealList.add(tempDrugScheMeal);
        }
        cursor.close();
        return drugScheMealList;
    }

    public boolean deleteByID(int id){
        try {
            mDatabase.delete(DatabaseMaster.DrugScheduleMeal.TABLE_NAME,
                    DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_ID +"="+ id, null);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }

    }

    public ArrayList<DrugScheduleMeal> searchByName(String name){
        ArrayList<DrugScheduleMeal> drugScheMealList = new ArrayList<>();

        String sqlQuery = "SELECT * FROM "+ DatabaseMaster.DrugScheduleMeal.TABLE_NAME +" WHERE "+
                DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_METHOD +" LIKE '% "+ name +" %'";

        Cursor cursor = mDatabase.rawQuery(sqlQuery, null);

        if(cursor.getCount() > 0){
            if(cursor.moveToFirst()){
                do{
                    DrugScheduleMeal tempDrugScheMeal = new DrugScheduleMeal();

                    tempDrugScheMeal.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_ID)));
                    tempDrugScheMeal.setMethod(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_METHOD)));
                    tempDrugScheMeal.setNoOfPills(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_NOOFPILLS)));
                    tempDrugScheMeal.setSelectedMeal(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_SELECTEDMEAL)));
                    tempDrugScheMeal.setBeforeOrAfter(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_BEFOREORAFTER)));
                    tempDrugScheMeal.setPrescriptionId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_PRESCRIPTION_ID)));
                    tempDrugScheMeal.setDrugId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_DRUG_ID)));


                    drugScheMealList.add(tempDrugScheMeal);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return drugScheMealList;
    }

    public DrugScheduleMeal findById(int id){
        DrugScheduleMeal retDrugScheMeal = new DrugScheduleMeal();

        String sqlQuery = "SELECT * FROM "+ DatabaseMaster.DrugScheduleMeal.TABLE_NAME +" WHERE "+
                DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_ID +" = "+ id;

        Cursor cursor = mDatabase.rawQuery(sqlQuery, null);

        if(cursor.moveToFirst() && cursor.getCount() == 1){

            retDrugScheMeal.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_ID)));
            retDrugScheMeal.setMethod(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_METHOD)));
            retDrugScheMeal.setNoOfPills(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_NOOFPILLS)));
            retDrugScheMeal.setSelectedMeal(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_SELECTEDMEAL)));
            retDrugScheMeal.setBeforeOrAfter(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_BEFOREORAFTER)));
            retDrugScheMeal.setPrescriptionId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_PRESCRIPTION_ID)));
            retDrugScheMeal.setDrugId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_DRUG_ID)));
        }
        cursor.close();
        return retDrugScheMeal;
    }

    public boolean updateDrugScheduleMeal(DrugScheduleMeal drugScheduleMeal){
        String sqlQuery = DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_ID +" = ?";
        String id = String.valueOf(drugScheduleMeal.getId());
        String[] selectionArgs = {id};

        ContentValues values = new ContentValues();
        values.put(DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_METHOD, drugScheduleMeal.getMethod());
        values.put(DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_NOOFPILLS, drugScheduleMeal.getNoOfPills());
        values.put(DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_SELECTEDMEAL, drugScheduleMeal.getSelectedMeal());
        values.put(DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_BEFOREORAFTER, drugScheduleMeal.getBeforeOrAfter());
        values.put(DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_PRESCRIPTION_ID, drugScheduleMeal.getPrescriptionId());
        values.put(DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_DRUG_ID, drugScheduleMeal.getDrugId());

        int count = mDatabase.update(
                DatabaseMaster.DrugScheduleMeal.TABLE_NAME,
                values,
                sqlQuery,
                selectionArgs
        );

        return count != 0;
    }
}
