package com.example.user.mymedic.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.Date;
import com.example.user.mymedic.Helper.TypeConverter;
import com.example.user.mymedic.Model.User;

public class UserDAO {

    private SQLiteDatabase mDatabase;
    private DatabaseHandler dbHelper;

    public UserDAO(Context context){
        dbHelper = new DatabaseHandler(context);

        try{
            Open();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void Open() throws SQLException{
        mDatabase = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public void add(User user){
        ContentValues values = new ContentValues();

        values.put(DatabaseMaster.Users.COLUMN_NAME_FNAME, user.getFirstName());
        values.put(DatabaseMaster.Users.COLUMN_NAME_LNAME, user.getLastName());
        values.put(DatabaseMaster.Users.COLUMN_NAME_INITIALS, user.getInitials());
        values.put(DatabaseMaster.Users.COLUMN_NAME_DOB, TypeConverter.toString(user.getDob()));
        values.put(DatabaseMaster.Users.COLUMN_NAME_PHONE, user.getPhone());
        values.put(DatabaseMaster.Users.COLUMN_NAME_GENDER, user.getGender());
        values.put(DatabaseMaster.Users.COLUMN_NAME_EMAIL, user.getEmail());
        values.put(DatabaseMaster.Users.COLUMN_NAME_BGROUP, user.getBloodGroup());
        values.put(DatabaseMaster.Users.COLUMN_NAME_GENDISEASES, user.getGenDiseases());
        values.put(DatabaseMaster.Users.COLUMN_NAME_ALLERGIES, user.getAllergies());
        values.put(DatabaseMaster.Users.COLUMN_NAME_OPERATIONS, user.getOperations());

        try{
           long newRowId = mDatabase.insert(DatabaseMaster.Users.TABLE_NAME, null, values);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public ArrayList<User> GetAll(){
        ArrayList<User> userList = new ArrayList<>();
        String sDate;
        Date date;

        String[] projection ={
            DatabaseMaster.Users.COLUMN_NAME_ID,
            DatabaseMaster.Users.COLUMN_NAME_FNAME,
            DatabaseMaster.Users.COLUMN_NAME_LNAME,
            DatabaseMaster.Users.COLUMN_NAME_INITIALS,
            DatabaseMaster.Users.COLUMN_NAME_DOB,
            DatabaseMaster.Users.COLUMN_NAME_PHONE,
            DatabaseMaster.Users.COLUMN_NAME_GENDER,
            DatabaseMaster.Users.COLUMN_NAME_EMAIL,
            DatabaseMaster.Users.COLUMN_NAME_BGROUP,
            DatabaseMaster.Users.COLUMN_NAME_GENDISEASES,
            DatabaseMaster.Users.COLUMN_NAME_ALLERGIES,
            DatabaseMaster.Users.COLUMN_NAME_OPERATIONS
        };

        String sortOrder = DatabaseMaster.Users.COLUMN_NAME_ID + " ASC ";

        Cursor cursor = mDatabase.query(
                DatabaseMaster.Users.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        while (cursor.moveToNext()){
            User tempUser = new User();
            sDate = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_DOB));

            date = TypeConverter.toDate(sDate);

            tempUser.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_ID)));
            tempUser.setFirstName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_FNAME)));
            tempUser.setLastName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_LNAME)));
            tempUser.setInitials(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_INITIALS)));
            tempUser.setDob(date);
            tempUser.setPhone(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_PHONE)));
            tempUser.setGender(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_GENDER)));
            tempUser.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_EMAIL)));
            tempUser.setBloodGroup(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_BGROUP)));
            tempUser.setGenDiseases(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_GENDISEASES)));
            tempUser.setAllergies(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_ALLERGIES)));
            tempUser.setOperations(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_OPERATIONS)));

            userList.add(tempUser);
        }
        cursor.close();
        return userList;
    }

    public boolean deleteByID(int id){
        try {
            mDatabase.delete(DatabaseMaster.Users.TABLE_NAME, DatabaseMaster.Users.COLUMN_NAME_ID +"="+ id, null);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }

    }

    public ArrayList<User> searchByName(String name){
        ArrayList<User> userList = new ArrayList<>();
        String sDate;
        Date date;

        String sqlQuery = "SELECT * FROM "+ DatabaseMaster.Users.TABLE_NAME +" WHERE "+
                DatabaseMaster.Users.COLUMN_NAME_FNAME +" LIKE '% "+ name +" %'";

        Cursor cursor = mDatabase.rawQuery(sqlQuery, null);

        if(cursor.getCount() > 0){
            if(cursor.moveToFirst()){
                do{
                    User tempUser = new User();
                    sDate = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_DOB));

                    date = TypeConverter.toDate(sDate);

                    tempUser.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_ID)));
                    tempUser.setFirstName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_FNAME)));
                    tempUser.setLastName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_LNAME)));
                    tempUser.setInitials(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_INITIALS)));
                    tempUser.setDob(date);
                    tempUser.setPhone(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_PHONE)));
                    tempUser.setGender(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_GENDER)));
                    tempUser.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_EMAIL)));
                    tempUser.setBloodGroup(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_BGROUP)));
                    tempUser.setGenDiseases(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_GENDISEASES)));
                    tempUser.setAllergies(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_ALLERGIES)));
                    tempUser.setOperations(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_OPERATIONS)));

                    userList.add(tempUser);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return userList;
    }

    public User findById(int id){
        User retUser = new User();
        String sDate;
        Date date;

        String sqlQuery = "SELECT * FROM "+ DatabaseMaster.Users.TABLE_NAME +" WHERE "+
                DatabaseMaster.Users.COLUMN_NAME_ID +" = "+ id;

        Cursor cursor = mDatabase.rawQuery(sqlQuery, null);

        if(cursor.moveToFirst() && cursor.getCount() == 1){
            sDate = cursor.getString(cursor.getColumnIndex(DatabaseMaster.Users.COLUMN_NAME_DOB));
            date = TypeConverter.toDate(sDate);

            retUser.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_ID)));
            retUser.setFirstName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_FNAME)));
            retUser.setLastName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_LNAME)));
            retUser.setInitials(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_INITIALS)));
            retUser.setDob(date);
            retUser.setPhone(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_PHONE)));
            retUser.setGender(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_GENDER)));
            retUser.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_EMAIL)));
            retUser.setBloodGroup(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_BGROUP)));
            retUser.setGenDiseases(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_GENDISEASES)));
            retUser.setAllergies(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_ALLERGIES)));
            retUser.setOperations(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_OPERATIONS)));
        }
        cursor.close();
        return retUser;
    }

    public boolean updateUser(User user){
        String sqlQuery = DatabaseMaster.Users.COLUMN_NAME_ID +" = ?";
        String id = String.valueOf(user.getId());
        String[] selectionArgs = {id};

        ContentValues values = new ContentValues();
        values.put(DatabaseMaster.Users.COLUMN_NAME_FNAME, user.getFirstName());
        values.put(DatabaseMaster.Users.COLUMN_NAME_LNAME, user.getLastName());
        values.put(DatabaseMaster.Users.COLUMN_NAME_INITIALS, user.getInitials());
        values.put(DatabaseMaster.Users.COLUMN_NAME_DOB, TypeConverter.toString(user.getDob()));
        values.put(DatabaseMaster.Users.COLUMN_NAME_PHONE, user.getPhone());
        values.put(DatabaseMaster.Users.COLUMN_NAME_GENDER, user.getGender());
        values.put(DatabaseMaster.Users.COLUMN_NAME_EMAIL, user.getEmail());
        values.put(DatabaseMaster.Users.COLUMN_NAME_BGROUP, user.getBloodGroup());
        values.put(DatabaseMaster.Users.COLUMN_NAME_GENDISEASES, user.getGenDiseases());
        values.put(DatabaseMaster.Users.COLUMN_NAME_ALLERGIES, user.getAllergies());
        values.put(DatabaseMaster.Users.COLUMN_NAME_OPERATIONS, user.getOperations());

        int count = mDatabase.update(
                DatabaseMaster.Users.TABLE_NAME,
                values,
                sqlQuery,
                selectionArgs
        );

        return count != 0;
    }

}
