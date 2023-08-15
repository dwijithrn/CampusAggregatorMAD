package com.example.campusaggreg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class OutputScreen extends AppCompatActivity {

    TextView Title,Desc,Source;
    ImageView Img;
    int position,choose;
    NewsArticle article;
    Bundle bundle;
    DataBaseHelper db = new DataBaseHelper(OutputScreen.this,"ImageFinal", null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output_screen);

        Title = findViewById(R.id.Title);
        Desc = findViewById(R.id.desc);
        Source= findViewById(R.id.source);
        Img = findViewById(R.id.img);

        bundle = getIntent().getExtras();
        position=bundle.getInt("pos");
        choose =bundle.getInt("choose");

        article = db.ClickViewRecord(position,choose);

        Title.setText(article.getTitle());
        Desc.setText(article.getDescription());
        Source.setText("By : "+article.getSource());
        Glide.with(this).load(article.getURL()).into(Img);
    }

    public void back(View view){
        finish();
    }


}