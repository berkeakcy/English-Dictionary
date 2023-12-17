package com.example.englishdictionary;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Repository{


    public ArrayList<Word> allWords(Database database){

        ArrayList<Word> wordArrayList = new ArrayList<>();
        SQLiteDatabase db = database.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM words",null);
        while (cursor.moveToNext()){
            System.out.println("DB OKUNDU");
            @SuppressLint("Range")
            Word word = new Word(cursor.getInt(cursor.getColumnIndex("word_id")),
                    cursor.getString(cursor.getColumnIndex("word_eng")),
                    cursor.getString(cursor.getColumnIndex("word_tr")),
                    cursor.getString(cursor.getColumnIndex("word_other_meanings")));
            wordArrayList.add(word);
        }
        return wordArrayList;
    }


    public ArrayList<Word> searchWord(Database database, String searchWord){
        ArrayList<Word> wordArrayList = new ArrayList<>();
        SQLiteDatabase db = database.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM words WHERE word_eng like '%"+searchWord+"%'",null);
        while (cursor.moveToNext()){
            @SuppressLint("Range")
            Word word = new Word(cursor.getInt(cursor.getColumnIndex("word_id")),
                    cursor.getString(cursor.getColumnIndex("word_eng")),
                    cursor.getString(cursor.getColumnIndex("word_tr")),
                    cursor.getString(cursor.getColumnIndex("word_other_meanings")));
            wordArrayList.add(word);
        }
        return wordArrayList;
    }

}
