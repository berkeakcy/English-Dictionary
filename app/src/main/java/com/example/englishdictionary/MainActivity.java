package com.example.englishdictionary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private Toolbar toolbar;
    private RecyclerView rv;
    private ArrayList<Word> wordList;
    private RecyclerViewAdapter adapter;
    private Database database;
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new Database(this);
        databaseCopy();

        toolbar = findViewById(R.id.toolbar);
        rv = findViewById(R.id.rv);
        fab = findViewById(R.id.floatingActionButton);

        toolbar.setTitle("Dictionary");
        setSupportActionBar(toolbar);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        wordList = new Repository().allWords(database);

        adapter = new RecyclerViewAdapter(this,wordList,database);
        rv.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                alertShow();
            }
        });

    }

    public void addBackButtonInToolbar(Toolbar toolbar, AppCompatActivity appCompatActivity){
        appCompatActivity.setSupportActionBar(toolbar);
        if (appCompatActivity.getSupportActionBar() != null){
            appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            appCompatActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    public void searchWord(String searchWord){
        wordList = new Repository().searchWord(database,searchWord);
        adapter = new RecyclerViewAdapter(this,wordList,database);
        rv.setAdapter(adapter);
    }

    public void alertShow(){
        LayoutInflater layout = LayoutInflater.from(this);
        View design = layout.inflate(R.layout.alert_design,null);

        EditText eng_editText = design.findViewById(R.id.eng_editText);
        EditText tr_editText = design.findViewById(R.id.tr_editText);
        EditText other_meanings_editText = design.findViewById(R.id.other_meanings_editText);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Kelime ekle");
        alert.setView(design);
        alert.setPositiveButton("Ekle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        alert.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        alert.create().show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_search_menu,menu);

        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(this );

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.e("gönderilen arama",query);
        searchWord(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.e("harf girdikçe",newText);
        searchWord(newText);
        return false;
    }

    public void databaseCopy(){
        DatabaseCopyHelper databaseCopyHelper = new DatabaseCopyHelper(this);
        try {
            databaseCopyHelper.createDataBase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        databaseCopyHelper.openDataBase();
    }
}