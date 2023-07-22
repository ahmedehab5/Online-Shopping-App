package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UsersDBHelper extends SQLiteOpenHelper {
    final private static String databaseName="UsersDatabase";
    SQLiteDatabase usersDatabase;

    public UsersDBHelper(Context context) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(id integer primary key,"+
                "name text not null, email text unique  not null ," +
                "password text not null,type boolean not null," +
                "phone text not null )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
        onCreate(db);
    }
    public void createNewUser (String name,String email, String pass, String type,String phone){
        ContentValues row=new ContentValues();
        row.put("name",name);
        row.put("email",email);
        row.put("password",pass);
        row.put("type",type);
        row.put("phone",phone);
        usersDatabase=getWritableDatabase();
        usersDatabase.insert("users",null,row);
        usersDatabase.close();
    }
    public Cursor FerchAllUsers (String name,String email,String pass,String type, String phone) {
        usersDatabase=getReadableDatabase();
        String[] rowDetails={"name","email","pass","type","phone"};
        Cursor cursor=usersDatabase.query("ussers",rowDetails,null,null,null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();
        usersDatabase.close();
        return cursor;
    }
    public Boolean checkName(String email){
        SQLiteDatabase usersDatabase = this.getWritableDatabase();
        Cursor cursor = usersDatabase.rawQuery("Select * from users where email=? " , new String[] {email} );
        if(cursor.getCount()>0){
            return true;
        }
        else {
            return  false;
        }

    }
    public Boolean checkPassword(String email, String password){
        SQLiteDatabase usersDatabase = this.getWritableDatabase();
        Cursor cursor = usersDatabase.rawQuery("Select * from users where email=?  and password = ?" , new String[] {email,password} );
        if(cursor.getCount()==1){
            return true;
        }   else
        {
            return  false;
        }
    }

}
