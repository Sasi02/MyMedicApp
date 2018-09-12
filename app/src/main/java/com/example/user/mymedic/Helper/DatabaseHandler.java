package com.example.user.mymedic.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "myMedicApp";

    private static final String TABLE_USER = "user";

    private static final String COLUMN_USERID = "id";
    private static final String COLUMN_FIRSTNAME = "firstname";
    private static final String COLUMN_LASTNAME = "lastname";

    private static final String CREATE_TABLE_USER = "create table "+ TABLE_USER +" " +
            "( "+ COLUMN_USERID +" interger primary key autoincrement," +
            " "+ COLUMN_FIRSTNAME +" varchar(40)," +
            " "+ COLUMN_LASTNAME +" varchar(40) ) ";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_USER);

        onCreate(db);
    }
}
