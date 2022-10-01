package com.OnlineShopping.login.com.hypermart.login;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.OnlineShopping.login.com.hypermart.login.start.Tables;

import com.OnlineShopping.login.com.hypermart.login.account.Table;
import com.OnlineShopping.login.com.hypermart.login.account.model.Customer;



public class DatabaseHelper extends SQLiteOpenHelper {



    public static final String DATABASE_NAME = "OnlineShopping.db";



    public static  final String adminTABLE_NAME = "products";
    public static final String adminCOL_1 = "ID";
    public static final String adminCOL_2 = "productName";
    public static final String adminCOL_3 = "category";
    public static final String adminCOL_4 = "price";
    public static final String adminCOL_5 = "quantity";
    public static final String adminCOL_6 = "description";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //------------------------------Helani------------------------------
        sqLiteDatabase.execSQL("CREATE TABLE "+ Table.User.TABLE_NAME_CUSTOMER + "(" + Table.User.COL_1 + " TEXT PRIMARY KEY , " + Table.User.COL_2 + " TEXT NOT NULL , " + Table.User.COL_3 + " TEXT NOT NULL," + Table.User.COL_4 + " TEXT ," + Table.User.COL_5 + " TEXT ," + Table.User.COL_6 + " TEXT )");

        //------------------------------Gihan-------------------------------
        sqLiteDatabase.execSQL("CREATE TABLE " + Tables.Delivery.TABLE_NAME_DELIVERY + "(" + Tables.Delivery.COL_1_D + " INTEGER  PRIMARY KEY AUTOINCREMENT , " + Tables.Delivery.COL_2_D +" TEXT NOT NULL," + Tables.Delivery.COL_3_D + " TEXT NOT NULL , " + Tables.Delivery.COL_4_D + " INTEGER NOT NULL , " + Tables.Delivery.COL_5_D + " INTEGER NOT NULL ," + Tables.Delivery.COL_6_D + " TEXT NOT NULL UNIQUE , " + Tables.Delivery.COL_7_D + " TEXT NOT NULL )");
        sqLiteDatabase.execSQL("CREATE TABLE " + Tables.Bank.TABLE_NAME_BANK + "(" + Tables.Bank.COL_1_B + " INTEGER PRIMARY KEY , " + Tables.Bank.COL_2_B + " TEXT NOT NULL , " + Tables.Bank.COL_3_B + " TEXT NOT NULL , " + Tables.Bank.COL_4_B + " TEXT NOT NULL , " + Tables.Bank.COL_5_B + " INTEGER NOT NULL )");
        sqLiteDatabase.execSQL("INSERT INTO " + Tables.Bank.TABLE_NAME_BANK + " VALUES(1234123412341234,'Gihan','Visa Debit Card','03/20',233)");
        sqLiteDatabase.execSQL("INSERT INTO " + Tables.Bank.TABLE_NAME_BANK + " VALUES(1234123412345678,'Shehan','Master Card','11/22',243)");
        sqLiteDatabase.execSQL("CREATE TABLE " + Tables.Payment.TABLE_NAME_PAYMENT + "(" + Tables.Payment.COL_1_P + " INTEGER PRIMARY KEY AUTOINCREMENT ," + Tables.Payment.COL_2_P + " TEXT NOT NULL UNIQUE, " + Tables.Payment.COL_3_P + " REAL NOT NULL )");


        //------------------------------Isuri-------------------------------



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        //------------------------------Helani------------------------------
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Table.User.TABLE_NAME_CUSTOMER);

        //------------------------------Gihan-------------------------------
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Tables.Delivery.TABLE_NAME_DELIVERY);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Tables.Payment.TABLE_NAME_PAYMENT);


        //------------------------------Isuri-------------------------------


        //------------------------------Vimanga-------------------------------
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+adminTABLE_NAME);

        onCreate(sqLiteDatabase);
    }


        //------------------------------Helani------------------------------

    public boolean signup(Customer customer){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Table.User.COL_1,customer.getUserName());
        contentValues.put(Table.User.COL_2,customer.getEmail());
        contentValues.put(Table.User.COL_3,customer.getPassword());

        long result = sqLiteDatabase.insert( Table.User.TABLE_NAME_CUSTOMER , null , contentValues);

        if(result == -1){

            return  false;
        }
        else{

            return true;
        }

    }

    public Boolean getSignIn(Customer customer){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String sql="select * from " + Table.User.TABLE_NAME_CUSTOMER +" where userName = '"+ customer.getUserName() +"' and password = '"+customer.getPassword()+"'";
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
        String sql="select * from " + Table.User.TABLE_NAME_CUSTOMER +" where userName = '"+ user +"'";
        Cursor res = sqLiteDatabase.rawQuery (sql,null);

        return res;

    }

    public boolean update(Customer customer){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Table.User.COL_2,customer.getEmail());
        contentValues.put(Table.User.COL_3,customer.getPassword());
        contentValues.put(Table.User.COL_4,customer.getName());
        contentValues.put(Table.User.COL_5,customer.getAddress());
        contentValues.put(Table.User.COL_6,customer.getContactNo());
        db.update(Table.User.TABLE_NAME_CUSTOMER,contentValues,"userName = ?",new String[] {customer.getUserName()});

        return true;

    }

        //------------------------------Gihan-------------------------------






        //------------------------------Isuri-------------------------------













    //------------------------------Vimanga-------------------------------
















}
