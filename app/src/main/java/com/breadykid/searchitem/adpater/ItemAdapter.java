package com.breadykid.searchitem.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.breadykid.searchitem.R;
import com.breadykid.searchitem.domain.Item;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by breadykid on 2016/12/11.
 */

public class ItemAdapter extends BaseAdapter {

//    @Bind(R.id.tv_item)
    TextView tv;

    private Context context;
    private String item=null;

    public ItemAdapter(Context mContext, String mItem) {
        super();
        context = mContext;
        item = mItem;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View res = LayoutInflater.from(context).inflate(R.layout.listview_item, viewGroup, false);
//        ButterKnife.bind(res);
        tv=(TextView)res.findViewById(R.id.tv_item);
        if(item!=null){
            tv.setText(item.toString());
        }
        return res;
    }
}
