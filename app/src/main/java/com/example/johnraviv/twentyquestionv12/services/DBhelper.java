package com.example.johnraviv.twentyquestionv12.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
// DATABASE FOR HIGHSCORES. locally set with sqLite
public class DBhelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "HighScore.db";
    public static final String TABLE_NAME = "users";
    private static final String COLUMN_NAME = "playername";
    private static final String COLUMN_SCORE = "userscore";

    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (playername TEXT , userscore INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);

    }

    public String loadHandler() {

        String result = "";
        String query = "Select * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_SCORE+ " DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            String result_0 = cursor.getString(0);
            int result_1 = cursor.getInt(1);
            result += String.valueOf(result_0) + " " + result_1 +
                    System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }

    public void addHandler(User user) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_SCORE, user.getScore());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME,null, values);
        db.close();

    }
}
