package com.augmentis.ayp.keepwalking.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.augmentis.ayp.keepwalking.model.KeepWalkDbSchema.KeepWalkTable;
/**
 * Created by Nutdanai on 7/27/2016.
 */
public class KeepWalkLab {
    private static KeepWalkLab instance;

    /////////////////////////////////////////// STATIC ZONE //////////////////////////////////////////////

    public static KeepWalkLab getInstance(Context context) {
        if (instance == null) {
            instance = new KeepWalkLab(context);
        }
        return instance;
    }

    public static ContentValues getContentValues(KeepWalk keepWalk){
        ContentValues contentValues = new ContentValues();
        contentValues.put(KeepWalkTable.Cols.UUID,keepWalk.getId().toString());
        contentValues.put(KeepWalkTable.Cols.TITLE,keepWalk.getTitle());
        contentValues.put(KeepWalkTable.Cols.DATE,keepWalk.getKeepDate().getTime());
        return contentValues;
    }

    /////////////////////////////////////////// METHOD ZONE //////////////////////////////////////////////
    private Context context;
    private SQLiteDatabase database;



    public KeepWalkLab(Context context) {
        this.context = context.getApplicationContext();
        KeepWalkBaseHelper keepWalkBaseHelper = new KeepWalkBaseHelper(this.context);
        database = keepWalkBaseHelper.getWritableDatabase();
    }

    public KeepWalk getCrimeById(UUID uuid) {

      KeepWalkCursorWrapper cursor = queryKeep(KeepWalkTable.Cols.UUID+ " = ? ",new String[]{uuid.toString()});
        try{
         if(cursor.getCount() == 0 ){
             return  null;
         }
            cursor.moveToFirst();
            return cursor.getKeepWalk();
        }finally {
            cursor.close();
        }

    }

    public KeepWalk getCrimePositionById(UUID uuid) {
       KeepWalkCursorWrapper cursor = queryKeep(KeepWalkTable.Cols.UUID+ " = ? ",
               new String[] {uuid.toString()});
        try{

            if(cursor.getCount()==0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getKeepWalk();
        }finally {
            cursor.close();
        }

    }

    public int getKeepPositionById(UUID uuid) {
        int countPos = 0;
        KeepWalkCursorWrapper cursor = queryKeep(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                countPos++;
                if (cursor.getKeepWalk().getId().equals(uuid)) {
                    return countPos;
                }
            }
        } finally {
            cursor.close();
        }
            return 0;
    }


    public KeepWalkCursorWrapper queryKeep(String whereCause, String [] whereArgs){
        Cursor cursor = database.query(KeepWalkTable.NAME,
                null,
                whereCause,
                whereArgs,
                null,
                null,
                null);
        return new KeepWalkCursorWrapper(cursor);

    }


    public List<KeepWalk> getKeep(){
        List<KeepWalk> keepWalks = new ArrayList<>();
        KeepWalkCursorWrapper cursor = queryKeep(null,null);
        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                keepWalks.add(cursor.getKeepWalk());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return  keepWalks;
    }



    public void addKeep(KeepWalk keepWalk){
        ContentValues contentValues = getContentValues(keepWalk);
        database.insert(KeepWalkTable.NAME,null,contentValues);
    }

    public void deleteKeep(UUID uuid){
        database.delete(KeepWalkTable.NAME,
                KeepWalkTable.Cols.UUID + " = ? ", new String[] {uuid.toString()});
    }

    public void updateKeep(KeepWalk keepWalk){
        String uuidStr = keepWalk.getId().toString();
        ContentValues contentValues = getContentValues(keepWalk);
        database.update(KeepWalkTable.NAME,contentValues,KeepWalkTable.Cols.UUID + " = ? ",new String[] {uuidStr});
    }

}
