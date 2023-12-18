package com.example.englishdictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailsActivity extends AppCompatActivity {

    private Word word;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Toolbar toolbar2 = findViewById(R.id.detailsToolbar);
        Button eng_text = findViewById(R.id.eng_button);
        TextView tr_textView = findViewById(R.id.tr_textView);
        TextView other_meanings_textView = findViewById(R.id.other_meanings_textView);
        TextView genel_textView = findViewById(R.id.genel_textView);
        TextView yan_anlam_textView = findViewById(R.id.yan_anlam_textView);

        toolbar2.setTitle("Dictionary");

        setSupportActionBar(toolbar2);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        word = (Word) getIntent().getSerializableExtra("word");
        System.out.println(word);
        eng_text.setText(word.getWord_eng());
        tr_textView.setText(word.getWord_tr());

        other_meanings_textView.setText("");
        String other_meanings = word.getWord_other_meaning();
        String[] values = other_meanings.split(",\\s*");
        for (String value : values){
            other_meanings_textView.append(value);
            other_meanings_textView.append("\n");
        }

        if (other_meanings == ""){
            yan_anlam_textView.setVisibility(View.GONE);
            System.out.println("null");
        }
        else{
            yan_anlam_textView.setVisibility(View.VISIBLE);
            System.out.println("doly");
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