package com.example.flagquiz;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class FlagDataGenerator {

    public ArrayList<FlagData> generateFlagData(FlagDatabaseHelper flagDatabaseHelper){
            ArrayList<FlagData> listFlagData = new ArrayList<>();
            SQLiteDatabase sqLiteDatabase = flagDatabaseHelper.getWritableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM flagquizapp ORDER BY RANDOM() LIMIT 10", null);
            int flagIdIndex = cursor.getColumnIndex("flag_id");
            int flagNameIndex = cursor.getColumnIndex("flag_name");
            int flagImgIndex = cursor.getColumnIndex("flag_img");

            while (cursor.moveToNext()) {
                FlagData flagData = new FlagData(cursor.getInt(flagIdIndex), cursor.getString(flagNameIndex), cursor.getString(flagImgIndex));
                listFlagData.add(flagData);
            }
            return listFlagData;
    }

    public ArrayList<FlagData> generateFlagDataChoices(FlagDatabaseHelper flagDatabaseHelper, int flag_id){
        ArrayList<FlagData> listFlagData=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=flagDatabaseHelper.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM flagquizapp WHERE flag_id !="+flag_id+" ORDER BY RANDOM () LIMIT 3",null);
        int flagIdIndex=cursor.getColumnIndex("flag_id");
        int flagNameIndex=cursor.getColumnIndex("flag_name");
        int flagImgIndex=cursor.getColumnIndex("flag_img");
        Log.i("generateFlagDataChoices","flagName"+flagImgIndex);


        while(cursor.moveToNext()){
            FlagData flagData = new FlagData(cursor.getInt(flagIdIndex), cursor.getString(flagNameIndex), cursor.getString(flagImgIndex));
            listFlagData.add(flagData);
            Log.i("generateFlagDataChoices","flagName"+flagData.getFlag_name());
        }
        return listFlagData;
    }
}
