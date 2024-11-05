package com.example.lab3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;

public class DatabaseHelper extends android.database.sqlite.SQLiteOpenHelper {
    private static final String DATABASE_NAME = "group.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "groupmates";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FULL_NAME = "full_name";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_FULL_NAME + " TEXT NOT NULL, " +
                    COLUMN_TIMESTAMP + " TIME DEFAULT CURRENT_TIME" + ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        initializeData(db);
    }

    private void initializeData(SQLiteDatabase db) {
        db.execSQL("DELETE FROM " + TABLE_NAME);
        String[] fullNames = {
                "Новикова Анастасия Евгеньевна",
                "Петров Пётр Петрович",
                "Сидоров Сидор Сидорович",
                "Никитин Никита Никитич",
                "Сергеев Сергей Сергеевич"
        };
        for (String name : fullNames) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_FULL_NAME, name);
            db.insert(TABLE_NAME, null, values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}