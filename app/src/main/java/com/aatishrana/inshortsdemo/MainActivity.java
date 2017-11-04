package com.aatishrana.inshortsdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;

import com.aatishrana.inshortsdemo.model.CardItem;
import com.aatishrana.inshortsdemo.model.itemData.CardItemData;
import com.aatishrana.inshortsdemo.model.itemData.CardItemImage;
import com.aatishrana.inshortsdemo.model.itemData.CardItemMulti;
import com.aatishrana.inshortsdemo.model.itemData.CardItemMultiImage;
import com.aatishrana.inshortsdemo.network.ApiClient;
import com.aatishrana.inshortsdemo.network.ApiInterface;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
{
    RecyclerView recyclerView;
    MyAdapter adapter;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.activity_main_recyclerView);
        adapter = new MyAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        SnapHelper snapHelper = new StartSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        recyclerView.setAdapter(adapter);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getAllData("123", "Android", "Vm10S1MyRXlXbE5UTTBaNFUyMXdhMVF3VGtaVVp6MDk=").enqueue(new Callback<JsonObject>()
        {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response)
            {
                if (response.isSuccessful())
                {
                    try
                    {
                        List<CardItem> datas = parseJson(new JSONObject(response.body().toString()));
                        if (datas != null)
                        {
                            adapter.setData(datas);
                            adapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t)
            {
                t.printStackTrace();
            }
        });
    }

    List<CardItem> parseJson(JSONObject body) throws JSONException
    {
        List<CardItem> cardItemList = new ArrayList<>();
        if (body.has("card_list"))
        {
            JSONArray card_list = body.getJSONArray("card_list");
            for (int i = 0; i < card_list.length(); i++)
            {
                JSONObject card_item = card_list.getJSONObject(i);
                if (card_item.has("card_obj") && card_item.getJSONObject("card_obj").has("type"))
                {
                    String itemType = card_item.getJSONObject("card_obj").getString("type");
                    if (itemType.equalsIgnoreCase("IMAGE") || itemType.equalsIgnoreCase("MULTI_IMAGE_CARD"))
                    {
                        //flat data
                        String id = card_item.getString("id");
                        long rank = card_item.getLong("rank");
                        long version = card_item.getLong("version");
                        JSONObject card_obj = card_item.getJSONObject("card_obj");
                        String share_image = card_obj.getString("share_image");
                        String share_text = card_obj.getString("share_text");
                        String title = card_obj.getString("title");
                        String type = card_obj.getString("type");

                        //categories
                        List<String> categories = new ArrayList<>();
//                        if (card_obj.has("categories"))
//                            for (int j = 0; j <= card_obj.getJSONArray("categories").length(); j++)
//                            {
//                                String cat = (String) card_obj.getJSONArray("categories").get(j);
//                                categories.add(cat);
//                            }

                        //data
                        CardItemData data = null;
                        JSONObject card_data = card_obj.getJSONObject("data");
                        if (type.equalsIgnoreCase("Image"))
                        {
                            int height = card_data.getInt("height");
                            int width = card_data.getInt("width");
                            String image_url = card_data.getString("image_url");
                            String placeHolderAlt = "", placeHolderData = "", placeHolderType = "";

                            if (card_data.has("placeholder"))
                            {
                                placeHolderAlt = card_data.getJSONObject("placeholder").getString("alt");
                                placeHolderData = card_data.getJSONObject("placeholder").getString("data");
                                placeHolderType = card_data.getJSONObject("placeholder").getString("type");
                            }
                            data = new CardItemImage(height, width, image_url, placeHolderAlt, placeHolderData, placeHolderType);
                        } else if (type.equalsIgnoreCase("MULTI_IMAGE_CARD"))
                        {
                            List<CardItemMultiImage> cardItemMultiImageList = new ArrayList<>();
                            JSONObject card_data_list = card_data.getJSONObject("card_data_list");
                            Iterator<String> keys = card_data_list.keys();
                            while (keys.hasNext())
                            {
                                String key = keys.next();
                                JSONObject position = card_data_list.getJSONObject(key);
                                String placeHolderAlt, placeHolderData, placeHolderType;

                                int pos_height = position.getInt("height");
                                int pos_width = position.getInt("width");
                                String pos_id = position.getString("id");
                                String full_story_url = position.getString("full_story_url");
                                String image_url = position.getString("image_url");

                                placeHolderAlt = position.getJSONObject("placeholder").getString("alt");
                                placeHolderData = position.getJSONObject("placeholder").getString("data");
                                placeHolderType = position.getJSONObject("placeholder").getString("type");

                                cardItemMultiImageList.add(
                                        new CardItemMultiImage(pos_height, pos_width, pos_id, full_story_url, image_url, placeHolderAlt,
                                                placeHolderData, placeHolderType));
                            }

                            data = new CardItemMulti(cardItemMultiImageList);
                        }

                        CardItem item = new CardItem(id, share_image, share_text, title, type, version, rank, categories, data);
                        cardItemList.add(item);
                    }
                }
            }
        }
        return cardItemList;
    }
}
