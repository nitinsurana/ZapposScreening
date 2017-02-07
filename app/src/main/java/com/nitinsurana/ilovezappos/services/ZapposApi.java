package com.nitinsurana.ilovezappos.services;

import com.nitinsurana.ilovezappos.beans.ApiResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by coding_idiot on 06/02/17.
 */

public interface ZapposApi {
    String baseApiUrl = "https://api.zappos.com/";

    //    https://api.zappos.com/Search?term=&key=b743e26728e16b81da139182bb2094357c31d331
    @GET("Search?key=b743e26728e16b81da139182bb2094357c31d331")
    Call<ApiResult> listRepos(@Query("term") String term);
}
