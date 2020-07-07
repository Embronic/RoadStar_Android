package com.road.star.api;


import com.road.star.models.AccessTokenResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("access-token")
    Call<AccessTokenResponse> getToken(@Field("mobile") String mobile);

}
