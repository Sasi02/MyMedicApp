package com.example.user.mymedic.Database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class PrescriptionDAO {

    private SQLiteDatabase mDatabase;
    private DatabaseHandler dbHelper;
    private Context mContext;

    public PrescriptionDAO(Context context) {
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
}
