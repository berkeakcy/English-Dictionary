package com.example.englishdictionary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context) {
        super(context, "words.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS\"words\" (\n" +
                "\t\"word_id\"\tINTEGER NOT NULL UNIQUE PRIMARY KEY AUTOINCREMENT,\n" +
                "\t\"word_eng\"\tTEXT NOT NULL,\n" +
                "\t\"word_tr\"\tTEXT NOT NULL,\n" +
                "\t\"word_other_meanings\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"word_id\")\n" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS words");
        onCreate(sqLiteDatabase);
    }
}
