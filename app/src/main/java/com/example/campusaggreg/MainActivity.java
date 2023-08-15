package com.example.campusaggreg;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem home,student,admin,dept;
    NewsAdapter adapter;
    Toolbar toolbar;
    RecyclerView recyclerView;
    List<NewsArticle> articles = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        home = findViewById(R.id.home);
        student = findViewById(R.id.student);
        admin = findViewById(R.id.admin);
        dept = findViewById(R.id.dept);
        tabLayout = findViewById(R.id.include);

        DataBaseHelper db = new DataBaseHelper(getApplicationContext(),"ImageFinal", null,1);

        recyclerView =(RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        articles =  db.ReadRecords(-1);

        adapter = new NewsAdapter(articles);
        recyclerView.setAdapter(adapter);


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent intent = new Intent(MainActivity.this, OutputScreen.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("pos",position);
                        bundle.putInt("choose",-1);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    @Override public void onLongItemClick(View view, int position) {
                    }
                })
        );


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                SelectedActivity(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    private void SelectedActivity(int ch)
    {
        if(ch==1)
            startActivity(new Intent(MainActivity.this,Student.class));
        else if(ch==2)
            startActivity(new Intent(MainActivity.this,Dept.class));
        else if (ch==3)
            startActivity(new Intent(MainActivity.this,Admin.class));

    }
}
