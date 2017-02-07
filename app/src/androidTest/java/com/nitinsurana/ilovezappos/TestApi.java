package com.nitinsurana.ilovezappos;

import com.nitinsurana.ilovezappos.beans.ApiResult;
import com.nitinsurana.ilovezappos.services.ZapposApi;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by coding_idiot on 06/02/17.
 */
//@RunWith(AndroidJUnit4.class)
public class TestApi {
    @Test
    public void testApi() throws Exception {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.zappos.com/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        ZapposApi service = retrofit.create(ZapposApi.class);
        Call<ApiResult> call = service.listRepos("paper");
        ApiResult result = call.execute().body();
        System.out.println(ToStringBuilder.reflectionToString(result, ToStringStyle.MULTI_LINE_STYLE));
//        System.out.println("Nitin");
//        Log.d("info","Nitin");
    }
}
