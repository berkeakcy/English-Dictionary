package com.example.englishdictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    private Word word;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Toolbar toolbar2 = findViewById(R.id.detailsToolbar);
        Button eng_textView = findViewById(R.id.eng_button);
        TextView tr_textView = findViewById(R.id.genel_textView);
        TextView other_meanings_textView = findViewById(R.id.yanAnlam_textView);

        toolbar2.setTitle("Dictionary");

        setSupportActionBar(toolbar2);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        word = (Word) getIntent().getSerializableExtra("word");
        System.out.println(word);
        eng_textView.setText(word.getWord_eng());
        tr_textView.setText(word.getWord_tr());
        //other_meanings_textView.setText(word.getWord_other_meaning());

        other_meanings_textView.setText("");
        String other_meanings = word.getWord_other_meaning();
        String[] values = other_meanings.split(",\\s*");
        for (String value : values){
            other_meanings_textView.append(value);
            other_meanings_textView.append("\n");
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Geri butonuna tıklama olayı
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}