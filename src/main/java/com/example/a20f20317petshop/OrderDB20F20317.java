package com.example.a20f20317petshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OrderDB20F20317 extends SQLiteOpenHelper {
    public static final String ordercustdb = "OrderProductCustomer.db";
    public static final String prdtable = "Product_table";
    public static final String pid = "Product_ID";
    public static final String pquantity = "Total_Quantity";
    public static final String ptamt = "Total_Cost";
    public static final String pd = "Purchase_Date";
    public static final String pnid = "PetName_ID";

    public OrderDB20F20317(Context context) {
        super(context, ordercustdb, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + prdtable + " ("
                + pid + " TEXT PRIMARY KEY, "
                + pquantity + " TEXT, "
                + ptamt + " TEXT, "
                + pd + " TEXT, "
                + pnid + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldDB, int newDB) {
        db.execSQL("DROP TABLE IF EXISTS " + prdtable);
        onCreate(db);
    }

    public boolean addproducts(String Product_ID, String Total_Quantity, String Total_Cost, String Purchase_Date, String PetName_ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(pid, Product_ID);
        contentValues.put(pquantity, Total_Quantity);
        contentValues.put(ptamt, Total_Cost);
        contentValues.put(pd, Purchase_Date);
        contentValues.put(pnid, PetName_ID);

        long result = db.insert(prdtable, null, contentValues);
        db.close();
        return result != -1;
    }

    public Integer deletepetproducts(String productID) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete(prdtable, pid + " = ?", new String[]{productID});
        db.close();
        return rowsDeleted;
    }

    public Cursor viewproduct(String petNameOrID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + prdtable + " WHERE " + pnid + " = ?", new String[]{petNameOrID});
        return cursor;
    }

    public Cursor viewallproducts() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + prdtable, null);
        return cursor;
    }

    public boolean lastpurchasedateupdate(String newPurchaseDate, String productID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(pd, newPurchaseDate);
        int rowsUpdated = db.update(prdtable, contentValues, pid + " = ?", new String[]{productID});
        db.close();
        return rowsUpdated > 0;
    }
}
