package com.maple.paginateexample.like;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.maple.paginateexample.R;
import com.maple.paginateexample.widget.like.LikeButton;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private ListAdapter adapter;
    private RecyclerView recyclerView;

    private List<LikeBean> data;
    private LikeBean bean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        data = new ArrayList<>();
        for (int i = 0; i < 80; i++) {
            bean = new LikeBean();
            bean.title = String.valueOf(i);
            if(i == 5){
               bean.hasLike = true;
            }
            data.add(bean);
        }

        recyclerView = findViewById(R.id.recycler_view);
        adapter = new ListAdapter(this);
        adapter.setData(data);
        recyclerView.setAdapter(adapter);

    }
}
