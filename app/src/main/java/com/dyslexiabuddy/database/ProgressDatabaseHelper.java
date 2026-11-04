package com.dyslexiabuddy.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProgressDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dyslexia_buddy.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_PROGRESS = "user_progress";

    public ProgressDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_PROGRESS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "stars INTEGER DEFAULT 0, " +
                "streak INTEGER DEFAULT 1, " +
                "completed_lessons INTEGER DEFAULT 0)";
        db.execSQL(createTable);

        ContentValues values = new ContentValues();
        values.put("stars", 15);
        values.put("streak", 3);
        values.put("completed_lessons", 5);
        db.insert(TABLE_PROGRESS, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROGRESS);
        onCreate(db);
    }

    public int getStars() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT stars FROM " + TABLE_PROGRESS + " WHERE id = 1", null);
        int stars = 0;
        if (cursor.moveToFirst()) {
            stars = cursor.getInt(0);
        }
        cursor.close();
        return stars;
    }
}
