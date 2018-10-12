package com.example.janith.manage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static com.example.janith.manage.UserProfile.Users.COLUMN_NAME_DATEOfBIRTH;
import static com.example.janith.manage.UserProfile.Users.COLUMN_NAME_GENDER;
import static com.example.janith.manage.UserProfile.Users.COLUMN_NAME_ID;
import static com.example.janith.manage.UserProfile.Users.COLUMN_NAME_PASSWORD;
import static com.example.janith.manage.UserProfile.Users.COLUMN_NAME_USERNAME;
import static com.example.janith.manage.UserProfile.Users.Table_Name;

public class DBhelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="user-Info.db";
    public DBhelper( Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_USER="CREATE TABLE "+Table_Name+
                "("+COLUMN_NAME_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +COLUMN_NAME_USERNAME+" TEXT,"
                +COLUMN_NAME_DATEOfBIRTH+" TEXT,"
                +COLUMN_NAME_GENDER+" TEXT,"
                +COLUMN_NAME_PASSWORD+" TEXT)";
        db.execSQL(SQL_CREATE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public long addinfo(String username,String DOB,String gender,String password)
    {
        SQLiteDatabase db=getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(COLUMN_NAME_USERNAME,username);
        values.put(COLUMN_NAME_DATEOfBIRTH,DOB);
        values.put(COLUMN_NAME_GENDER,gender);
        values.put(COLUMN_NAME_PASSWORD,password);
        long newrecode=db.insert(Table_Name,null,values);
        return  newrecode;
    }

    public boolean updateInfor(String username,String DOB,String gender,String ID,String password)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_NAME_USERNAME,username);
        values.put(COLUMN_NAME_DATEOfBIRTH,DOB);
        values.put(COLUMN_NAME_GENDER,gender);
        values.put(COLUMN_NAME_PASSWORD,password);

        String selection=COLUMN_NAME_ID+" LIKE ?";
        String[] selectargs={ID};

        int count=db.update(Table_Name,values,selection,selectargs);

        if(count==0)
        {
            return false;
        }

        else
        {
            return true;
        }
    }

public ArrayList<UserProfile.Users> readAllInfor()
{
    ArrayList<UserProfile.Users> userList = new ArrayList<>();
    SQLiteDatabase db=getReadableDatabase();
    String[] projection={COLUMN_NAME_ID,
        COLUMN_NAME_USERNAME,
        COLUMN_NAME_DATEOfBIRTH,
        COLUMN_NAME_GENDER};

    String sortOder=COLUMN_NAME_ID+"DESC";

    Cursor cursor=db.query(Table_Name,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    sortOder);

    while (cursor.moveToNext()){
        UserProfile.Users users = UserProfile.getProfile().getUser();

        users.setId(Integer.parseInt(cursor.getString(0)));
        users.setUsername(cursor.getString(1));
        users.setPassword(cursor.getString(2));
        users.setGender(cursor.getString(3));
        users.setDob(cursor.getString(4));

        userList.add(users);
    }
    return userList;
}
    public UserProfile.Users readAllInfor(String ID)
    {
        SQLiteDatabase db=getWritableDatabase();
        String[] projection={COLUMN_NAME_ID,
                COLUMN_NAME_USERNAME,
                COLUMN_NAME_DATEOfBIRTH,
                COLUMN_NAME_GENDER};

        String selection=COLUMN_NAME_ID+"= ?";
        String[] selectargs={ID};
        Cursor cursor=db.query(Table_Name,
                projection,
                selection,
                selectargs,
                null,
                null,
                null);


        if (cursor.moveToNext()){
                UserProfile.Users users = UserProfile.getProfile().getUser();

                users.setId(Integer.parseInt(cursor.getString(0)));
                users.setUsername(cursor.getString(1));
                users.setPassword(cursor.getString(2));
                users.setDob(cursor.getString(3));

               return  users;
            }
            return null;
    }
    public int deleteInfo(String ID)
    {
        SQLiteDatabase db=getWritableDatabase();
        String selection=COLUMN_NAME_ID+" LIKE ?";
        String[] selectargs={ID};
        int count=db.delete(Table_Name,selection,selectargs);
        return count;

    }
}