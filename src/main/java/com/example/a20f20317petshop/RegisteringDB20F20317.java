package com.example.a20f20317petshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class RegisteringDB20F20317 extends SQLiteOpenHelper {
    public static final String regcustdb ="RegisterCustomer.db";
    public static final String reginfo="Customer_information";
    public static final String custid="Customer_ID";
    public static final String custname="Customer_Name";
    public static final String custtype="Type_of_Customer";
    public static final String custpassword="Customer_Password";

    public RegisteringDB20F20317(Context context){super(context,regcustdb,null,1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE "+reginfo+" ("+custid+" TEXT PRIMARY KEY, "+custname+" TEXT, "+custtype+" TEXT, "+custpassword+" TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old_db, int new_db) {
        db.execSQL("DROP TABLE IF EXISTS "+reginfo);
        onCreate(db);
    }
    public boolean addcustomer(String Customer_ID, String Customer_Name, String Customer_Type,String Customer_Password){
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(custid,Customer_ID);
        contentValues.put(custname,Customer_Name);
        contentValues.put(custtype,Customer_Type);
        contentValues.put(custpassword,Customer_Password);

        long result=db.insert(reginfo,null,contentValues);
        db.close();
        if(result==-1)
            return false;
        else
            return true;
    }

    public String getpassword(String customerid){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+reginfo+" WHERE "+custid+" ='"+customerid+"'",null);
        cursor.moveToFirst();
        String Password=cursor.getString(3);
        db.close();
        cursor.close();
        return Password;
    }

    public Cursor getaccounts(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT  * FROM "+reginfo,null);
        return cursor;
    }
}