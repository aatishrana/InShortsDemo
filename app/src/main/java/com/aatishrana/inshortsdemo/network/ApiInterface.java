package com.aatishrana.inshortsdemo.network;


import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;

import retrofit2.http.Header;

/**
 * Created by Aatish on 11/4/2017.
 */

public interface ApiInterface
{
    @GET("all_card?first_login=true")
    Call<JsonObject> getAllData(@Header("X-DEVICE-ID") String xDeviceId,
                                @Header("X-OS-TYPE") String xOsType,
                                @Header("X-AUTH-TOKEN") String token);
}
