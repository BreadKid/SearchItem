package com.breadykid.searchitem.http;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by breadykid on 2016/12/12.
 * 中国物品编码网搜索
 */

public interface SearchItemService {

    @GET("searchResult2.aspx")
    Call<ResponseBody> searchItem(@Query("keyword") String keyword);

}
