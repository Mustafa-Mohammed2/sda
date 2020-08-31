package com.example.project_external_data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class My_data_base extends SQLiteAssetHelper {

    public static final String DB_NAME="last_try.db";
    public static final int VERSION=2;


    public static final String CAR_TABLE_NAME="last";
    public static final String CAR_ID="id";
    public static final String CAR_MODLE="modle";
    public static final String CAR_COLOR="color";
    public static final String CAR_DESCRIPTION="description";
    public static final String CAR_IMAGE="image";
    public static final String CAR_DPL="destancePerLetter";

    public My_data_base(Context context ) {
        super(context, DB_NAME,null, VERSION);
    }


}