package com.example.user.mymedic.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.example.user.mymedic.Helper.TypeConverter;
import com.example.user.mymedic.Model.Prescription;
import java.util.ArrayList;

public class PrescriptionDAO {

    private SQLiteDatabase mDatabase;
    private DatabaseHandler dbHelper;

    public PrescriptionDAO(Context context) {
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

    public void add(Prescription prescription){
        ContentValues values = new ContentValues();

        values.put(DatabaseMaster.Prescription.COLUMN_NAME_NAME, prescription.getName());
        values.put(DatabaseMaster.Prescription.COLUMN_NAME_DOCNAME, prescription.getDocName());
        values.put(DatabaseMaster.Prescription.COLUMN_NAME_DISEASE, prescription.getDisease());
        values.put(DatabaseMaster.Prescription.COLUMN_NAME_DESCRIPTION, prescription.getDiscription());
        values.put(DatabaseMaster.Prescription.COLUMN_NAME_SDATE, TypeConverter.toString(prescription.getStartDate()));
        values.put(DatabaseMaster.Prescription.COLUMN_NAME_EDATE, TypeConverter.toString(prescription.getEndDate()));
        values.put(DatabaseMaster.Prescription.COLUMN_NAME_USER_ID, prescription.getUserId());

        try{
            long newRowId = mDatabase.insert(DatabaseMaster.Prescription.TABLE_NAME, null, values);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public ArrayList<Prescription> GetAll(){
        ArrayList<Prescription> prescriptionList = new ArrayList<>();

        String[] projection ={
                DatabaseMaster.Prescription.COLUMN_NAME_ID,
                DatabaseMaster.Prescription.COLUMN_NAME_NAME,
                DatabaseMaster.Prescription.COLUMN_NAME_DOCNAME,
                DatabaseMaster.Prescription.COLUMN_NAME_DISEASE,
                DatabaseMaster.Prescription.COLUMN_NAME_DESCRIPTION,
                DatabaseMaster.Prescription.COLUMN_NAME_SDATE,
                DatabaseMaster.Prescription.COLUMN_NAME_EDATE,
                DatabaseMaster.Prescription.COLUMN_NAME_USER_ID
        };

        String sortOrder = DatabaseMaster.Prescription.COLUMN_NAME_ID + " ASC ";

        Cursor cursor = mDatabase.query(
                DatabaseMaster.Prescription.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        while (cursor.moveToNext()){
            Prescription tempPrescription = new Prescription();

            tempPrescription.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.Prescription.COLUMN_NAME_ID)));
            tempPrescription.setName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Prescription.COLUMN_NAME_NAME)));
            tempPrescription.setDocName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Prescription.COLUMN_NAME_DOCNAME)));
            tempPrescription.setDisease(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Prescription.COLUMN_NAME_DISEASE)));
            tempPrescription.setDiscription(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Prescription.COLUMN_NAME_DESCRIPTION)));
            tempPrescription.setStartDate(TypeConverter.toDate(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Prescription.COLUMN_NAME_SDATE))));
            tempPrescription.setEndDate(TypeConverter.toDate(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Prescription.COLUMN_NAME_EDATE))));
            tempPrescription.setUserId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.Prescription.COLUMN_NAME_USER_ID)));

            prescriptionList.add(tempPrescription);
        }
        cursor.close();
        return prescriptionList;
    }

    public boolean deleteByID(int id){
        try {
            mDatabase.delete(DatabaseMaster.Prescription.TABLE_NAME,
                    DatabaseMaster.Prescription.COLUMN_NAME_ID +"="+ id, null);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }

    }

    public ArrayList<Prescription> searchByName(String name){
        ArrayList<Prescription> prescriptionList = new ArrayList<>();

        String sqlQuery = "SELECT * FROM "+ DatabaseMaster.Prescription.TABLE_NAME +" WHERE "+
                DatabaseMaster.Prescription.COLUMN_NAME_NAME +" LIKE '% "+ name +" %'";

        Cursor cursor = mDatabase.rawQuery(sqlQuery, null);

        if(cursor.getCount() > 0){
            if(cursor.moveToFirst()){
                do{
                    Prescription tempPrescription = new Prescription();

                    tempPrescription.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.Prescription.COLUMN_NAME_ID)));
                    tempPrescription.setName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Prescription.COLUMN_NAME_NAME)));
                    tempPrescription.setDocName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Prescription.COLUMN_NAME_DOCNAME)));
                    tempPrescription.setDisease(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Prescription.COLUMN_NAME_DISEASE)));
                    tempPrescription.setDiscription(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Prescription.COLUMN_NAME_DESCRIPTION)));
                    tempPrescription.setStartDate(TypeConverter.toDate(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Prescription.COLUMN_NAME_SDATE))));
                    tempPrescription.setEndDate(TypeConverter.toDate(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Prescription.COLUMN_NAME_EDATE))));
                    tempPrescription.setUserId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.Prescription.COLUMN_NAME_USER_ID)));

                    prescriptionList.add(tempPrescription);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return prescriptionList;
    }

    public Prescription findById(int id){
        Prescription retPrescription = new Prescription();

        String sqlQuery = "SELECT * FROM "+ DatabaseMaster.Prescription.TABLE_NAME +" WHERE "+
                DatabaseMaster.Prescription.COLUMN_NAME_ID +" = "+ id;

        Cursor cursor = mDatabase.rawQuery(sqlQuery, null);

        if(cursor.moveToFirst() && cursor.getCount() == 1){

            retPrescription.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.Prescription.COLUMN_NAME_ID)));
            retPrescription.setName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Prescription.COLUMN_NAME_NAME)));
            retPrescription.setDocName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Prescription.COLUMN_NAME_DOCNAME)));
            retPrescription.setDisease(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Prescription.COLUMN_NAME_DISEASE)));
            retPrescription.setDiscription(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Prescription.COLUMN_NAME_DESCRIPTION)));
            retPrescription.setStartDate(TypeConverter.toDate(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Prescription.COLUMN_NAME_SDATE))));
            retPrescription.setEndDate(TypeConverter.toDate(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Prescription.COLUMN_NAME_EDATE))));
            retPrescription.setUserId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.Prescription.COLUMN_NAME_USER_ID)));
        }
        cursor.close();
        return retPrescription;
    }

    public boolean updatePrescription(Prescription prescription){
        String sqlQuery = DatabaseMaster.Prescription.COLUMN_NAME_ID +" = ?";
        String id = String.valueOf(prescription.getId());
        String[] selectionArgs = {id};

        ContentValues values = new ContentValues();
        values.put(DatabaseMaster.Prescription.COLUMN_NAME_NAME, prescription.getName());
        values.put(DatabaseMaster.Prescription.COLUMN_NAME_DOCNAME, prescription.getDocName());
        values.put(DatabaseMaster.Prescription.COLUMN_NAME_DISEASE, prescription.getDisease());
        values.put(DatabaseMaster.Prescription.COLUMN_NAME_DESCRIPTION, prescription.getDiscription());
        values.put(DatabaseMaster.Prescription.COLUMN_NAME_SDATE, TypeConverter.toString(prescription.getStartDate()));
        values.put(DatabaseMaster.Prescription.COLUMN_NAME_EDATE, TypeConverter.toString(prescription.getEndDate()));
        values.put(DatabaseMaster.Prescription.COLUMN_NAME_USER_ID, prescription.getUserId());

        int count = mDatabase.update(
                DatabaseMaster.Prescription.TABLE_NAME,
                values,
                sqlQuery,
                selectionArgs
        );

        return count != 0;
    }
}
