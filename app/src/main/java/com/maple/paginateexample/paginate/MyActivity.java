package com.maple.paginateexample.paginate;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.maple.paginateexample.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import es.dmoral.toasty.Toasty;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MyActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, PaginateHelper.OnLoadMoreListener {
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recycler;

    private TestAdapter mAdapter;
    private List<String> mData;

    private PaginateHelper helper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        refreshLayout = findViewById(R.id.refreshLayout);
        recycler = findViewById(R.id.recycler);
        refreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorAccent));
        refreshLayout.setOnRefreshListener(this);
        showRefreshing();
        mData = new ArrayList<>();
        mAdapter = new TestAdapter(this);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(mAdapter);

        helper = new PaginateHelper("正在加载...",recycler);
        helper.setOnLoadMoreListener(this);

        for (int i = 0; i < 10; i++) {
            mData.add("" + i);
        }
        mAdapter.setData(mData);
        hideRefreshing();
    }

    public void showRefreshing() {
        refreshLayout.setRefreshing(true);
    }

    public void hideRefreshing() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        helper.setLoadingMore(false);
        mData.clear();
        for (int i = 0; i < 10; i++) {
            mData.add("" + i);
        }
        mAdapter.notifyDataSetChanged();
        hideRefreshing();

        Toasty.info(this,"刷新成功",Toast.LENGTH_SHORT, false).show();
    }

    @Override
    public void onLoadingMore() {
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        int size = mData.size();
                        if (size >= 30) {
                            helper.setLoadMessage("没有更多了");
                        } else {
                            int size10 = size + 10;
                            for (int i = size; i < size10; i++) {
                                mData.add("" + i);
                            }
                            helper.hideLoading();
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}
