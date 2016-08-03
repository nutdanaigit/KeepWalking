package com.augmentis.ayp.keepwalking.model;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.augmentis.ayp.keepwalking.model.KeepWalkDbSchema.KeepWalkTable;
/**
 * Created by Nutdanai on 8/2/2016.
 */
public class KeepWalkBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "";
    private static final String TAG = "";

    public KeepWalkBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG,"CreateDateBase");
        db.execSQL("CREATE TABLE "+ KeepWalkTable.NAME
                + "("
                + "_id integer primary key autoincrement, "
                + KeepWalkTable.Cols.UUID + ","
                + KeepWalkTable.Cols.TITLE + ","
                + KeepWalkTable.Cols.DATE + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
