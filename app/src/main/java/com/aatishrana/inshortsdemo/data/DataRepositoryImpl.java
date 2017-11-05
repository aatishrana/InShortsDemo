package com.aatishrana.inshortsdemo.data;

import com.aatishrana.inshortsdemo.model.CardItem;
import com.aatishrana.inshortsdemo.network.ApiInterface;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Aatish on 11/4/2017.
 */

public class DataRepositoryImpl implements DataRepository
{
    private ApiInterface apiInterface;

    public DataRepositoryImpl(ApiInterface apiInterface)
    {
        this.apiInterface = apiInterface;
    }

    @Override
    public Observable<List<CardItem>> getData()
    {
        return Observable.create(new ObservableOnSubscribe<List<CardItem>>()
        {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<List<CardItem>> e) throws Exception
            {
                apiInterface.getAllData("123", "Android", "Vm10S1MyRXlXbE5UTTBaNFUyMXdhMVF3VGtaVVp6MDk=")
                        .enqueue(new Callback<JsonObject>()
                        {
                            @Override
                            public void onResponse(Call<JsonObject> call, Response<JsonObject> response)
                            {
                                if (response.isSuccessful())
                                {
                                    try
                                    {
                                        List<CardItem> data = MyJsonParser.parseJson(new JSONObject(response.body().toString()));
                                        Collections.sort(data, new Comparator<CardItem>()
                                        {
                                            @Override
                                            public int compare(CardItem o1, CardItem o2)
                                            {
                                                if (o1.getRank() > o2.getRank())
                                                    return 1;
                                                else if (o1.getRank() < o2.getRank())
                                                    return -1;
                                                else
                                                    return 0;
                                            }
                                        });
                                        e.onNext(data);
                                        e.onComplete();
                                    } catch (JSONException ex)
                                    {
                                        ex.printStackTrace();
                                        e.onError(ex);
                                    }
                                } else
                                    e.onError(new RuntimeException("Network Call failed"));
                            }

                            @Override
                            public void onFailure(Call<JsonObject> call, Throwable t)
                            {
                                t.printStackTrace();
                                e.onError(t);
                            }
                        });
            }
        });
    }


}
