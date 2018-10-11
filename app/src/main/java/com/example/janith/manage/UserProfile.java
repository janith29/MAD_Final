package com.example.janith.manage;

import android.provider.BaseColumns;
public final class UserProfile {

    private UserProfile(){}

    public static UserProfile getProfile(){

        UserProfile userProfile = new UserProfile();
        return userProfile;
    }

    public  static class Users implements BaseColumns{
        public static final String Table_Name="UserInfo";
        public static final String COLUMN_NAME_ID="ID";
        public static final String COLUMN_NAME_USERNAME="userName";
        public static final String COLUMN_NAME_DATEOfBIRTH="dateOfBirth";
        public static final String COLUMN_NAME_GENDER="gender";
        public static final String COLUMN_NAME_PASSWORD="password";

        private int id;
        private String username;
        private String dob;
        private String gender;
        private String password;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public Users getUser(){
        Users users = new Users();

        return users;
    }

}
