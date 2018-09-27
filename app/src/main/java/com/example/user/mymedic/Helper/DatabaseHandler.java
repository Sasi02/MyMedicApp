package com.example.user.mymedic.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "myMedicApp";

    private static final String CREATE_TABLE_USERS =
            "CREATE TABLE "+ DatabaseMaster.Users.TABLE_NAME +" " +
            "( "+ DatabaseMaster.Users.COLUMN_NAME_ID +" INTEGER NOT NULL AUTO_INCREMENT," +
            " "+ DatabaseMaster.Users.COLUMN_NAME_FNAME +" VARCHAR(100)," +
            " "+ DatabaseMaster.Users.COLUMN_NAME_LNAME +" VARCHAR(100)," +
            " "+ DatabaseMaster.Users.COLUMN_NAME_INITIALS +" VARCHAR(40)," +
            " "+ DatabaseMaster.Users.COLUMN_NAME_DOB +" VARCHAR(50)," +
            " "+ DatabaseMaster.Users.COLUMN_NAME_PHONE +" VARCHAR(50)," +
            " "+ DatabaseMaster.Users.COLUMN_NAME_GENDER +" VARCHAR(20)," +
            " "+ DatabaseMaster.Users.COLUMN_NAME_BGROUP +" VARCHAR(10)," +
            " "+ DatabaseMaster.Users.COLUMN_NAME_GENDISEASES +" VARCHAR(255)," +
            " "+ DatabaseMaster.Users.COLUMN_NAME_ALLERGIES +" VARCHAR(255)," +
            " "+ DatabaseMaster.Users.COLUMN_NAME_OPERATIONS +" varchar(255)," +
            " PRIMARY KEY ("+ DatabaseMaster.Users.COLUMN_NAME_ID +") ) ";

    private static final String CREATE_TABLE_DRUGS =
            "CREATE TABLE "+ DatabaseMaster.Drug.TABLE_NAME +" " +
                    "( "+ DatabaseMaster.Drug.COLUMN_NAME_ID +" INTEGER NOT NULL AUTO_INCREMENT," +
                    " "+ DatabaseMaster.Drug.COLUMN_NAME_MANUFACTURER +" VARCHAR(100)," +
                    " "+ DatabaseMaster.Drug.COLUMN_NAME_NAME +" VARCHAR(100)," +
                    " "+ DatabaseMaster.Drug.COLUMN_NAME_DOSAGE +" FLOAT(10)," +
                    " PRIMARY KEY ("+ DatabaseMaster.Drug.COLUMN_NAME_ID +") )";

    private static final String CREATE_TABLE_DRUG_SCHEDULE_HOURS =
            "CREATE TABLE "+ DatabaseMaster.DrugScheduleHours.TABLE_NAME +" " +
                    "( "+ DatabaseMaster.DrugScheduleHours.COLUMN_NAME_ID +" INTEGER NOT NULL AUTO_INCREMENT," +
                    " "+ DatabaseMaster.DrugScheduleHours.COLUMN_NAME_METHOD +" VARCHAR(50)," +
                    " "+ DatabaseMaster.DrugScheduleHours.COLUMN_NAME_NOOFPILLS +" INTEGER," +
                    " "+ DatabaseMaster.DrugScheduleHours.COLUMN_NAME_DURATION +" FLOAT(10)," +
                    " "+ DatabaseMaster.DrugScheduleHours.COLUMN_NAME_PRESCRIPTION_ID +" INTEGER NOT NULL," +
                    " "+ DatabaseMaster.DrugScheduleHours.COLUMN_NAME_DRUG_ID +" INTEGER NOT NULL," +
                    " PRIMARY KEY ("+ DatabaseMaster.DrugScheduleHours.COLUMN_NAME_ID +")," +
                    " FOREIGN KEY ("+ DatabaseMaster.DrugScheduleHours.COLUMN_NAME_PRESCRIPTION_ID +") REFERENCES " +
                    " "+ DatabaseMaster.Prescription.TABLE_NAME +"("+ DatabaseMaster.Prescription.COLUMN_NAME_ID +")," +
                    " FOREIGN KEY ("+ DatabaseMaster.DrugScheduleHours.COLUMN_NAME_DRUG_ID +") REFERENCES " +
                    " "+ DatabaseMaster.Drug.TABLE_NAME +"("+ DatabaseMaster.Drug.COLUMN_NAME_ID +")" +
                    ")";

    private static final String CREATE_TABLE_DRUG_SCHEDULE_MEALS =
            "CREATE TABLE "+ DatabaseMaster.DrugScheduleMeal.TABLE_NAME +" " +
                    "( "+ DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_ID +" INTEGER NOT NULL AUTO_INCREMENT," +
                    " "+ DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_METHOD +" VARCHAR(50)," +
                    " "+ DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_NOOFPILLS +" INTEGER," +
                    " "+ DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_SELECTEDMEAL +" VARCHAR(50)," +
                    " "+ DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_BEFOREORAFTER +" VARCHAR(50)," +
                    " "+ DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_PRESCRIPTION_ID +" INTEGER NOT NULL," +
                    " "+ DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_DRUG_ID +" INTEGER NOT NULL," +
                    " PRIMARY KEY ("+ DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_ID +")," +
                    " FOREIGN KEY ("+ DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_PRESCRIPTION_ID +") REFERENCES " +
                    " "+ DatabaseMaster.Prescription.TABLE_NAME +"("+ DatabaseMaster.Prescription.COLUMN_NAME_ID +")," +
                    " FOREIGN KEY ("+ DatabaseMaster.DrugScheduleMeal.COLUMN_NAME_DRUG_ID +") REFERENCES " +
                    " "+ DatabaseMaster.Drug.TABLE_NAME +"("+ DatabaseMaster.Drug.COLUMN_NAME_ID +")" +
                    ")";

    private static final String CREATE_TABLE_PRESCRIPTIONS =
            "CREATE TABLE "+ DatabaseMaster.Prescription.TABLE_NAME +" " +
                    "( "+ DatabaseMaster.Prescription.COLUMN_NAME_ID +" INTEGER NOT NULL AUTO_INCREMENT," +
                    " "+ DatabaseMaster.Prescription.COLUMN_NAME_NAME +" VARCHAR(100)," +
                    " "+ DatabaseMaster.Prescription.COLUMN_NAME_DOCNAME +" VARCHAR(100)," +
                    " "+ DatabaseMaster.Prescription.COLUMN_NAME_DISEASE +" VARCHAR(100)," +
                    " "+ DatabaseMaster.Prescription.COLUMN_NAME_DESCRIPTION +" VARCHAR(255)," +
                    " "+ DatabaseMaster.Prescription.COLUMN_NAME_SDATE +" VARCHAR(50)," +
                    " "+ DatabaseMaster.Prescription.COLUMN_NAME_EDATE +" VARCHAR(50)," +
                    " PRIMARY KEY ("+ DatabaseMaster.Prescription.COLUMN_NAME_ID +") " +
                    " FOREIGN KEY ("+ DatabaseMaster.Prescription.COLUMN_NAME_USER_ID +") REFERENCES " +
                    " "+ DatabaseMaster.Users.TABLE_NAME +"("+ DatabaseMaster.Users.COLUMN_NAME_ID +")," +
                    ")";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_DRUGS);
        db.execSQL(CREATE_TABLE_PRESCRIPTIONS);
        db.execSQL(CREATE_TABLE_DRUG_SCHEDULE_HOURS);
        db.execSQL(CREATE_TABLE_DRUG_SCHEDULE_MEALS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseMaster.Users.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseMaster.Drug.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseMaster.Prescription.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseMaster.DrugScheduleHours.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseMaster.DrugScheduleMeal.TABLE_NAME);

        onCreate(db);
    }
}
