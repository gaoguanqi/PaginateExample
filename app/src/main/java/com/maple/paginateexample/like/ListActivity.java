package com.maple.paginateexample.like;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.maple.paginateexample.R;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    private ListAdapter adapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.recycler_view);
        adapter = new ListAdapter(this);
        recyclerView.setAdapter(adapter);

    }
}
