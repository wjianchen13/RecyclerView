package com.example.recyclerview.chat;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;
import com.example.recyclerview.chat.bean.Product;
import com.example.recyclerview.marquee.MarqueeAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvTest1;
    private TextView tvTest2;
    private RecyclerView rvChat;
    private List<String> datas = new ArrayList<>();
    private MarqueeAdapter adapter;
    private LinearLayoutManager layoutManager;

    protected List<Product.Classify> classifies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        rvChat = findViewById(R.id.rv_chat);
        initData();
        initRecyclerView();

    }

    private void initData() {
        classifies.add(new Product.Classify("颜色颜色颜色颜色颜色颜色颜色颜色颜色颜色颜色颜色颜色颜色颜色颜色", Arrays.asList(new Product.Classify.Des("红色"),
                new Product.Classify.Des("白色"),
                new Product.Classify.Des("蓝色"),
                new Product.Classify.Des("咖啡色"))));

    }

    public void initRecyclerView(){
        rvChat.setLayoutManager(new LinearLayoutManager(this));
        rvChat.setAdapter(new ProductAdapter(this, classifies));
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tv_test1) {

        } else if(v.getId() == R.id.tv_test2) {

        }
    }
}
