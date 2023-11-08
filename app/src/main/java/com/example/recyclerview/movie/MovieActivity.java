package com.example.recyclerview.movie;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

public class MovieActivity extends AppCompatActivity {

    private List<String> datas = new ArrayList<>();
    private MovieAdapter adapter;
    private List<String> datas1 = new ArrayList<>();
    private RecyclerView recyclerView;

    private GridLayoutManager layoutManager;

    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        initVertical();
    }

    public void initVertical(){
        recyclerView = findViewById(R.id.recyclerview_vertical);
        layoutManager = new GridLayoutManager(this, 4);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.left = 10;
                outRect.top = 10;
                outRect.right = 10;
                outRect.bottom = 10;
            }
        });

        String s1 = "test1";
        datas.add(s1);
        String s2 = "test2";
        datas.add(s2);
        String s3 = "test3";
        datas.add(s3);
        String s4 = "test4";
        datas.add(s4);
        String s5 = "test5";
        datas.add(s5);
        String s6 = "test6";
        datas.add(s6);
        String s7 = "test7";
        datas.add(s7);
        String s8 = "test8";
        datas.add(s8);
        adapter = new MovieAdapter(datas);
        recyclerView.setAdapter(adapter);
    }

    public void onTest1(View v) {
        if(recyclerView.getChildAt(1) != null) {
            TextView tv = recyclerView.getChildAt(1).findViewById(R.id.tv_content);
            if (tv != null) {
                tv.setText("修改");
            }
        }
    }

    public void onTest2(View v) {

    }



}
