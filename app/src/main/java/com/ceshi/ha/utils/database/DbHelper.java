package com.ceshi.ha.utils.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * http://www.trinea.cn
 */
public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {
        super(context, DbConstants.DB_NAME, null, DbConstants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            db.execSQL(DbConstants.CREATE_IMAGE_SDCARD_CACHE_TABLE_SQL.toString());
            db.execSQL(DbConstants.CREATE_IMAGE_SDCARD_CACHE_TABLE_INDEX_SQL.toString());

            db.execSQL(DbConstants.CREATE_HTTP_CACHE_TABLE_SQL.toString());
            db.execSQL(DbConstants.CREATE_HTTP_CACHE_TABLE_INDEX_SQL.toString());
            db.execSQL(DbConstants.CREATE_HTTP_CACHE_TABLE_UNIQUE_INDEX.toString());
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
