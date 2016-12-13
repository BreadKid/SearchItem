package com.breadykid.searchitem.adpater;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.breadykid.searchitem.R;
import com.breadykid.searchitem.domain.Item;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by breadykid on 2016/12/11.
 */

public class ItemAdapter extends BaseAdapter {

    private TextView tvName;
    private TextView tvType;
    private TextView tvSize;
    private TextView tvCompany;
    private Context context;
    private Item item = null;

    public ItemAdapter(Context mContext, Item object) {
        super();
        context = mContext;
        item = object;
    }

    @Override
    public int getCount() {
        int count = 1;
//        if (o == ArrayList.class) {
//            ArrayList list = (ArrayList<String>) o;
//            count = list.size();
//        } else if (o == Item.class) {
//            count = 1;
//        }
        return count;
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
        tvName = (TextView) res.findViewById(R.id.tv_item_name);
        tvSize = (TextView) res.findViewById(R.id.tv_item_size);
        tvType = (TextView) res.findViewById(R.id.tv_item_type);
        tvCompany = (TextView) res.findViewById(R.id.tv_item_company);

        // 设置字体
        Typeface typeFace = Typeface.createFromAsset(context.getAssets(), "fonts/NotoSansCJKsc-Light.otf");// 应用字体
        tvName.setTypeface(typeFace);
        tvCompany.setTypeface(typeFace);
        tvSize.setTypeface(typeFace);
        tvType.setTypeface(typeFace);
        if (item != null) {
//            Item item = (Item) o;
            if (item.getName().startsWith("http")) {
                tvName.setText(item.getCode());
            } else {
                tvName.setText(item.getName());
                tvType.setText(item.getType());
                tvSize.setText(item.getSize());
                tvCompany.setText(item.getCompany());
            }
        }
//            if (o.==String[].class){
//
//            }
        return res;
    }
}
