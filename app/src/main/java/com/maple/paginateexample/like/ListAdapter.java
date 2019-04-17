package com.maple.paginateexample.like;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maple.paginateexample.R;
import com.maple.paginateexample.widget.like.LikeButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joel on 3/3/18.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {
    private Activity activity;
    private List<Integer> numbers;
    private final int max = 30;


    public ListAdapter(Activity activity) {
        this.activity = activity;
    }

    private void generateNumbers() {
        numbers = new ArrayList<>();

        for (int x = 1; x <= max; x++) {
            numbers.add(x);
        }
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.list_row, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {

        holder.title.setText(String.valueOf(position));
        if (position % 2 == 0) {
            holder.starButton.setLiked(true);
        } else {
            holder.starButton.setLiked(false);
        }
    }

    @Override
    public int getItemCount() {
        return max;
    }


    static class ListViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        LikeButton starButton;

        ListViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            starButton = view.findViewById(R.id.star_button);
        }
    }
}
