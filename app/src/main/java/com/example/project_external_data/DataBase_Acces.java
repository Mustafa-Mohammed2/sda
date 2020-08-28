package com.example.project_external_data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBase_Acces {


    private SQLiteDatabase database;
    private SQLiteOpenHelper openHelper;
    private static DataBase_Acces instance;

    private DataBase_Acces (Context context){
        this.openHelper=new My_data_base(context);
    }


    public static DataBase_Acces getInstance_car (Context context){
        if (instance==null){
            instance=new DataBase_Acces(context);
        }
        return instance;
           }


    public void open(){
        this.database=this.openHelper.getWritableDatabase();
    }

    public void close(){
        if (this.database!=null)
            this.database.close();
    }

   public boolean insert_car (Car car){

       ContentValues values =new ContentValues();
       values.put(My_data_base.CAR_MODLE,car.getModle());
       values.put(My_data_base.CAR_COLOR,car.getColor());
       values.put(My_data_base.CAR_DESCRIPTION,car.getDiscraption());
//       values.put(My_data_base.CAR_IMAGE,car.getImage());
       values.put(My_data_base.CAR_DPL,car.getDistansePerLetter());

       Long result= database.insert(My_data_base.CAR_TABLE_NAME,null, values);
       return result !=-1;

           }

   public ArrayList<Car> get_all_car(){

        ArrayList<Car> cars=new ArrayList<>();

       Cursor cursor= database.rawQuery(" select * from "+My_data_base.CAR_TABLE_NAME,null);

       if (cursor.moveToFirst() ){
           do {
           int id =cursor.getInt(cursor.getColumnIndex(My_data_base.CAR_ID));
           String modle =cursor.getString(cursor.getColumnIndex(My_data_base.CAR_MODLE));
           String color =cursor.getString(cursor.getColumnIndex(My_data_base.CAR_COLOR));
           String discraption =cursor.getString(cursor.getColumnIndex(My_data_base.CAR_DESCRIPTION));
           double dpl =cursor.getDouble(cursor.getColumnIndex(My_data_base.CAR_DPL));

           Car car1=new Car(id,modle,color,discraption,dpl);
           cars.add(car1);
       }while (cursor.moveToNext());
           cursor.close();
       }
       return cars;
   }

    public  Long get_count_enter(){

        return DatabaseUtils.queryNumEntries(database,My_data_base.CAR_TABLE_NAME);
    }

    public boolean delete_all(){

       int result= database.delete("select * from "+ My_data_base.CAR_TABLE_NAME,null,null);

        return result>0;
    }


}
