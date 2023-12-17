package com.example.englishdictionary;

import java.io.Serial;
import java.io.Serializable;

public class Word implements Serializable {
    private Integer word_id;
    private String word_eng;
    private String word_tr;
    private String word_other_meaning;

    public Word() {
    }

    public Word(Integer word_id, String word_eng, String word_tr, String word_other_meaning) {
        this.word_id = word_id;
        this.word_eng = word_eng;
        this.word_tr = word_tr;
        this.word_other_meaning = word_other_meaning;
    }

    public Integer getWord_id() {
        return word_id;
    }

    public void setWord_id(Integer word_id) {
        this.word_id = word_id;
    }

    public String getWord_eng() {
        return word_eng;
    }

    public void setWord_eng(String word_eng) {
        this.word_eng = word_eng;
    }

    public String getWord_tr() {
        return word_tr;
    }

    public void setWord_tr(String word_tr) {
        this.word_tr = word_tr;
    }

    public String getWord_other_meaning() {
        return word_other_meaning;
    }

    public void setWord_other_meaning(String word_other_meaning) {
        this.word_other_meaning = word_other_meaning;
    }
}
