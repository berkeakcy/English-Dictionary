package com.example.englishdictionary;

import android.annotation.SuppressLint;
import android.content.ContentValues;
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
            @SuppressLint("Range")
            Word word = new Word(cursor.getInt(cursor.getColumnIndex("word_id")),
                    cursor.getString(cursor.getColumnIndex("word_eng")),
                    cursor.getString(cursor.getColumnIndex("word_tr")),
                    cursor.getString(cursor.getColumnIndex("word_other_meanings")));
            wordArrayList.add(word);
        }
        return wordArrayList;
    }

    public void deleteWord(Database database, int id){
        SQLiteDatabase db = database.getWritableDatabase();
        db.delete("words","word_id=?",new String[]{String.valueOf(id)});
    }

    public void addWord(Database database, String word_eng, String word_tr,String word_other_meanings){
        SQLiteDatabase db = database.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("word_eng",word_eng);
        values.put("word_tr",word_tr);
        values.put("word_other_meanings",word_other_meanings);

        db.insertOrThrow("words",null,values);
        db.close();
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
