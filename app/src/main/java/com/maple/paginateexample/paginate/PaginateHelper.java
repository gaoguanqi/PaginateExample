package com.maple.paginateexample.paginate;

import android.support.v7.widget.RecyclerView;

import com.paginate.Paginate;

/**
 * author: gaogq
 * time: 2019/3/21 20:43
 * description:
 */
public class PaginateHelper {

    private Paginate mPaginate;
    private Paginate.Callbacks mCallbacks;
    private LoadMoreItemCreator loadMoreItem;
    private boolean isLoadingMore;
    private String loadingText;

    private OnLoadMoreListener mListener;

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        this.mListener = listener;
    }

    public void setLoadingMore(boolean loadingMore) {
        isLoadingMore = loadingMore;
    }

    public PaginateHelper(String text,RecyclerView recycler) {
        this.loadingText = text;
        if (mCallbacks == null) {
            mCallbacks = new Paginate.Callbacks() {
                @Override
                public void onLoadMore() {
                    if(mListener != null){
                        showLoading();
                        mListener.onLoadingMore();
                    }
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


    public void setLoadMessage(String msg) {
        isLoadingMore = true;
        //isLoadingMore = false; //会重新加载
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


    public interface OnLoadMoreListener{
        void onLoadingMore();
    }
}
