package com.example.janith.manage.database;

import android.provider.BaseColumns;
public final class UserProfile {

    private UserProfile(){}

    public  static class Users implements BaseColumns {
        public static final String Table_Name = "UserInfo";
        public static final String COLUMN_NAME_ID = "ID";
        public static final String COLUMN_NAME_USERNAME = "userName";
        public static final String COLUMN_NAME_DATEOfBIRTH = "dateOfBirth";
        public static final String COLUMN_NAME_GENDER = "gender";
        public static final String COLUMN_NAME_PASSWORD = "password";
    }



}
