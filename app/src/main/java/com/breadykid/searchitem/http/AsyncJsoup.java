package com.breadykid.searchitem.http;

import android.os.AsyncTask;
import android.util.Log;

import com.breadykid.searchitem.domain.Item;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

import retrofit2.Call;

/**
 * Created by breadykid on 2016/12/12.
 * 异步处理解析类
 */

public class AsyncJsoup extends AsyncTask {

    private Call call;
    private Item item;
    private MessageResponse messageResponse;

    public AsyncJsoup(Call call, MessageResponse messageResponse) {
        this.call = call;
        this.messageResponse = messageResponse;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            Document doc = Jsoup.connect(call.request().url().toString()).get();
            Elements itemInfo = doc.select("div.wrap").select("div.bodyer");
            Log.d("商品信息", itemInfo.text());

            if (itemInfo.text().equals("暂时没有您要找的商品！")) {
                item = null;
            } else {
                // 处理string
                String[] strItem = itemInfo.text().split(" ");
                Log.d("商品信息处理", strItem[1]);
                item = new Item(strItem[1], strItem[10], strItem[4], strItem[12], strItem[6]);
            }
            messageResponse.onReceivedSuccess(item);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
