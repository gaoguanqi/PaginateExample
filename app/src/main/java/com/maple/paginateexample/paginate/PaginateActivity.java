package com.maple.paginateexample.paginate;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.maple.paginateexample.R;
import com.paginate.Paginate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PaginateActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recycler;

    private TestAdapter mAdapter;
    private List<String> mData;

    private Paginate mPaginate;
    private Paginate.Callbacks mCallbacks;
    private LoadMoreItemCreator loadMoreItem;
    private boolean isLoadingMore;
    private String loadingText = "正在加载...";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paginate);

        refreshLayout = findViewById(R.id.refreshLayout);
        recycler = findViewById(R.id.recycler);
        refreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorAccent));
        refreshLayout.setOnRefreshListener(this);
        showRefreshing();
        mData = new ArrayList<>();
        mAdapter = new TestAdapter(this);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(mAdapter);
        initPaginate();

        for (int i = 0; i < 10; i++) {
            mData.add("" + i);
        }
        mAdapter.setData(mData);
        hideRefreshing();
    }

    private void initPaginate() {
        if (mCallbacks == null) {
            mCallbacks = new Paginate.Callbacks() {
                @Override
                public void onLoadMore() {
                    onLoadingMore();
                }


                @Override
                public boolean isLoading() {
                    return isLoadingMore;
                }

                @Override
                public boolean hasLoadedAllItems() {
                    return false;
                }
            };
        }

        if (loadMoreItem == null) {
            loadMoreItem = new LoadMoreItemCreator(loadingText);
        }
        mPaginate = Paginate.with(recycler, mCallbacks)
                .setLoadingTriggerThreshold(0)
                .setLoadingListItemCreator(loadMoreItem)
                .build();
        mPaginate.setHasMoreDataToLoad(false);
    }

    private void onLoadingMore() {
        showLoading();
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        int size = mData.size();
                        if (size >= 30) {
                            setLoadMessage("没有更多了");
                        } else {
                            int size10 = size + 10;
                            for (int i = size; i < size10; i++) {
                                mData.add("" + i);
                            }
                            hideLoading();
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }


    @Override
    public void onRefresh() {
        mData.clear();
        for (int i = 0; i < 10; i++) {
            mData.add("" + i);
        }
        mAdapter.notifyDataSetChanged();
        hideRefreshing();
    }

    public void showRefreshing() {
        refreshLayout.setRefreshing(true);
    }

    public void hideRefreshing() {
        refreshLayout.setRefreshing(false);
    }


    public void setLoadMessage(String msg) {
        isLoadingMore = false;
        if (loadMoreItem != null) {
            loadMoreItem.setViewText(msg);
        }
    }


    public void showLoading() {
        isLoadingMore = true;
        if (loadMoreItem != null) {
            loadMoreItem.setViewState(isLoadingMore);
        }
    }

    public void hideLoading() {
        isLoadingMore = false;
        if (loadMoreItem != null) {
            loadMoreItem.setViewState(isLoadingMore);
        }
    }
}
