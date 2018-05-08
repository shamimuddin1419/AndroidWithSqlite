package com.example.shamim.keepdataapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBaseHelper extends SQLiteOpenHelper {

    private  static  final String DATABASE_NAME="Employee.db";
    private  static  final String TABLE_NAME="Employee_details";
    private  static  final String ID="_id";
    private  static  final String NAME="Name";
    private  static  final String AGE="Age";
    private static  final  String Gender="Gender";
    private  static  final String CreateTable="CREATE TABLE "+TABLE_NAME+"( "+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+" VARCHAR(255),"+AGE+" INTEGER,"+Gender+" VARCHAR(15) );";
    private  static  final String Drop_Table="DROP TABLE IF EXISTS "+TABLE_NAME;
    private  static  final  String ALLDATASELECT="SELECT * FROM "+TABLE_NAME+" ORDER BY "+ ID +" DESC";
    private  static  final int VERSION_NUMBER=1;

    private Context context;

    public MyDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            Toast.makeText(context,"Save",Toast.LENGTH_LONG).show();
            db.execSQL(CreateTable);
        }catch (Exception ex){
            Toast.makeText(context,"Exception: "+ex,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        try{
            Toast.makeText(context,"onCreate is Called",Toast.LENGTH_LONG).show();
            db.execSQL(Drop_Table);
        }catch (Exception ex){
            Toast.makeText(context,"Exception: "+ex,Toast.LENGTH_LONG).show();
        }

    }

    public  long InsertData(String name,String age,String gender){

       SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(NAME,name);
        contentValues.put(AGE,age);
        contentValues.put(Gender,gender);

        long rowID=sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return  rowID;
    }

    public Cursor GetAllData(){
        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery(ALLDATASELECT,null);
        return  cursor;

    }
}
