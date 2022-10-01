package com.OnlineShopping.login;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "OnlineShopping.db";
    public static  final String TABLE_NAME = "customer";
    public static final String COL_1 = "userName";
    public static final String COL_2 = "email";
    public static final String COL_3 = "password";
    public static final String COL_4 = "name";
    public static final String COL_5 = "address";
    public static final String COL_6 = "contactNumber";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table "+TABLE_NAME+"(userName TEXT PRIMARY KEY , email TEXT NOT NULL,password TEXT NOT NULL,name TEXT,address TEXT,contactNumber TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean signup(String  userName,String email,String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,userName);
        contentValues.put(COL_2,email);
        contentValues.put(COL_3,password);

        long result =sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

        if(result == -1){

            return  false;
        }
        else{

            return true;
        }

    }

    public Boolean getSignIn(String userName,String password){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String sql="select * from " + TABLE_NAME +" where userName = '"+ userName +"' and password = '"+password+"'";
        Cursor res = sqLiteDatabase.rawQuery (sql,null);
        Log.d("Test",sql);

        if(res.getCount()>0){
            return true;

        }
        else {
            return false;
        }


    }

    public Cursor getDataById(String user){

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql="select * from " + TABLE_NAME +" where userName = '"+ user +"'";
        Cursor res = sqLiteDatabase.rawQuery (sql,null);

        return res;

    }

    public boolean update(String name,String address,String contact,String userName,String email,String password){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,email);
        contentValues.put(COL_3,password);
        contentValues.put(COL_4,name);
        contentValues.put(COL_5,address);
        contentValues.put(COL_6,contact);
        db.update(TABLE_NAME,contentValues,"userName = ?",new String[] {userName});

        return true;

    }

    public Integer deleteData(String userName){

        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"userName = ?",new String[]{userName});

    }





}
