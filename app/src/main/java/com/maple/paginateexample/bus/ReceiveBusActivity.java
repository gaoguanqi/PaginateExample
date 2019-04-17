package com.maple.paginateexample.bus;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.maple.paginateexample.R;
import com.maple.paginateexample.utils.LogUtils;

public class ReceiveBusActivity extends AppCompatActivity {

    private TextView tvMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_bus);

        tvMsg = findViewById(R.id.tv_msg);


        LiveDataBus.get().with("key_text", String.class).observeForever( new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                LogUtils.logGGQ("接收的消息是：" + s);

                tvMsg.setText("接收的消息是：" + s);
            }
        });
    }
}
