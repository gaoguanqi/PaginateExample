package com.maple.paginateexample.paginate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maple.paginateexample.R;

import java.util.List;

/**
 * author: gaogq
 * time: 2019/3/21 14:32
 * description:
 */
public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {
    private Context mContext;
    private List<String> mData;

    public TestAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<String> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_test,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TestAdapter.ViewHolder viewHolder, int i) {
        viewHolder.setData(mData.get(i));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0:mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvText = itemView.findViewById(R.id.item_text);
        }

        public void setData(String s) {
            tvText.setText(s);
        }
    }
}
