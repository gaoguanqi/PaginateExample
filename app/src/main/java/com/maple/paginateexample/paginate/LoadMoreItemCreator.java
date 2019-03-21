package com.maple.paginateexample.paginate;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maple.paginateexample.R;
import com.paginate.recycler.LoadingListItemCreator;

/**
 * author: gaogq
 * time: 2019/3/21 17:24
 * description:
 */
class LoadMoreItemCreator implements LoadingListItemCreator {

    private LoadMoreHolder mHolder;
    private String loadText;

    public LoadMoreItemCreator(String loadText) {
        this.loadText = loadText;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loadmore,parent,false);
        mHolder = new LoadMoreHolder(view,loadText);
        mHolder.setLoadText(loadText);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    public void setViewText(String text) {
        if(mHolder != null){
            mHolder.setViewText(text);
        }
    }

    public void setViewState(boolean isLoadMore) {
        if(mHolder != null){
            mHolder.setViewState(isLoadMore);
        }
    }
}
