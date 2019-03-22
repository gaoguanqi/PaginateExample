package com.maple.paginateexample.paginate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maple.paginateexample.R;

import java.util.List;

/**
 * author: gaogq
 * time: 2019/3/22 2:45
 * description:
 */
public class PermissionAdapter extends RecyclerView.Adapter<PermissionAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mData;

    public PermissionAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<String> data){
        this.mData = data;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_permission,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.setData(mData.get(i));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 :mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void setData(String s) {

        }
    }
}
