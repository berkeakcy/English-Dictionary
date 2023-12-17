package com.example.englishdictionary;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends  RecyclerView.Adapter<RecyclerViewAdapter.CardDesign> {
    private Context xContext;
    private List<Word> wordList;

    public RecyclerViewAdapter(Context xContext, List<Word> wordList) {
        this.xContext = xContext;
        this.wordList = wordList;
    }

    @NonNull
    @Override
    public CardDesign onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_design,parent,false);
        return new CardDesign(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardDesign holder, int position) {
        Word Word = wordList.get(position);

        holder.eng_textView.setText(Word.getWord_eng());
        holder.tr_textView.setText(Word.getWord_tr());
        holder.word_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(xContext, DetailsActivity.class);
                intent.putExtra("word",Word);
                xContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    public class CardDesign extends RecyclerView.ViewHolder{
        private TextView eng_textView;
        private TextView tr_textView;
        private CardView word_cardView;

        public CardDesign(@NonNull View itemView) {
            super(itemView);
            eng_textView = itemView.findViewById(R.id.eng_textView);
            tr_textView = itemView.findViewById(R.id.tr_textView);
            word_cardView = itemView.findViewById(R.id.word_cardView);
        }
    }


}
