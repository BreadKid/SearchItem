package com.breadykid.searchitem.http;

import android.util.Log;
import android.widget.Toast;

import com.breadykid.searchitem.domain.Item;
import com.breadykid.searchitem.util.StaticUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.FormElement;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Time;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.breadykid.searchitem.util.StaticUtil.DEFAULT_TIMEOUT;
import static com.breadykid.searchitem.util.StaticUtil.SEARCH_ITEM;
import static com.breadykid.searchitem.util.StaticUtil.SEARCH_ITEM_PRICE_TAOBAO;
import static com.breadykid.searchitem.util.StaticUtil.TAOBAO_JSON_PRICE;
import static com.breadykid.searchitem.util.StaticUtil.TAOBAO_JSON_TAG1;
import static com.breadykid.searchitem.util.StaticUtil.TAOBAO_JSON_TAG2;
import static com.breadykid.searchitem.util.StaticUtil.TAOBAO_JSON_TAG3;

/**
 * Created by breadykid on 2016/12/12.
 * 网络通信类
 */

public class Http {

    private Retrofit retrofit;
    private SearchItemService searchItemService;
    private SearchPriceTaoBaoService searchPriceTaoBaoService;
    private OkHttpClient.Builder builder;

    private Http() {
        //手动创建一个OkHttpClient并设置超时时间
        builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final Http INSTANCE = new Http();
    }

    //获取单例
    public static Http getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 中国商品编码网搜索商品信息
     * @param code
     * @param messageResponse
     */
    public void searchItem(final String code, final MessageResponse messageResponse) {
        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(SEARCH_ITEM)
                .build();
        searchItemService = retrofit.create(SearchItemService.class);
        Log.d("网络通信", code);
        Call<ResponseBody> callItem = searchItemService.searchItem(code);
        Log.d("通信地址", callItem.request().url().toString());
        callItem.enqueue(new Callback<ResponseBody>() {
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

    /**
     * 淘宝搜索商品价格
     * @param item
     */
    public void searchPriceByTaoBao(String item){
        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(SEARCH_ITEM_PRICE_TAOBAO)
                .build();
        searchPriceTaoBaoService = retrofit.create(SearchPriceTaoBaoService.class);

        Map<String,String> mapOption=new HashMap<>();
        mapOption.put("q",item);// url encode
        mapOption.put("stats_click","search_radio_all%3A1");
        mapOption.put("initiative_id","staobaoz_20161213"/*+ System.currentTimeMillis()*/);// 20161212
        mapOption.put("sort","price-asc");
        mapOption.put("ajax","true");

        Call<ResponseBody> callPriceTaoBao = searchPriceTaoBaoService.searchPriceByTaoBao(mapOption);
        Log.d("通信地址", callPriceTaoBao.request().url().toString());
        callPriceTaoBao.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        String priceTaoBao = response.body().string();
                        String json1 = new JSONObject(priceTaoBao).getString(TAOBAO_JSON_TAG1);
                        String json2= new JSONObject(json1).getString(TAOBAO_JSON_TAG2);
                        String json3= new JSONObject(json2).getString(TAOBAO_JSON_TAG3);
                        String json= new JSONObject(json3).getString(TAOBAO_JSON_PRICE);
                        Log.d("淘宝返回json",json);

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

}
