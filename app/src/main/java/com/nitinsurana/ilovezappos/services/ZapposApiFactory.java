package com.nitinsurana.ilovezappos.services;

import com.nitinsurana.ilovezappos.beans.ApiResult;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by coding_idiot on 06/02/17.
 */

public class ZapposApiFactory {

    public static ZapposApi getApiInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ZapposApi.baseApiUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        ZapposApi service = retrofit.create(ZapposApi.class);
        return service;
    }
}
