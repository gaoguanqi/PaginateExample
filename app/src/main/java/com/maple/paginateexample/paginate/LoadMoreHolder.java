package com.maple.paginateexample.paginate;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.maple.paginateexample.R;

/**
 * author: gaogq
 * time: 2019/3/21 17:27
 * description:
 */
public class LoadMoreHolder extends RecyclerView.ViewHolder {
    private LinearLayout root;
    private ProgressBar pb;
    private TextView tv;

    private String loadText;

    public LoadMoreHolder(@NonNull View itemView,String text) {
        super(itemView);
        root = itemView.findViewById(R.id.root_loading);
        pb = itemView.findViewById(R.id.pb_loading);
        tv = itemView.findViewById(R.id.tv_loading);
        this.loadText = text;
    }

    public void setLoadText(String loadText) {
        if(root.getVisibility() == View.GONE){
            root.setVisibility(View.VISIBLE);
        }

        if(pb.getVisibility() == View.GONE){
            pb.setVisibility(View.VISIBLE);
        }

        if(tv.getVisibility() == View.GONE){
            tv.setVisibility(View.VISIBLE);
        }
        tv.setText(loadText);
    }



    public void setViewText(String text) {
        if(root.getVisibility() == View.GONE){
            root.setVisibility(View.VISIBLE);
        }

        if(pb.getVisibility() == View.VISIBLE){
            pb.setVisibility(View.GONE);
        }

        if(tv.getVisibility() == View.GONE){
            tv.setVisibility(View.VISIBLE);
        }
        tv.setText(text);
    }


    public void setViewState(boolean isLoadMore) {
        if(isLoadMore){
            if(root.getVisibility() == View.GONE){
                root.setVisibility(View.VISIBLE);
            }
            if(pb.getVisibility() == View.GONE){
                pb.setVisibility(View.VISIBLE);
            }

            if(tv.getVisibility() == View.GONE){
                tv.setVisibility(View.VISIBLE);
            }
            tv.setText(loadText);
        }else {
            if(root.getVisibility() == View.VISIBLE){
                root.setVisibility(View.GONE);
            }
        }
    }
}
