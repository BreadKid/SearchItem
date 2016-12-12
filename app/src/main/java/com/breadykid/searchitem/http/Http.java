package com.breadykid.searchitem.http;

import android.util.Log;
import android.widget.Toast;

import com.breadykid.searchitem.domain.Item;
import com.breadykid.searchitem.util.StaticUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.FormElement;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.breadykid.searchitem.util.StaticUtil.DEFAULT_TIMEOUT;
import static com.breadykid.searchitem.util.StaticUtil.SEARCH_ITEM;

/**
 * Created by breadykid on 2016/12/12.
 * 网络通信类
 */

public class Http {

    private Retrofit retrofit;
    private SearchItemService searchItemService;

    private Http() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(SEARCH_ITEM)
                .build();

        searchItemService = retrofit.create(SearchItemService.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final Http INSTANCE = new Http();
    }

    //获取单例
    public static Http getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void searchItem(final String code, final MessageResponse messageResponse) {
        Log.d("网络通信", code);
        Call<ResponseBody> call = searchItemService.searchItem(code);
        Log.d("通信地址", call.request().url().toString());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    AsyncJsoup ac = new AsyncJsoup(call,messageResponse);
                    ac.execute();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

}
