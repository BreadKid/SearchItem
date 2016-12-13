package com.breadykid.searchitem.http;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by breadykid on 2016/12/13.
 * 淘宝搜索价格
 */

public interface SearchPriceTaoBaoService {

    @GET("search")
    Call<ResponseBody> searchPriceByTaoBao(@QueryMap(encoded = true) Map<String, String> options);

}
