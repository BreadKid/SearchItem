package com.breadykid.searchitem.adpater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.breadykid.searchitem.R;
import com.breadykid.searchitem.domain.Item;

/**
 * Created by breadykid on 2016/12/13.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter{

    private TextView tvName;
    private TextView tvType;
    private TextView tvSize;
    private TextView tvCompany;
    private Context context;
    private Item item=null;

    public RecyclerViewAdapter(Context context, Item item) {
        this.context = context;
        this.item = item;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_item, parent, false);
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
