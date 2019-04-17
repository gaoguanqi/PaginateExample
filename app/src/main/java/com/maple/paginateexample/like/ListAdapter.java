package com.maple.paginateexample.like;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maple.paginateexample.R;
import com.maple.paginateexample.widget.like.LikeButton;
import com.maple.paginateexample.widget.like.OnLikeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joel on 3/3/18.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {
    private Activity activity;
    private List<LikeBean> mData;


    public ListAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setData(List<LikeBean> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.list_row, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        holder.setData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    static class ListViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        LikeButton starButton;

        ListViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            starButton = view.findViewById(R.id.star_button);
        }

        public void setData(LikeBean bean) {
            title.setText(bean.title);
            starButton.setLiked(bean.hasLike);
            starButton.setOnLikeListener(new OnLikeListener() {
                @Override
                public void liked(LikeButton likeButton) {
                    bean.hasLike = true;
                }

                @Override
                public void unLiked(LikeButton likeButton) {
                    bean.hasLike = false;
                }
            });
        }
    }
}
