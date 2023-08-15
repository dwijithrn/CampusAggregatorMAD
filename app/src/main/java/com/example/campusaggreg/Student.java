package com.example.campusaggreg;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Student extends AppCompatActivity {


    TabLayout tabLayout;
    TabItem home,student,admin,dept;
    NewsAdapter adapter;
    Toolbar toolbar;
    RecyclerView recyclerView;
    List<NewsArticle> articles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        home = findViewById(R.id.home);
        student = findViewById(R.id.student);
        admin = findViewById(R.id.admin);
        dept = findViewById(R.id.dept);
        tabLayout = findViewById(R.id.include);

        DataBaseHelper db = new DataBaseHelper(Student.this,"ImageFinal", null,1);

        recyclerView =(RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(Student.this));

        articles =  db.ReadRecords(0);

        adapter = new NewsAdapter(articles);
        recyclerView.setAdapter(adapter);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent intent = new Intent(Student.this, OutputScreen.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("pos",position);
                        bundle.putInt("choose",0);
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
            startActivity(new Intent(Student.this,Dept.class));
        else if(ch==2)
            startActivity(new Intent(Student.this,Admin.class));
        else if (ch==3)
            startActivity(new Intent(Student.this,MainActivity.class));

    }
}
