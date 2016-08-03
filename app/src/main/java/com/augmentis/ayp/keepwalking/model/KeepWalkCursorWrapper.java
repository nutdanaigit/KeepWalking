package com.augmentis.ayp.keepwalking.model;

import android.database.Cursor;
import android.database.CursorWrapper;
import com.augmentis.ayp.keepwalking.model.KeepWalkDbSchema.KeepWalkTable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Nutdanai on 8/2/2016.
 */
public class KeepWalkCursorWrapper extends CursorWrapper {

    public KeepWalkCursorWrapper(Cursor cursor) {super(cursor);}

    public KeepWalk getKeepWalk(){
        String uuidString = getString(getColumnIndex(KeepWalkTable.Cols.UUID));
        String title = getString(getColumnIndex(KeepWalkTable.Cols.TITLE));
        long date = getLong(getColumnIndex(KeepWalkTable.Cols.DATE));

        KeepWalk keepWalk = new KeepWalk(UUID.fromString(uuidString));
        keepWalk.setTitle(title);
        keepWalk.setKeepDate(new Date());

        return keepWalk;
    }
}
