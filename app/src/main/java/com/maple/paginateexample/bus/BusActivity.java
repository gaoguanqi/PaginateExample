package com.maple.paginateexample.bus;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.maple.paginateexample.R;

public class BusActivity extends AppCompatActivity {

    private TextView tvText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);

        findViewById(R.id.btn_receive).setOnClickListener(v -> startActivity(new Intent(BusActivity.this,ReceiveBusActivity.class)));

        tvText = findViewById(R.id.tv_text);

        findViewById(R.id.btn_send).setOnClickListener(v -> {
            LiveDataBus.get().with("key_text", String.class).postValue("这是发射的字符串");
         });


        LiveDataBus.get().with("key_text", String.class).observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                tvText.setText(s);
            }
        });
    }
}
